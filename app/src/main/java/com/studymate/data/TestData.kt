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

    fun getSubjects() = listOf(
        Subject(
            name = "Programming basics",
            creditScore = 8,
            hours = 75,
            teacher = "Hicran",
            semester = 1,
            limit = 12,
            missedLessons = 4
        ),

        Subject(
            name = "Web technologies",
            creditScore = 6,
            hours = 60,
            teacher = "Alla",
            semester = 1,
            limit = 10,
            missedLessons = 3
        ),

        Subject(
            name = "Mathematical analysis",
            creditScore = 6,
            hours = 60,
            teacher = "Abbas",
            semester = 1,
            limit = 10,
            missedLessons = 1
        ),

        Subject(
            name = "Analytical geometry",
            creditScore = 4,
            hours = 45,
            teacher = "Alla",
            semester = 1,
            limit = 8,
            missedLessons = 0
        ),

        Subject(
            name = "Physics",
            creditScore = 4,
            hours = 30,
            teacher = "Murad",
            semester = 1,
            limit = 30,
            missedLessons = 1
        ),

        Subject(
            name = "Databases",
            creditScore = 5,
            hours = 45,
            teacher = "Anton",
            semester = 1,
            limit = 4,
            missedLessons = 0
        ),

        Subject(
            name = "Programming technologies",
            creditScore = 8,
            hours = 90,
            teacher = "Fuad",
            semester = 1,
            limit = 15,
            missedLessons = 4
        ),

        Subject(
            name = "Information technology",
            creditScore = 3,
            hours = 30,
            teacher = "Alla",
            semester = 1,
            limit = 3,
            missedLessons = 2
        ),

        Subject(
            name = "Comprehensive analysis",
            creditScore = 6,
            hours = 60,
            teacher = "Abbas",
            semester = 1,
            limit = 10,
            missedLessons = 1
        ),

        Subject(
            name = "Linear algebra",
            creditScore = 4,
            hours = 45,
            teacher = "Adil",
            semester = 1,
            limit = 6,
            missedLessons = 0
        ),

    )
}

