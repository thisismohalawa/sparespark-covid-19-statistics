package sparespark.covid.statistics.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Resource
import sparespark.covid.statistics.data.model.totalreport.TotalReportResponse
import sparespark.covid.statistics.domain.repository.StatisticsRepository
import java.io.IOException
import javax.inject.Inject

class GetTotalReportUseCase @Inject constructor(
    private val repository: StatisticsRepository,
    private val currentDate: String
) {

    operator fun invoke(): Flow<Resource<TotalReportResponse>> = flow {
        try {
            emit(Resource.Loading())

            val totalReport = getTotalReport()
            emit(Resource.Success(totalReport))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: Constants.NO_INTERNET_CONNECTION))
        }
    }

    private suspend fun getTotalReport() = withContext(Dispatchers.IO) {
        delay(Constants.FETCH_DELAY_TIME)
        repository.getTotalReport(currentDate)
    }
}