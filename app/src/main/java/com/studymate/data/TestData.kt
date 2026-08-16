package com.studymate.data

object TestData {
    fun getLessons() = listOf(
        Lesson(
            name = "Programming basics",
            teacher = "Hijran",
            type = "Lecture",
            location = "409",
            startTime = "8:30",
        ),

        Lesson(
            name = "Web technologies",
            teacher = "Alla",
            type = "Seminar",
            location = "401",
            startTime = "10:15",
        ),

        Lesson(
            name = "Mathematical analysis",
            teacher = "Abbas",
            type = "Lecture",
            location = "202",
            startTime = "12:00",
        ),

        Lesson(
            name = "Analytical geometry",
            teacher = "Adil",
            type = "Seminar",
            location = "311",
            startTime = "13:50",
        ),

        Lesson(
            name = "Physics",
            teacher = "Murad",
            type = "Lecture",
            location = "200",
            startTime = "15:35",
        ),

        Lesson(
            name = "Databases",
            teacher = "Anton",
            type = "Seminar",
            location = "405",
            startTime = "17:20",
        )
    )

    fun getWeek() = listOf(
        Day(day = "Mon", date = "13 may"),
        Day(day = "Tue", date = "14 may"),
        Day(day = "Wed", date = "15 may"),
        Day(day = "Thu", date = "16 may"),
        Day(day = "Fri", date = "17 may",),
        Day(day = "Sat", date = "18 may"),
        Day(day = "Sun", date = "19 may"),
    )
}

