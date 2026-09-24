package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SubjectAddScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SubjectAddState())

    val uiState = _uiState.asStateFlow()

    fun changeTeacherCount(index: Int) {
        _uiState.update { state ->
            val count = if (index == 0) 1 else 2
            state.copy(teacherCount = count)
        }
    }
}

data class SubjectAddState(
    val subjectName: String = "",
    val teacherCount: Int = 1,
    val teacherLecture: String? = null,
    val teacherSeminar: String? = teacherLecture,
    val creditScore: Int? = null,
    val hours: Int? = null,
)