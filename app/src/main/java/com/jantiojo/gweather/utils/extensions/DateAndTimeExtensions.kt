package com.jantiojo.gweather.utils.extensions

import android.os.Build
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.util.Locale
import java.util.TimeZone


fun Long.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this * 1000L)
}

fun Long.isPastSixAndMoonIcon(): Boolean {
    val epochTime = this * 1000L
    val instant = Instant.ofEpochMilli(epochTime)
    val time = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        LocalTime.ofInstant(instant, ZoneId.systemDefault())
    } else {
        LocalTime.ofNanoOfDay(epochTime)
    }

    return time.isAfter(LocalTime.of(18, 0))
            || time.isBefore(LocalTime.of(5, 0))
}