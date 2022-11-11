package sparespark.covid.statistics.data.model.provincereport

data class ReportData(
    val active: Int? = null,
    val confirmed: Int? = null,
    val deaths: Int? = null,
    val last_update: String? = null,
    val recovered: Int? = null,
    val region: Region?
)
//    val active_diff: Int,
//    val confirmed_diff: Int,
//    val date: String,
//    val deaths_diff: Int,
//    val fatality_rate: Double,
//    val recovered_diff: Int,