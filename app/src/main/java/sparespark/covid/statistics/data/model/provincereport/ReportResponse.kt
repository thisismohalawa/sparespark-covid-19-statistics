package sparespark.covid.statistics.data.model.provincereport

import com.google.gson.annotations.SerializedName

data class ReportResponse(
    @SerializedName("data")
    val reportData: List<ReportData>
)

