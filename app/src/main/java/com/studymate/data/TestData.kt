package com.studymate.data

import java.time.LocalTime

object TestData {
    fun getLessons() = listOf(
        Lesson(
            name = "Programming basics",
            teacher = "Hicran",
            type = "Lecture",
            location = "409",
            startTime = LocalTime.of(8,30),
        ),

        Lesson(
            name = "Web technologies",
            teacher = "Alla",
            type = "Seminar",
            location = "401",
            startTime = LocalTime.of(10,15),
        ),

        Lesson(
            name = "Mathematical analysis",
            teacher = "Abbas",
            type = "Lecture",
            location = "202",
            startTime = LocalTime.of(12,0),
        ),

        Lesson(
            name = "Analytical geometry",
            teacher = "Adil",
            type = "Seminar",
            location = "311",
            startTime = LocalTime.of(13,50),
        ),

        Lesson(
            name = "Physics",
            teacher = "Murad",
            type = "Lecture",
            location = "200",
            startTime = LocalTime.of(15,35),
        ),

        Lesson(
            name = "Databases",
            teacher = "Anton",
            type = "Seminar",
            location = "405",
            startTime = LocalTime.of(17,20),
        )
    )

    fun getWeek() = listOf(
        Day(day = "Mon", date = "13 may"),
        Day(day = "Tue", date = "14 may"),
        Day(day = "Wed", date = "15 may"),
        Day(day = "Thu", date = "16 may"),
        Day(day = "Fri", date = "17 may"),
        Day(day = "Sat", date = "18 may"),
        Day(day = "Sun", date = "19 may"),
    )
}

