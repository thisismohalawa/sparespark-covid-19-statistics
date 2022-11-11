package sparespark.covid.statistics.presentation.regionslist

import sparespark.covid.statistics.data.model.region.RegionsResponse

data class RegionsListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val regionsResponse: RegionsResponse? = null
)
