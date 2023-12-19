package com.jantiojo.gweather.di

import android.content.Context
import com.jantiojo.gweather.BuildConfig
import com.jantiojo.gweather.data.network.api.GWeatherApiService
import com.jantiojo.gweather.data.network.interceptor.CacheInterceptor
import com.jantiojo.gweather.data.network.interceptor.ForceCacheInterceptor
import com.jantiojo.gweather.data.network.interceptor.NetworkRequestInterceptor
import com.jantiojo.gweather.utils.AppConstant
import com.jantiojo.gweather.utils.JsonSerializerConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val NETWORK_CACHE_SIZE = (10 * 1024 * 1024).toLong() // 10MB
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT_SECONDS = 30L

    /**
     * this will provide the logging/printing of request and response output
     *
     * print the network logs if app is in DEBUG Mode
     * otherwise, nothing will be printed
     * @see provideOkHttpClient
     *
     * @return HttpLoggingInterceptor
     */
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }


    /**
     * this will provide Caching of network response
     *
     * @see CacheInterceptor
     * @return Interceptor
     */
    @Provides
    @Singleton
    fun providesCacheInterceptor(): Interceptor = CacheInterceptor()

    /**
     * this will provide Caching of network response offline
     *
     * @see ForceCacheInterceptor
     * @return Interceptor
     */
    @Provides
    @Singleton
    fun providesForceCacheInterceptor(context: Context): Interceptor =
        ForceCacheInterceptor(context)

    /**
     * this will provide the AuthenticatedInterceptor
     *
     * @param httpLoggingInterceptor - logging/printing the request and response output
     * @param networkRequestInterceptor - Interceptor for all the API request
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    @Named("weatherApiService")
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        forceCacheInterceptor: ForceCacheInterceptor,
        cacheInterceptor: CacheInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkRequestInterceptor: NetworkRequestInterceptor
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

        okHttpBuilder.cache(Cache(context.cacheDir, NETWORK_CACHE_SIZE))
        okHttpBuilder.addInterceptor(networkRequestInterceptor)
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        okHttpBuilder.addNetworkInterceptor(cacheInterceptor)
        okHttpBuilder.addInterceptor(forceCacheInterceptor)

        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    internal fun providesWeatherApiService(
        @Named("weatherApiService") okHttpClient: OkHttpClient,
        jsonConverter: JsonSerializerConverter
    ): GWeatherApiService {
        val converterFactory = jsonConverter.createKotlinxConverter()
        val retrofit =
            Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(converterFactory).client(okHttpClient).build()
        return retrofit.create(GWeatherApiService::class.java)
    }
}
