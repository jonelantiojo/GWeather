package com.jantiojo.gweather.utils.extensions

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun Long.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this * 1000L)
}
