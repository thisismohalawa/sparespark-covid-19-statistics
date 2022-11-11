package sparespark.covid.statistics.presentation.provinceslist

import sparespark.covid.statistics.data.model.province.ProvincesResponse


data class ProvincesListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val provincesResponse: ProvincesResponse? = null
)
