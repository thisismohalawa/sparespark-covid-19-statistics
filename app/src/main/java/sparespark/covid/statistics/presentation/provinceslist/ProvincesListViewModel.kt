package sparespark.covid.statistics.presentation.provinceslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Resource
import sparespark.covid.statistics.domain.usecases.GetProvincesUseCase
import javax.inject.Inject

@HiltViewModel
class ProvincesListViewModel @Inject constructor(
    private val getProvincesUseCase: GetProvincesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ProvincesListState())
    val state: State<ProvincesListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ISO_KEY)?.let { iso_key ->
            getProvincesList(iso_key)
        }
    }

    private fun getProvincesList(iso: String) {
        getProvincesUseCase(iso).onEach { result ->
            when (result) {
                is Resource.Loading ->
                    _state.value = ProvincesListState(isLoading = true)
                is Resource.Error ->
                    _state.value =
                        ProvincesListState(error = result.message ?: Constants.ERROR_OCCURRED)
                is Resource.Success ->
                    _state.value =
                        ProvincesListState(provincesResponse = result.data)
            }
        }.launchIn(viewModelScope)
    }
}