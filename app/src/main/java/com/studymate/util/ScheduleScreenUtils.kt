package com.studymate.util

import com.studymate.data.Day
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

/**
 * Computes a 7-day week list (Monday to Sunday) containing the specified [currentDay].
 *
 * @param currentDay Reference date within the desired week.
 * @return List of 7 [Day] models starting from Monday of that week.
 */
fun getWeekDays(currentDay: LocalDate): List<Day> {

    // Find the Monday of the current week
    val currentMonday = currentDay.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

    // Map days 0..6 (Monday through Sunday) to Day domain models
    return (0..6L).map { offset ->
        val date = currentMonday.plusDays(offset)
        date.toDay()
    }
}

/**
 * Extension function converting a [LocalDate] into a [Day] UI data model.
 * Formats day name (e.g., "Mon") and date string (e.g., "14 Sep").
 */
fun LocalDate.toDay(): Day {
    val dayNameFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
    val dateFormatter = DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH)

    return Day(
        day = format(dayNameFormatter).replaceFirstChar { it.uppercase() },
        date = format(dateFormatter),
        dayOfWeek = dayOfWeek
    )
}