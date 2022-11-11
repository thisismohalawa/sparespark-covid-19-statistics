package sparespark.covid.statistics.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import sparespark.covid.statistics.data.model.province.ProvincesResponse
import sparespark.covid.statistics.data.model.region.RegionsResponse
import sparespark.covid.statistics.data.model.provincereport.ReportResponse
import sparespark.covid.statistics.data.model.totalreport.TotalReportResponse

interface RapidApiService {

    @GET("reports/total")
    suspend fun getTotalReport(
        @Query("date") date: String
    ): TotalReportResponse

    @GET("regions")
    suspend fun getRegions(): RegionsResponse

    @GET("provinces")
    suspend fun getProvinces(
        @Query("iso") iso: String
    ): ProvincesResponse

    @GET("reports")
    suspend fun getProvinceReport(
        @Query("iso") iso: String?,
        @Query("region_province") regionProvince: String?,
        @Query("date") data: String
    ): ReportResponse
}