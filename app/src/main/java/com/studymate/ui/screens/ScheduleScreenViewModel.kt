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

data class ScheduleUIState(
    val lessonCardUIState: LessonCardUIState,
    val weekUiState: WeekUiState
)

data class WeekUiState(
    val week: List<Day>,
    val weekType: String,
    val isCurrentWeek: Boolean,
    val selectedDay: Day,
    val currentDay: LocalDate = LocalDate.now()
)

data class LessonCardUIState(
    val lessons: List<Lesson>,
    val clickedCardId: Int? = null
)

class ScheduleScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        ScheduleUIState(
            lessonCardUIState = LessonCardUIState(lessons = TestData.getLessons()),
            weekUiState = WeekUiState(
                week = getWeekDays(LocalDate.now()),
                weekType = "Upper",
                isCurrentWeek = true,
                selectedDay = LocalDate.now().toDay(),
                currentDay = LocalDate.now()
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    // Card functions
    fun onEditClick() {}

    fun onCardClick(cardId: Int) {
        _uiState.update {
            it.copy(
                lessonCardUIState = it.lessonCardUIState.copy(
                    clickedCardId = if (it.lessonCardUIState.clickedCardId == cardId) null else cardId
                )
            )
        }
    }

    // WeekRowAndType functions
    fun onNextWeekClick() {
        val nextWeekDay = LocalDate.now().plusDays(7)
        val nextWeek = getWeekDays(currentDay = nextWeekDay)

        _uiState.update {
            it.copy(
                weekUiState = it.weekUiState.copy(
                    week = nextWeek,
                    weekType = "Lower",
                    isCurrentWeek = false,
                    selectedDay = nextWeek[0]
                )
            )
        }
    }
    fun onCurrentWeekClick() {
        _uiState.update {
            it.copy(
                weekUiState = it.weekUiState.copy(
                    week = getWeekDays(LocalDate.now()),
                    weekType = "Upper",
                    isCurrentWeek = true,
                    selectedDay = LocalDate.now().toDay()
                )
            )
        }
    }

    fun onDayClick(day: Day) {
        _uiState.update {
            it.copy(
                weekUiState = it.weekUiState.copy(selectedDay = day)
            )
        }
    }

    fun onFabClick () {}

}

private fun getWeekDays(currentDay: LocalDate): List<Day> {
    val currentMonday = currentDay.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

    return (0..6L).map { offset ->
        val date = currentMonday.plusDays(offset)
        date.toDay()
    }
}

private fun LocalDate.toDay(): Day {
    val dayNameFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
    val dateFormatter = DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH)

    return Day(
        day = format(dayNameFormatter).replaceFirstChar { it.uppercase() },
        date = format(dateFormatter),
    )
}