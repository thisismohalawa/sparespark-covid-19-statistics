package sparespark.covid.statistics.presentation.provincereport

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
import sparespark.covid.statistics.domain.usecases.GetProvinceReportUseCase
import javax.inject.Inject


@HiltViewModel
class ProvinceReportViewModel @Inject constructor(
    private val reportUseCase: GetProvinceReportUseCase,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val _state = mutableStateOf(ProvinceReportViewState())
    val state: State<ProvinceReportViewState> = _state

    init {
        val iso = savedStateHandle.get<String>(Constants.PARAM_ISO_KEY)
        val regionProvince = savedStateHandle.get<String>(Constants.PARAM_PROVINCE_KEY)

        if (iso != null && regionProvince != null)
            getProvincesReport(iso = iso, regionProvince = regionProvince)
    }

    private fun getProvincesReport(iso: String, regionProvince: String) {
        reportUseCase(iso, regionProvince).onEach { result ->
            when (result) {
                is Resource.Loading ->
                    _state.value =
                        ProvinceReportViewState(isLoading = true)
                is Resource.Error ->
                    _state.value =
                        ProvinceReportViewState(error = result.message ?: Constants.ERROR_OCCURRED)
                is Resource.Success ->
                    _state.value =
                        ProvinceReportViewState(report = result.data)
            }
        }.launchIn(viewModelScope)
    }
}