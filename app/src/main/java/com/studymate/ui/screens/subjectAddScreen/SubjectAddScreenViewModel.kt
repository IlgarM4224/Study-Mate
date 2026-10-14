package com.studymate.ui.screens.subjectAddScreen

import androidx.lifecycle.viewModelScope
import com.studymate.data.Subject
import com.studymate.data.SubjectRepository
import kotlinx.coroutines.launch

class SubjectAddScreenViewModel(private val subjectRepository: SubjectRepository): SubjectFormViewModel() {
    fun addSubject() {
        val state = _uiState.value

        viewModelScope.launch {
            subjectRepository.addSubject(
                Subject(
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