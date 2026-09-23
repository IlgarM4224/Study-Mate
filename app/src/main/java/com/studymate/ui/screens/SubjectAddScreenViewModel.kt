package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SubjectAddScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SubjectAddState())

    val uiState = _uiState.asStateFlow()
}

data class SubjectAddState(
    val subjectName: String = "",
    val teacherLecture: String? = null,
    val teacherSeminar: String? = teacherLecture,
    val creditScore: Int? = null,
    val hours: Int? = null,
)