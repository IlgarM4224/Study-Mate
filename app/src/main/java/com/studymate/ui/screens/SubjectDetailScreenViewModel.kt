package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import com.studymate.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SubjectDetailState(
    val id: Int,
    val name: String,
    val seminarGradesList: List<Int> = emptyList(),
    val colloquiumGradesList: List<Int> = emptyList(),
    val missedLessons: Int? = null,
    val limit: Int? = null,
    val maxScore: Int = 50,
    val overallScore: Float? = null

)
class SubjectDetailScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        SubjectDetailState(
            id = TestData.getSubjects()[0].id,
            name = TestData.getSubjects()[0].name,
            seminarGradesList = TestData.getSubjects()[0].seminarGradesList,
            colloquiumGradesList = TestData.getSubjects()[0].colloquiumGradesList,
            missedLessons = TestData.getSubjects()[0].missedLessons,
            limit = TestData.getSubjects()[0].limit
        )
    )

    val uiState = _uiState.asStateFlow()
}