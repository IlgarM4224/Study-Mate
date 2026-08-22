package com.studymate.data

import java.time.DayOfWeek
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
    val dayOfWeek: DayOfWeek = DayOfWeek.MONDAY
)

data class Subject(
    val id: Int,
    val name: String,
    val creditScore: Int? = null,
    val hours: Int? = null,
    val teacher: String? = null,
    val semester: Int? = null,
    val limit: Int? = null,
    val missedLessons: Int? = null,
    val seminarGradesList: List<Int>,
    val colloquiumGradesList: List<Int>,
)
