package com.sb.mediasearch.core.common.instant


import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId.systemDefault
import java.time.format.DateTimeFormatter

fun Instant.formatToDateTimeString(): String {
    val javaInstant = this.toJavaInstant()
    val timeZone = systemDefault()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(timeZone)
    return formatter.format(javaInstant)
}