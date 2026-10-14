package com.studymate.ui.screens.subjectDetailScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studymate.data.GradeType
import com.studymate.data.Subject
import com.studymate.data.SubjectRepository
import com.studymate.util.averageForLabel
import com.studymate.util.calculateAttendanceScore
import com.studymate.util.calculateLimit
import com.studymate.util.getOverallScore
import com.studymate.util.isValidGrade
import com.studymate.util.replaceAt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SubjectDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val subjectRepository: SubjectRepository
): ViewModel() {

    // Extract the subject ID from the passed navigation arguments
    private val subjectId: Int = checkNotNull(savedStateHandle[SubjectDetailDestination.SUBJECT_ID_ARG])
    private val _uiState = MutableStateFlow(MutableSubjectDetailState())
    val uiState: StateFlow<SubjectDetailState> = combine(
        subjectRepository.getSubjectByIdStream(subjectId).filterNotNull(),
        _uiState
    ) { subject, currentState ->
        SubjectDetailState(
            subject = subject,
            state = currentState.copy(
                averageSeminar = subject.seminarGradesList.averageForLabel(),
                averageColloquium = subject.colloquiumGradesList.averageForLabel(),
                independentWorkSum = subject.independentWorkGradesList.sum(),
                attendanceScore = subject.calculateAttendanceScore(),
                limit = subject.calculateLimit(),
                overallScore = subject.getOverallScore()
            ),
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SubjectDetailState(
            Subject(subjectId, ""),
            state = MutableSubjectDetailState()
        )
    )

    // UI actions
    fun onMoreClick() {
        _uiState.update { it.copy(showMoreActions = true) }
    }

    fun onMoreDismissRequest() {
        _uiState.update { it.copy(showMoreActions = false) }
    }

    fun deleteSubject() {
        _uiState.update { it.copy(showMoreActions = false) }
        viewModelScope.launch {
            subjectRepository.deleteSubject(uiState.value.subject)
        }
    }

    fun onEditClick() {
        _uiState.update { it.copy(showMoreActions = false) }
    }

    fun addMissed() = updateMissed(1) // Increments the missed classes counter by 1
    fun removeMissed() = updateMissed(-1) // Decrements the missed classes counter by 1

    /** Opens the BottomSheet to add a grade of a specific type */
    fun onAddClick(type: GradeType) {
        _uiState.update {
            it.copy(
                sheetState = BottomSheetState(type = type),
                activeGradeType = type
            )
        }
    }


    fun onValueChange(newGrade: String) {
        val state = _uiState.value
        val maxScore = when(state.activeGradeType) {
            GradeType.INDEPENDENT_WORK -> if (state.sheetState.selectedGradeIndex == null) 10 - state.independentWorkSum else 10
            else -> 10
        }

        val isValid = isValidGrade(newGrade, maxScore = maxScore) && newGrade.isNotEmpty()

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

    /** Routes the grade addition to the appropriate function based on the type */
    fun addGrade(type: GradeType, grade: Int) {
        when (type) {
            GradeType.SEMINAR -> updateGradesInDb { it.copy(seminarGradesList = it.seminarGradesList + grade) }
            GradeType.COLLOQUIUM -> updateGradesInDb { it.copy(colloquiumGradesList = it.colloquiumGradesList + grade) }
            GradeType.INDEPENDENT_WORK -> updateGradesInDb { it.copy(independentWorkGradesList = it.independentWorkGradesList + grade) }
            else -> Unit
        }
        onDismissRequest()
    }

    fun changeGrade(index: Int, grade: Int, type: GradeType) {
        _uiState.update { it.copy(sheetState = BottomSheetState()) }

        when (type) {
            GradeType.SEMINAR -> updateGradesInDb { it.copy(seminarGradesList = it.seminarGradesList.replaceAt(index, grade)) }
            GradeType.COLLOQUIUM -> updateGradesInDb { it.copy(colloquiumGradesList = it.colloquiumGradesList.replaceAt(index, grade)) }
            GradeType.INDEPENDENT_WORK -> updateGradesInDb { it.copy(independentWorkGradesList = it.independentWorkGradesList.replaceAt(index, grade)) }
            else -> Unit
        }
        onDismissRequest()
    }

    fun onGradeClick(type: GradeType, id: Int) {
        _uiState.update { state ->
            state.copy(
                sheetState = state.sheetState.copy(selectedGradeIndex = id, type = type),
            )
        }
    }

    fun onGradeDismissRequest() {
        _uiState.update {
            state -> state.copy(
                sheetState = BottomSheetState(),
            )
        }
    }

    fun onGradeEditClick(type: GradeType, id: Int) {
        val subject = uiState.value.subject
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

    fun onGradeDeleteClick(type: GradeType, index: Int) {
        val state = uiState.value.subject

        when (type) {
            GradeType.SEMINAR -> {
                val newList = state.seminarGradesList.toMutableList().apply { removeAt(index) }
                updateGradesInDb { it.copy(seminarGradesList = newList) }
            }
            GradeType.COLLOQUIUM -> {
                val newList = state.colloquiumGradesList.toMutableList().apply { removeAt(index) }
                updateGradesInDb { it.copy(colloquiumGradesList = newList) }
            }
            GradeType.INDEPENDENT_WORK -> {
                val newList = state.independentWorkGradesList.toMutableList().apply { removeAt(index) }
                updateGradesInDb { it.copy(independentWorkGradesList = newList) }
            }
            else -> Unit
        }
    }

    /** Toggles the flag for showing additional information (expandable list) */
    fun onArrowClick() {
        _uiState.update { it.copy( showMore = !it.showMore ) }
    }

    // private helpers

    private fun updateMissed(delta: Int) {
        val currentSubject = uiState.value.subject
        val newMissed = (currentSubject.missedLessons + delta).coerceAtLeast(0)
        val updatedSubject = currentSubject.copy(missedLessons = newMissed)

        viewModelScope.launch {
            subjectRepository.updateSubject(updatedSubject)
        }
    }

    private fun updateGradesInDb(transform: (Subject) -> Subject) {
        val currentSubject = uiState.value.subject
        val updatedSubject = transform(currentSubject)

        viewModelScope.launch {
            subjectRepository.updateSubject(updatedSubject)
        }
    }
}