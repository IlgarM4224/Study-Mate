package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import com.studymate.data.Day
import com.studymate.data.Lesson
import com.studymate.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

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

/**
 * ViewModel managing schedule display, card selection, and week/day navigation.
 */
class ScheduleScreenViewModel: ViewModel() {

    // Internal mutable state initialized with today's schedule and the current week's days
    private val _uiState = MutableStateFlow(
        ScheduleUIState(
            lessonCardUIState = LessonCardUIState(lessons = TestData.getSchedule(LocalDate.now().dayOfWeek)),
            weekUiState = WeekUiState(
                week = getWeekDays(LocalDate.now()),
                weekType = "Upper",
                isCurrentWeek = true,
                selectedDay = LocalDate.now().toDay(),
                currentDay = LocalDate.now()
            )
        )
    )

    // Read-only StateFlow exposed to the UI layer
    val uiState = _uiState.asStateFlow()

    /**
     * Handles the edit action for modifying the schedule.
     */
    fun onEditClick() {
        // TODO: Implement schedule editing logic
    }

    /**
     * Toggles expansion/selection state for a specific lesson card.
     * If the clicked card is already selected, it collapses (resets to null).
     *
     * @param cardId Unique identifier of the clicked lesson card.
     */
    fun onCardClick(cardId: Int) {
        _uiState.update {
            it.copy(
                lessonCardUIState = it.lessonCardUIState.copy(
                    clickedCardId = if (it.lessonCardUIState.clickedCardId == cardId) null else cardId
                )
            )
        }
    }

    /**
     * Navigates to the next academic week ("Lower" week).
     * Resets active selection to Monday of the next week and fetches its schedule.
     */
    fun onNextWeekClick() {
        val nextWeekDay = LocalDate.now().plusDays(7)
        val nextWeek = getWeekDays(currentDay = nextWeekDay)

        _uiState.update {
            it.copy(
                lessonCardUIState = LessonCardUIState(
                    lessons = TestData.getSchedule(dafOfWeek = DayOfWeek.MONDAY),
                    clickedCardId = null
                ),
                weekUiState = it.weekUiState.copy(
                    week = nextWeek,
                    weekType = "Lower",
                    isCurrentWeek = false,
                    selectedDay = nextWeek[0]   // Select Monday of next week
                )
            )
        }
    }

    /**
     * Resets the schedule view back to the current real-time week ("Upper" week) and today's date.
     */
    fun onCurrentWeekClick() {
        _uiState.update {
            it.copy(
                lessonCardUIState = LessonCardUIState(
                    lessons = TestData.getSchedule(dafOfWeek = LocalDate.now().dayOfWeek),
                    clickedCardId = null
                ),
                weekUiState = it.weekUiState.copy(
                    week = getWeekDays(LocalDate.now()),
                    weekType = "Upper",
                    isCurrentWeek = true,
                    selectedDay = LocalDate.now().toDay()
                )
            )
        }
    }

    /**
     * Updates the active selected day and loads the corresponding lesson list.
     *
     * @param day The [Day] item selected by the user in the week strip.
     */
    fun onDayClick(day: Day) {
        _uiState.update {
            it.copy(
                lessonCardUIState = LessonCardUIState(
                    lessons = TestData.getSchedule(dafOfWeek = day.dayOfWeek),
                    clickedCardId = null
                ),
                weekUiState = it.weekUiState.copy(selectedDay = day)
            )
        }
    }
}

/**
 * Computes a 7-day week list (Monday to Sunday) containing the specified [currentDay].
 *
 * @param currentDay Reference date within the desired week.
 * @return List of 7 [Day] models starting from Monday of that week.
 */
private fun getWeekDays(currentDay: LocalDate): List<Day> {

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
private fun LocalDate.toDay(): Day {
    val dayNameFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
    val dateFormatter = DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH)

    return Day(
        day = format(dayNameFormatter).replaceFirstChar { it.uppercase() },
        date = format(dateFormatter),
        dayOfWeek = dayOfWeek
    )
}