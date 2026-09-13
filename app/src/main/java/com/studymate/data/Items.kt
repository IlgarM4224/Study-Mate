package com.studymate.data

import com.studymate.ui.screens.LessonType
import java.time.DayOfWeek
import java.time.LocalTime

/**
 * Represents a single scheduled class session within the timetable.
 *
 * @property name Title of the lesson or course.
 * @property teacher Name of the instructor or professor conducting the class.
 * @property type Category of the lesson (e.g., [LessonType.SEMINAR] or [LessonType.LECTURES]).
 * @property location Room number, building, or virtual link where the class takes place.
 * @property startTime Scheduled starting time of the lesson.
 */
data class Lesson(
    val name: String,
    val teacher: String,
    val type: LessonType,
    val location: String,
    val startTime: LocalTime
)

/**
 * Data model representing a specific day in the schedule or calendar view.
 *
 * @property day Short string representation of the day (e.g.,label "Mon").
 * @property date Formatted full date string (e.g., "14 Sept").
 * @property dayOfWeek Standard [DayOfWeek] enum value representing the day (defaults to [DayOfWeek.MONDAY]).
 */
data class Day(
    val day: String,
    val date: String,
    val dayOfWeek: DayOfWeek = DayOfWeek.MONDAY
)

/**
 * Data model representing an academic subject, including its metadata,
 * attendance records, and grade history.
 *
 * @property id Unique identifier for the subject.
 * @property name Title/Name of the subject.
 * @property creditScore Academic credit weight (e.g., ECTS credits).
 * @property hours Total number of academic hours allocated for the course.
 * @property teacherLecture Name of the lecturer.
 * @property teacherSeminar Name of the seminar instructor (defaults to [teacherLecture] if not specified).
 * @property semester Semester number in which the subject is taught.
 * @property missedLessons Total count of missed classes (defaults to 0).
 * @property seminarGradesList List of grades earned during seminar sessions.
 * @property colloquiumGradesList List of grades earned in colloquiums/midterms.
 * @property independentWorkGradesList List of grades earned for independent assignments.
 */
data class Subject(
    val id: Int,
    val name: String,
    val creditScore: Int? = null,
    val hours: Int? = null,
    val teacherLecture: String? = null,
    val teacherSeminar: String? = teacherLecture,
    val semester: Int? = null,
    val missedLessons: Int = 0,
    val seminarGradesList: List<Int>,
    val colloquiumGradesList: List<Int>,
    val independentWorkGradesList: List<Int> = emptyList()
)
