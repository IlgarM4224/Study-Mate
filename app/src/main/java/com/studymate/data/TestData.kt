package com.studymate.data

import com.studymate.ui.screens.LessonType
import java.time.DayOfWeek
import java.time.LocalTime

object TestData {
    private val schedule = mapOf(
        DayOfWeek.MONDAY to listOf(getLessons()[0], getLessons()[1], getLessons()[4], getLessons()[5]),
        DayOfWeek.TUESDAY to listOf(getLessons()[2], getLessons()[3]),
        DayOfWeek.WEDNESDAY to listOf(getLessons()[5], getLessons()[1]),
        DayOfWeek.THURSDAY to listOf(getLessons()[3], getLessons()[0], getLessons()[2]),
        DayOfWeek.FRIDAY to emptyList(),
        DayOfWeek.SATURDAY to emptyList(),
        DayOfWeek.SUNDAY to emptyList()
    )
    fun getLessons() = listOf(
        Lesson(
            name = "Programming basics",
            teacher = "Hicran",
            type = LessonType.LECTURES,
            location = "409",
            startTime = LocalTime.of(8,30),
        ),

        Lesson(
            name = "Web technologies",
            teacher = "Alla",
            type = LessonType.SEMINAR,
            location = "401",
            startTime = LocalTime.of(10,15),
        ),

        Lesson(
            name = "Mathematical analysis",
            teacher = "Abbas",
            type = LessonType.LECTURES,
            location = "202",
            startTime = LocalTime.of(12,0),
        ),

        Lesson(
            name = "Analytical geometry",
            teacher = "Adil",
            type = LessonType.SEMINAR,
            location = "311",
            startTime = LocalTime.of(13,50),
        ),

        Lesson(
            name = "Physics",
            teacher = "Murad",
            type = LessonType.LECTURES,
            location = "200",
            startTime = LocalTime.of(15,35),
        ),

        Lesson(
            name = "Databases",
            teacher = "Anton",
            type = LessonType.SEMINAR,
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

    fun getSchedule(dafOfWeek: DayOfWeek) = schedule[dafOfWeek]

    fun getSubjects() = listOf(
        Subject(
            id = 1,
            name = "Programming basics",
            creditScore = 8,
            hours = 75,
            teacherLecture = "Hicran",
            teacherSeminar = "Ramzi",
            semester = 1,
            missedLessons = 4,
            seminarGradesList = emptyList(),
            colloquiumGradesList = emptyList()
        ),

        Subject(
            id = 2,
            name = "Web technologies",
            creditScore = 6,
            hours = 60,
            teacherLecture = "Alla",
            semester = 1,
            missedLessons = 3,
            seminarGradesList = listOf(8,7,9),
            colloquiumGradesList = listOf(9,8,10)
        ),

        Subject(
            id = 3,
            name = "Mathematical analysis",
            creditScore = 6,
            hours = 60,
            teacherLecture = "Abbas",
            semester = 1,
            missedLessons = 1,
            seminarGradesList = listOf(7,7,9),
            colloquiumGradesList = listOf(7,9,8)
        ),

        Subject(
            id = 4,
            name = "Analytical geometry",
            creditScore = 4,
            hours = 45,
            teacherLecture = "Alla",
            semester = 1,
            missedLessons = 0,
            seminarGradesList = listOf(7,7,7),
            colloquiumGradesList = listOf(8,7,7)
        ),

        Subject(
            id = 5,
            name = "Physics",
            creditScore = 4,
            hours = 30,
            teacherLecture = "Murad",
            semester = 1,
            missedLessons = 1,
            seminarGradesList = listOf(7,8,8),
            colloquiumGradesList = listOf(7,9,7)
        ),

        Subject(
            id = 6,
            name = "Databases",
            creditScore = 5,
            hours = 45,
            teacherLecture = "Anton",
            semester = 1,
            missedLessons = 0,
            seminarGradesList = listOf(9,8,10),
            colloquiumGradesList = listOf(9,9,10)
        ),

        Subject(
            id = 7,
            name = "Programming technologies",
            creditScore = 8,
            hours = 90,
            teacherLecture = "Fuad",
            semester = 1,
            missedLessons = 4,
            seminarGradesList = listOf(10,9,10),
            colloquiumGradesList = listOf(9,9,10)
        ),

        Subject(
            id = 8,
            name = "Information technology",
            creditScore = 3,
            hours = 30,
            teacherLecture = "Alla",
            semester = 1,
            missedLessons = 2,
            seminarGradesList = listOf(8,8,8),
            colloquiumGradesList = listOf(8,7,10)
        ),

        Subject(
            id = 9,
            name = "Comprehensive analysis",
            creditScore = 6,
            hours = 60,
            teacherLecture = "Abbas",
            semester = 1,
            missedLessons = 1,
            seminarGradesList = listOf(7,8,8),
            colloquiumGradesList = listOf(8,7,7)
        ),

        Subject(
            id = 10,
            name = "Linear algebra",
            creditScore = 4,
            hours = 45,
            teacherLecture = "Adil",
            semester = 1,
            missedLessons = 0,
            seminarGradesList = emptyList(),
            colloquiumGradesList = listOf(9,6)
        ),
    )
}

