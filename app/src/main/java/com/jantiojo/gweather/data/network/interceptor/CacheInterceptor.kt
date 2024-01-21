package com.jantiojo.gweather.data.network.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * [CacheInterceptor]
 * used an Interceptor with Cache-Control builder that contains
 * Cache-Control Header
 *
 * For the very first time, it will get the data from the server and it will cache the HTTP response on the client
 * Then, if we make the same API call again, it will return the data from the cache instantly
 *
 * Things become easier when we already have the Cache-Control header enabled from the server,
 * then OkHttp will respect that header and cache the response for a specific time
 * that is being sent from the server.
 *
 * But what if the Cache-Control is not enabled from the server?
 * We can still cache the response from OkHttp Client using Interceptor.
 * [https://medium.com/@amitshekhar/caching-with-okhttp-interceptor-and-retrofit-180faa853ff5]
 *
 * [max-age] which tells how long the response can be cached
 *
 * add this CacheInterceptor to the OkHttpClient using addNetworkInterceptor.
 */
class CacheInterceptor @Inject constructor() : Interceptor {
    companion object {
        const val CACHE_CONTROL_HEADER = "Cache-Control"
        const val CACHE_CONTROL_NO_CACHE = "no-cache"
        const val CACHE_AGE_IN_SECONDS = 15
        const val METHOD_GET = "GET"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        /**
         * We will only enable caching under two conditions:
         * 1. the request is a get request
         * 2. the header doesn't already have a header for cache_control
         * The idea here is to honor whatever policy the server has for caching
         * and only add caching if there's nothing set by the server
         *
         */
        val response: Response = chain.proceed(chain.request())

        with(chain.request()) {
            if (method != METHOD_GET || header(CACHE_CONTROL_HEADER) != null) {
                return response
            }
        }
        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_AGE_IN_SECONDS, TimeUnit.SECONDS)
            .build()
        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader(CACHE_CONTROL_HEADER)
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}
