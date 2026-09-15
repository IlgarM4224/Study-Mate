package com.studymate.util

import com.studymate.data.Subject
import kotlin.math.pow
import kotlin.math.roundToLong

fun isValidGrade(grade: String): Boolean {
    val numericGrade = grade.toIntOrNull() ?: return false

    return numericGrade in 0..10
}

/**
 * Calculates the attendance score.
 * A penalty proportional to the number of missed classes and total hours is subtracted from 10 points.
 */
fun Subject.calculateAttendanceScore(increment: Int? = null): Float {
    val newCount = if (increment != null) missedLessons + increment else missedLessons
    // Formula: 10 - (missed * 20 / total hours). If hours are null, returns 10 points.
    val attendanceScore = if (hours != null) 10.0f - ( newCount * 20.0f/ hours) else 10f

    return attendanceScore.roundTo(2)
}

/**
 * Calculates the allowed limit of missed classes.
 * The limit is 25% of the total course hours. Since each lesson is 2 hours long,
 * the formula to get the max number of missed lessons is: (hours * 0.25) / 2 = hours / 8.
 */
fun Subject.calculateLimit(): Int? {
    if (hours == null) return null
    return hours/8
}

/**
 * Extension function for Float, allowing it to be rounded to a given number of decimal places.
 */
fun Float.roundTo(decimals: Int): Float {
    if (decimals <= 0) return kotlin.math.round(this)

    val factor = 10.0.pow(decimals)
    return ((this * factor).roundToLong() / factor).toFloat()
}

/**
 * Calculates the average value from a list of grades, protecting against division by zero.
 * The result is rounded to 2 decimal places.
 */
fun List<Int>.averageForLabel(): Float {
    val size = if (isEmpty()) 1f else size.toFloat()

    return (sum() / size).roundTo(2)
}

/**
 * The main formula for calculating the overall (final) score for the subject.
 * Seminar weight is 40%, colloquium weight is 60%.
 * The result is multiplied by 3, then independent work points and attendance score are added.
 */
fun Subject.getOverallScore(): Float {
    val seminarAndColloquium = seminarGradesList.averageForLabel() * 0.4f + colloquiumGradesList.averageForLabel() * 0.6f
    val result = seminarAndColloquium * 3 + independentWorkGradesList.sum() + calculateAttendanceScore()

    return result.roundTo(2)
}