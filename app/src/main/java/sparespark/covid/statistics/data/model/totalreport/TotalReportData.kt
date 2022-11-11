package sparespark.covid.statistics.data.model.totalreport

data class TotalReportData(
    val active: Int? = null,
    val confirmed: Int? = null,
    val deaths: Int? = null,
    val last_update: String?=null,
    val recovered: Int? = null,
)
//    val deaths_diff: Int,
//    val fatality_rate: Double,
//    val active_diff: Int,
//    val confirmed_diff: Int,
//    val date: String,
//    val recovered_diff: Int


