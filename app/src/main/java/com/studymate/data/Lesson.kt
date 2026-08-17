package com.studymate.data

import java.time.LocalTime

data class Lesson(
    val name: String,
    val teacher: String,
    val type: String,
    val location: String,
    val startTime: LocalTime
)

data class Day(
    val day: String,
    val date: String,
)