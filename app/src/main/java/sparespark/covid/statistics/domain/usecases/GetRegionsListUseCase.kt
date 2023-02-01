package sparespark.covid.statistics.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Resource
import sparespark.covid.statistics.data.model.region.RegionsResponse
import sparespark.covid.statistics.domain.repository.StatisticsRepository
import java.io.IOException
import javax.inject.Inject

class GetRegionsListUseCase @Inject constructor(
    private val repository: StatisticsRepository
) {
    operator fun invoke(): Flow<Resource<RegionsResponse>> = flow {
        try {
            emit(Resource.Loading())
            /*
            * Emitting multiple data values over period time.
            *
            * */
            val reports = getRegions()
            emit(Resource.Success(reports))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: Constants.NO_INTERNET_CONNECTION))
        }
    }

    private suspend fun getRegions() = withContext(Dispatchers.IO) {
        delay(Constants.FETCH_DELAY_TIME)
        repository.getRegions()
    }
}