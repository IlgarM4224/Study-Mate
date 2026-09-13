package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import com.studymate.data.Subject
import com.studymate.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Data model representing the UI state for the main subjects list screen.
 *
 * @property listOfSubjects Holds the current list of subjects to display in the UI.
 */
data class SubjectUiState(
    val listOfSubjects: List<Subject>
)

/**
 * ViewModel responsible for managing state and user actions on the subjects list screen.
 */
class SubjectScreenViewModel: ViewModel() {
    // Internal mutable state flow pre-populated with initial subject data
    private val _uiState = MutableStateFlow(
        SubjectUiState(listOfSubjects = TestData.getSubjects())
    )

    // Public read-only StateFlow exposed to the UI layer for observation
    val uiState = _uiState.asStateFlow()

    fun onEditClick() {} // TODO: Implement edit functionality or navigation

    fun addNewSubject() {} // TODO: Implement logic to insert a new subject into _uiState
}