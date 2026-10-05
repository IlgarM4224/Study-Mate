package com.studymate.ui.screens

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studymate.data.LessonType
import com.studymate.data.Subject
import com.studymate.data.Subject1
import com.studymate.data.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SubjectAddScreenViewModel(private val subjectRepository: SubjectRepository): ViewModel() {
    private val _uiState = MutableStateFlow(SubjectAddState())

    val uiState = _uiState.asStateFlow()

    fun changeTeacherCount(index: Int) {
        _uiState.update { state ->
            val count = if (index == 0) 1 else 2
            state.copy(teacherCount = count)
        }
    }

    fun subjectNameChange(name: String) {
        _uiState.update { state ->
            val isValid = isValidString(name)
            state.copy(subjectName = ValidInput(name, isValid), isValidInput = isValid)
        }

        updateIsValidInput()
    }

    fun teacherNameChange(name: String, type: LessonType) {
        _uiState.update { state ->
            val isValid = isValidString(name, 20) || name.isEmpty()

            if (state.teacherCount == 1) {
                state.copy(
                    teacherLecture = ValidInput(name, isValid),
                    teacherSeminar = ValidInput(name, isValid),
                )
            } else {
                state.copy(
                    teacherLecture = if(type == LessonType.LECTURES) ValidInput(name, isValid) else state.teacherLecture,
                    teacherSeminar = if(type == LessonType.SEMINAR) ValidInput(name, isValid) else state.teacherSeminar
                )
            }
        }

        updateIsValidInput()
    }

    fun metricInputChange(metric: String, max: Int, isHours: Boolean) {
        _uiState.update { state ->
            val isValid = metric.isDigitsOnly() && (metric.toIntOrNull() ?: 0) <= max

            if (isHours) {
                state.copy(hours = ValidInput(metric.toIntOrNull(), isValid))
            } else state.copy(creditScore = ValidInput(metric.toIntOrNull(), isValid))
        }

        updateIsValidInput()
    }

    fun addSubject() {
        val state = _uiState.value

        viewModelScope.launch {
            subjectRepository.addSubject(
                Subject1(
                    name = state.subjectName.value,
                    teacherLecture = state.teacherLecture.value,
                    teacherSeminar = state.teacherSeminar.value,
                    hours = state.hours.value,
                    creditScore = state.creditScore.value,
                )
            )
        }
    }

    private fun updateIsValidInput() = _uiState.update { it.copy(isValidInput = canBeSaved(it)) }

}

data class SubjectAddState(
    val subjectName: ValidInput<String> = ValidInput(""),
    val teacherCount: Int = 1,
    val teacherLecture: ValidInput<String?> = ValidInput(null),
    val teacherSeminar: ValidInput<String?> = ValidInput(null),
    val creditScore: ValidInput<Int?> = ValidInput(null),
    val hours: ValidInput<Int?> = ValidInput(null),
    val isValidInput: Boolean = subjectName.value.isNotBlank(),
    val maxHoursValue: Int = 150,
    val maxCreditScore: Int = 15
)

data class ValidInput<T>(
    val value: T,
    val isValid: Boolean = true
)

private fun isValidString(input: String, maxLength: Int = 50): Boolean {
    return input.isNotEmpty() &&
            input.length <= maxLength &&
            !input.startsWith(" ") &&
            !input.first().isDigit()
}

private fun canBeSaved(state: SubjectAddState): Boolean {
    return isValidString(state.subjectName.value) &&
            state.hours.isValid &&
            state.creditScore.isValid &&
            state.teacherLecture.isValid &&
            state.teacherSeminar.isValid
}