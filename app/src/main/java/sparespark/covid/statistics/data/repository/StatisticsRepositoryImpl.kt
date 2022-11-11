package sparespark.covid.statistics.data.repository

import sparespark.covid.statistics.data.model.province.ProvincesResponse
import sparespark.covid.statistics.data.model.region.RegionsResponse
import sparespark.covid.statistics.data.model.provincereport.ReportResponse
import sparespark.covid.statistics.data.model.totalreport.TotalReportResponse
import sparespark.covid.statistics.data.remote.RapidApiService
import sparespark.covid.statistics.domain.repository.StatisticsRepository
import javax.inject.Inject

class StatisticsRepositoryImpl @Inject constructor(
    private val apiService: RapidApiService
) : StatisticsRepository {


    override suspend fun getTotalReport(date: String): TotalReportResponse {
        return apiService.getTotalReport(date)
    }

    override suspend fun getRegions(): RegionsResponse {
        return apiService.getRegions()
    }

    override suspend fun getProvinces(iso: String): ProvincesResponse {
        return apiService.getProvinces(iso)
    }

    override suspend fun getProvinceReport(
        iso: String?,
        regionProvince: String?,
        date: String
    ): ReportResponse {
        return apiService.getProvinceReport(iso, regionProvince, date)
    }
}