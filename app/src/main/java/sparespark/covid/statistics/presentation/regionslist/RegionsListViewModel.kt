package sparespark.covid.statistics.presentation.regionslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Resource
import sparespark.covid.statistics.domain.usecases.GetRegionsListUseCase
import javax.inject.Inject

@HiltViewModel
class RegionsListViewModel @Inject constructor(
    private val getRegionsListUseCase: GetRegionsListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RegionsListState())
    val state: State<RegionsListState> = _state

    init {
        getRegionsList()
    }

    private fun getRegionsList() {
        getRegionsListUseCase().onEach { result ->
            when (result) {
                is Resource.Loading ->
                    _state.value =
                        RegionsListState(isLoading = true)
                is Resource.Error ->
                    _state.value =
                        RegionsListState(error = result.message ?: Constants.ERROR_OCCURRED)
                is Resource.Success ->
                    _state.value =
                        RegionsListState(regionsResponse = result.data)
            }
        }.launchIn(viewModelScope)
    }
}