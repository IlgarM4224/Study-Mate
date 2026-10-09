package com.studymate.ui.screens.subjectEditScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.studymate.data.Subject
import com.studymate.data.SubjectRepository
import com.studymate.ui.screens.subjectAddScreen.SubjectAddState
import com.studymate.ui.screens.subjectAddScreen.SubjectFormViewModel
import com.studymate.ui.screens.subjectAddScreen.ValidInput
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SubjectEditScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val subjectRepository: SubjectRepository
) : SubjectFormViewModel() {

    private val subjectId: Int = checkNotNull(savedStateHandle[SubjectEditDestination.SUBJECT_ID_ARG])
    private var original: Subject? = null

    init {
        viewModelScope.launch {
            val subject = subjectRepository.getSubjectByIdStream(subjectId)
                .filterNotNull()
                .first()

            original = subject
            _uiState.value = subject.toState()
        }
    }

    fun updateSubject() {
        val state = _uiState.value
        val base = original ?: return

        viewModelScope.launch {
            subjectRepository.updateSubject(
                base.copy(
                    name = state.subjectName.value,
                    teacherLecture = state.teacherLecture.value,
                    teacherSeminar = state.teacherSeminar.value,
                    hours = state.hours.value,
                    creditScore = state.creditScore.value,
                )
            )
        }
    }
}

private fun Subject.toState() = SubjectAddState(
    subjectName = ValidInput(name),
    teacherCount = if (teacherLecture == teacherSeminar) 1 else 2,
    teacherLecture = ValidInput(teacherLecture),
    teacherSeminar = ValidInput(teacherSeminar),
    hours = ValidInput(hours),
    creditScore = ValidInput(creditScore),
    isValidInput = true,
)