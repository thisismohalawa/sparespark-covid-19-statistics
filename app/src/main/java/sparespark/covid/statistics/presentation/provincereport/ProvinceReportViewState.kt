package sparespark.covid.statistics.presentation.provincereport

import sparespark.covid.statistics.data.model.provincereport.ReportResponse

data class ProvinceReportViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val report: ReportResponse? = null
)
