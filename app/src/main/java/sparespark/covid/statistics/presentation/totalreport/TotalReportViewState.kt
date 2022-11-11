package sparespark.covid.statistics.presentation.totalreport

import sparespark.covid.statistics.data.model.totalreport.TotalReportResponse

data class TotalReportViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val report: TotalReportResponse? = null
)
