package com.thekorovay.testmosmetro.tools

import android.widget.TextView
import org.ocpsoft.prettytime.PrettyTime
import java.time.Instant
import java.time.ZoneId
import java.util.*

fun TextView.setPublishedDateFrom(stamp: Long) {
    val then = Instant.ofEpochMilli(stamp).atZone(ZoneId.systemDefault()).toInstant()
    text = PrettyTime().format(Date.from(then))
}