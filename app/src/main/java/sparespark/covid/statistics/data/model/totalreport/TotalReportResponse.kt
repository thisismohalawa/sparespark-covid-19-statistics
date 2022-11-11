package sparespark.covid.statistics.data.model.totalreport

import com.google.gson.annotations.SerializedName

data class TotalReportResponse(
    @SerializedName("data")
    val totalReportData: TotalReportData?
)


