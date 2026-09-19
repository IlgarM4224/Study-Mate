package com.studymate.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.studymate.data.GradeType
import com.studymate.data.Subject
import com.studymate.data.TestData
import com.studymate.util.averageForLabel
import com.studymate.util.calculateAttendanceScore
import com.studymate.util.calculateLimit
import com.studymate.util.getOverallScore
import com.studymate.util.isValidGrade
import com.studymate.util.replaceAt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SubjectDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    // Extract the subject ID from the passed navigation arguments
    private val subjectId: Int = checkNotNull(savedStateHandle[SubjectDetailDestination.SUBJECT_ID_ARG])
    private val initialSubject = TestData.getSubjects()[subjectId - 1]
    private val _uiState = MutableStateFlow(createInitialState(initialSubject))
    val uiState = _uiState.asStateFlow()


    // UI actions
    fun onMoreClick() { /* TODO: Implement logic for clicking the ":" button */}

    fun addMissed() = updateMissed(1) // Increments the missed classes counter by 1
    fun removeMissed() = updateMissed(-1) // Decrements the missed classes counter by 1

    /**
     * Opens the BottomSheet to add a grade of a specific type.
     */
    fun onAddClick(type: GradeType) {
        _uiState.update {
            it.copy(
                sheetState = BottomSheetState(type = type),
                activeGradeType = type
            )
        }
    }

    fun onGradeChange(newGrade: String) {
        val isValid = isValidGrade(newGrade) && newGrade.isNotEmpty()

        _uiState.update {
            it.copy(sheetState = it.sheetState.copy(grade = newGrade, isEntryValid = isValid))
        }
    }

    /**
     * Closes the BottomSheet, resetting the active grade type.
     */
    fun onDismissRequest() {
        _uiState.update { it.copy(activeGradeType = GradeType.NONE) }
    }

    /**
     * Routes the grade addition to the appropriate function based on the type.
     */
    fun addGrade(type: GradeType, grade: Int) {
        when (type) {
            GradeType.SEMINAR -> addGradeToList { it.copy(seminarGradesList = it.seminarGradesList + grade) }
            GradeType.COLLOQUIUM -> addGradeToList { it.copy(colloquiumGradesList = it.colloquiumGradesList + grade) }
            GradeType.INDEPENDENT_WORK -> addGradeToList { it.copy(independentWorkGradesList = it.independentWorkGradesList + grade) }
            else -> Unit
        }
        onDismissRequest()
    }

    fun changeGrade(index: Int, grade: Int, type: GradeType) {
        when (type) {
            GradeType.SEMINAR -> addGradeToList { it.copy(seminarGradesList = it.seminarGradesList.replaceAt(index, grade)) }
            GradeType.COLLOQUIUM -> addGradeToList { it.copy(colloquiumGradesList = it.colloquiumGradesList.replaceAt(index, grade)) }
            GradeType.INDEPENDENT_WORK -> addGradeToList { it.copy(independentWorkGradesList = it.independentWorkGradesList.replaceAt(index, grade)) }
            else -> Unit
        }
        onDismissRequest()
    }

    fun onGradeClick(type: GradeType, id: Int) {
        val subject = _uiState.value.subject
        val gradeForChange = when(type) {
            GradeType.SEMINAR -> subject.seminarGradesList
            GradeType.COLLOQUIUM -> subject.colloquiumGradesList
            GradeType.INDEPENDENT_WORK -> subject.independentWorkGradesList
            else -> emptyList()
        }

        _uiState.update { state ->
            state.copy(
                sheetState = BottomSheetState(
                    type = type,
                    grade = "${gradeForChange[id]}",
                    selectedGradeIndex = id,
                    isEntryValid = true
                ),
                activeGradeType = type
            )
        }
    }

    /**
     * Toggles the flag for showing additional information (expandable list).
     */
    fun onArrowClick() {
        _uiState.update { it.copy( showMore = !it.showMore ) }
    }

    // private helpers

    private fun createInitialState(subject: Subject) = SubjectDetailState(
        subject = subject,
        averageSeminar = subject.seminarGradesList.averageForLabel(),
        averageColloquium = subject.colloquiumGradesList.averageForLabel(),
        independentWorkSum = subject.independentWorkGradesList.sum(),
        attendanceScore = subject.calculateAttendanceScore(),
        limit = subject.calculateLimit(),
        overallScore = subject.getOverallScore()
    )

    private fun addGradeToList(transform: (Subject) -> Subject) {
        _uiState.update { createInitialState(transform(it.subject)) }
    }

    private fun updateMissed(delta: Int) {
        _uiState.update { state ->
            val newMissed = (state.subject.missedLessons + delta).coerceAtLeast(0)
            val newSubject = state.subject.copy(missedLessons = newMissed)
            state.copy(
                subject = newSubject,
                attendanceScore = newSubject.calculateAttendanceScore(),
                overallScore = newSubject.getOverallScore()
            )
        }
    }
}