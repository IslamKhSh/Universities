package com.xische.universities.feature.universitieslist.di

import com.xische.universities.feature.universitieslist.BuildConfig
import com.xische.universities.feature.universitieslist.BuildConfig.BASE_URL
import com.xische.universities.feature.universitieslist.data.networking.UniversitiesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 60L

    /**
     * provide Http Interceptor to be used in logging networking
     *
     * @return an instance of Http Interceptor with custom logging
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply { level = BODY }

    /**
     * Provides the okHttp client for networking
     *
     * @param loggingInterceptor the okHttp logging interceptor
     * @return okHttp client instance
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()

        client
            .connectTimeout(TIMEOUT_SECONDS, SECONDS) // connect timeout
            .readTimeout(TIMEOUT_SECONDS, SECONDS) // socket timeout
            .writeTimeout(TIMEOUT_SECONDS, SECONDS) // request timeout

        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
        }

        return client.build()
    }

    /**
     * Provides the Retrofit instance with [BASE_URL]
     *
     * @param httpClient the okHttp client
     * @return the Retrofit instance
     */
    @Provides
    @Singleton
    fun provideRetrofitInterface(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
            )
            .client(httpClient)
            .build()
    }

    /**
     * Provides the service implementation
     *
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the ApiService implementation.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UniversitiesApiService =
        retrofit.create(UniversitiesApiService::class.java)
}
