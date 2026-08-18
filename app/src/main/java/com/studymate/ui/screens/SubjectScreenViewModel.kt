package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import com.studymate.data.Subject
import com.studymate.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SubjectUiState(
    val listOfSubjects: List<Subject>
)
class SubjectScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        SubjectUiState(listOfSubjects = TestData.getSubjects())
    )
    val uiState = _uiState.asStateFlow()

    fun onSubjectClick() {

    }

    fun onEditClick() {}

    fun onFabClick() {}
}