package com.jantiojo.gweather.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import javax.inject.Inject
class JsonSerializerConverter @Inject constructor() {

    fun createKotlinxConverter(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return jsonBuilder.asConverterFactory(contentType)
    }

    inline fun <reified T> encodeDataToString(data: T): String {
        return runCatching {
            jsonBuilder.encodeToString(data)
        }.getOrDefault("")
    }

    inline fun <reified T> decodeDataFromString(encoded: String): T? {
        return runCatching {
            jsonBuilder.decodeFromString<T>(encoded)
        }.getOrNull()
    }

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        val jsonBuilder = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }
}
