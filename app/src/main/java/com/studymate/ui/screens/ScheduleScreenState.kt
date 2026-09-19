package com.studymate.ui.screens

import com.studymate.data.Day
import com.studymate.data.Lesson
import java.time.LocalDate

/**
 * Root UI state for the Schedule screen, combining class session cards and week selection state.
 *
 * @property lessonCardUIState Holds the list of scheduled lessons and card interaction state.
 * @property weekUiState Holds the calendar week selection, week type, and active day state.
 */
data class ScheduleUIState(
    val lessonCardUIState: LessonCardUIState,
    val weekUiState: WeekUiState
)

/**
 * Represents the calendar week strip state on the schedule screen.
 *
 * @property week List of 7 [Day] models representing the displayed week (Monday to Sunday).
 * @property weekType Classification of the academic week (e.g., "Upper" / "Lower" or Numerator / Denominator).
 * @property isCurrentWeek `true` if the displayed week matches the real-time calendar week.
 * @property selectedDay Currently highlighted/active day selected by the user.
 * @property currentDay Real-time current date reference (defaults to [LocalDate.now]).
 */
data class WeekUiState(
    val week: List<Day>,
    val weekType: String,
    val isCurrentWeek: Boolean,
    val selectedDay: Day,
    val currentDay: LocalDate = LocalDate.now()
)

/**
 * State container for the lesson cards displayed for a selected day.
 *
 * @property lessons List of scheduled [Lesson] instances for the active day, or `null` if empty/loading.
 * @property clickedCardId ID of the currently expanded or selected lesson card. `null` if none is focused.
 */
data class LessonCardUIState(
    val lessons: List<Lesson>?,
    val clickedCardId: Int? = null
)