package sparespark.covid.statistics.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sparespark.covid.statistics.core.Constants.RAPID_BASE_URL
import sparespark.covid.statistics.core.SECRET_RAPID_API_KEY
import sparespark.covid.statistics.data.remote.RapidApiService
import sparespark.covid.statistics.data.repository.StatisticsRepositoryImpl
import sparespark.covid.statistics.domain.repository.StatisticsRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRapidApiService(): RapidApiService {
        val requestInterceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header("X-RapidAPI-Key", SECRET_RAPID_API_KEY)
                .build()
            return@Interceptor chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(RAPID_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RapidApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideStaticsRepository(
        apiService: RapidApiService
    ): StatisticsRepository {
        return StatisticsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTwoDaysAgoDate(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -2)
        return SimpleDateFormat("yyyy-MM-dd").format(cal.time)
    }
}