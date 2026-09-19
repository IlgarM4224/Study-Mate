package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import com.studymate.data.Day
import com.studymate.data.TestData
import com.studymate.util.getWeekDays
import com.studymate.util.toDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.DayOfWeek
import java.time.LocalDate


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