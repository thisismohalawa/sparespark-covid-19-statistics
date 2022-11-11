package sparespark.covid.statistics.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Resource
import sparespark.covid.statistics.data.model.provincereport.ReportResponse
import sparespark.covid.statistics.domain.repository.StatisticsRepository
import java.io.IOException
import javax.inject.Inject

class GetProvinceReportUseCase @Inject constructor(
    private val repository: StatisticsRepository,
    private val currentDate: String
) {
    operator fun invoke(iso: String, regionProvince: String): Flow<Resource<ReportResponse>> =
        flow {
            try {
                emit(Resource.Loading())
                /*
                * Magic..
                *
                * */
                val report = withContext(Dispatchers.IO) {
                    kotlinx.coroutines.delay(200)
                    return@withContext repository.getProvinceReport(
                        iso,
                        regionProvince,
                        currentDate
                    )
                }
                emit(Resource.Success(report))

            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: Constants.ERROR_OCCURRED))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: Constants.NO_INTERNET_CONNECTION))
            }
        }
}