package com.studymate.ui.screens.subjectAddScreen

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.studymate.data.LessonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * The general logic of the subject form
 * SubjectAddScreenViewModel and SubjectEditScreenViewModel inherit from it
 */
abstract class SubjectFormViewModel : ViewModel() {
    protected val _uiState = MutableStateFlow(SubjectAddState())

    val uiState = _uiState.asStateFlow()

    fun changeTeacherCount(index: Int) {
        _uiState.update { state ->
            val count = if (index == 0) 1 else 2
            state.copy(teacherCount = count)
        }
    }

    fun subjectNameChange(name: String) {
        _uiState.update { state ->
            state.copy(subjectName = ValidInput(name, isValidString(name)))
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
                    teacherLecture = if (type == LessonType.LECTURES) ValidInput(name, isValid) else state.teacherLecture,
                    teacherSeminar = if (type == LessonType.SEMINAR) ValidInput(name, isValid) else state.teacherSeminar
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

    protected fun updateIsValidInput() = _uiState.update { it.copy(isValidInput = canBeSaved(it)) }
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
    val isValid: Boolean = true,
)


internal fun isValidString(input: String, maxLength: Int = 50): Boolean {
    val isValid = input.isNotEmpty() &&
            input.length <= maxLength &&
            !input.startsWith(" ") &&
            !input.first().isDigit()

    return isValid
}

internal fun canBeSaved(state: SubjectAddState): Boolean {
    return isValidString(state.subjectName.value) &&
            state.hours.isValid &&
            state.creditScore.isValid &&
            state.teacherLecture.isValid &&
            state.teacherSeminar.isValid
}