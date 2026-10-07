package com.studymate.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studymate.data.Subject
import com.studymate.data.SubjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Data model representing the UI state for the main subjects list screen.
 *
 * @property listOfSubjects Holds the current list of subjects to display in the UI.
 */
data class SubjectUiState( val listOfSubjects: List<Subject> )

/**
 * ViewModel responsible for managing state and user actions on the subjects list screen.
 */
class SubjectScreenViewModel(subjectRepository: SubjectRepository): ViewModel() {

    val uiState: StateFlow<SubjectUiState> =
        subjectRepository.getAllSubjectsStream().map { SubjectUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = SubjectUiState(emptyList())
            )

    fun onEditClick() {} // TODO: Implement edit functionality or navigation

    fun addNewSubject() {} // TODO: Implement logic to insert a new subject into _uiState

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}