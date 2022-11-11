package sparespark.covid.statistics.data.model.province

import com.google.gson.annotations.SerializedName

data class ProvincesResponse(
    @SerializedName("data")
    val provincesData: List<ProvincesData>
)
