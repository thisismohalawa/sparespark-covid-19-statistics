package sparespark.covid.statistics.data.model.region

import com.google.gson.annotations.SerializedName

data class RegionsResponse(
    @SerializedName("data")
    val regionsData: List<RegionsData>
)

