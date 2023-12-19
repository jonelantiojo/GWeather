package com.jantiojo.gweather.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * this class in the Interceptor added in application code
 */
class NetworkRequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.header("Accept", "application/json")

        //We can add some network headers here such as Authorization headr and etc
        return chain.proceed(requestBuilder.build())
    }
}
