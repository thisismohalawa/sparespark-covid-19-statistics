package sparespark.covid.statistics.domain.repository

import sparespark.covid.statistics.data.model.province.ProvincesResponse
import sparespark.covid.statistics.data.model.region.RegionsResponse
import sparespark.covid.statistics.data.model.provincereport.ReportResponse
import sparespark.covid.statistics.data.model.totalreport.TotalReportResponse

interface StatisticsRepository {

    suspend fun getTotalReport(date: String): TotalReportResponse
    suspend fun getRegions(): RegionsResponse
    suspend fun getProvinces(iso: String): ProvincesResponse
    suspend fun getProvinceReport(iso: String?, regionProvince: String?, date: String): ReportResponse

}