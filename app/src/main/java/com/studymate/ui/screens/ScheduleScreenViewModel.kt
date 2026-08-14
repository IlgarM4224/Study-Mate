package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import com.studymate.data.Day
import com.studymate.data.Lesson
import com.studymate.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScheduleScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        ScheduleUIState(
            lessons = TestData.getLessons(),
            week = TestData.getWeek(),
            weekType = "Current"
        )
    )
    val uiState = _uiState.asStateFlow()

    fun onEditeClick() {}

    fun onCardClick() {}

    fun onNextWeekClick() {}
    fun onCurrentWeekClick() {}

    fun onDayClick() {}

}

data class ScheduleUIState(
    val lessons: List<Lesson>,
    val week: List<Day>,
    val weekType: String,
)