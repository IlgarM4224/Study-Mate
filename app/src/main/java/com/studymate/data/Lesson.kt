package com.studymate.data

data class Lesson(
    val name: String,
    val teacher: String,
    val type: String,
    val location: String,
    val startTime: String
)

data class Day(
    val day: String,
    val date: String,
    val selected: Boolean = false
)