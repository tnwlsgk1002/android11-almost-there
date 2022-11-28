package com.woory.presentation.background.util

import android.content.Intent
import com.woory.presentation.model.PromiseAlarm
import com.woory.presentation.model.asAlarmState
import com.woory.presentation.util.TimeConverter.asMillis
import com.woory.presentation.util.TimeConverter.asOffsetDateTime

fun Intent.putPromiseAlarm(promiseAlarm: PromiseAlarm) {
    this.putExtra("alarmCode", promiseAlarm.alarmCode)
    this.putExtra("promiseCode", promiseAlarm.promiseCode)
    this.putExtra("state", promiseAlarm.state.state)
    this.putExtra("startTime", promiseAlarm.startTime.asMillis())
    this.putExtra("endTime", promiseAlarm.endTime.asMillis())
}

fun Intent.asPromiseAlarm(): PromiseAlarm {
    val extras = this.extras ?: throw IllegalArgumentException("is extras null")
    val alarmCode = extras.getInt("alarmCode")
    val promiseCode =
        extras.getString("promiseCode") ?: throw IllegalArgumentException("is promise code null")
    val state = extras.getString("state") ?: throw IllegalArgumentException("is state null")
    val startTime = extras.getLong("startTime")
    val endTime = extras.getLong("endTime")

    return PromiseAlarm(
        alarmCode = alarmCode,
        promiseCode = promiseCode,
        state = state.asAlarmState(),
        startTime = startTime.asOffsetDateTime(),
        endTime = endTime.asOffsetDateTime()
    )
}