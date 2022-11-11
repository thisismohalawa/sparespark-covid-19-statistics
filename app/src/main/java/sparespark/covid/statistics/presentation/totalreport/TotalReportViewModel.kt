package sparespark.covid.statistics.presentation.totalreport

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Resource
import sparespark.covid.statistics.domain.usecases.GetTotalReportUseCase
import javax.inject.Inject


@HiltViewModel
class TotalReportViewModel @Inject constructor(
    private val getTotalReportUseCase: GetTotalReportUseCase
) : ViewModel() {
    /*
    * ViewModel major responsibility is holding UI state, all business logic
    * will be implemented in use cases...
    *
    *
    *
    * */
    private val _state = mutableStateOf(TotalReportViewState())
    val state: State<TotalReportViewState> = _state

    init {
        getTotalReport()
    }

    private fun getTotalReport() {
        getTotalReportUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value =
                    TotalReportViewState(isLoading = true)
                is Resource.Error -> _state.value =
                    TotalReportViewState(error = result.message ?: Constants.ERROR_OCCURRED)
                is Resource.Success ->
                    _state.value = TotalReportViewState(report = result.data)
            }
        }.launchIn(viewModelScope)
    }
}