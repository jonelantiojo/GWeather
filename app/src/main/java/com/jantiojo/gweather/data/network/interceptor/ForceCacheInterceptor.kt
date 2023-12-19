package com.jantiojo.gweather.data.network.interceptor

import android.content.Context
import com.jantiojo.gweather.utils.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * [ForceCacheInterceptor]
 * used also an Interceptor with CacheControl.FORCE_CACHE that can be triggered
 * only if OFFLINE,
 *
 * it will still load cached response data even if there's no internet connection
 *
 * add this ForceCacheInterceptor to the OkHttpClient using addInterceptor.
 */

class ForceCacheInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!NetworkUtils.isInternetAvailable(context)) {
            builder.removeHeader("Pragma")
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}
