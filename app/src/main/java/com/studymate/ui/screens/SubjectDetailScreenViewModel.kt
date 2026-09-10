package com.studymate.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.studymate.data.Subject
import com.studymate.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.roundToLong

enum class GradeType { SEMINAR, COLLOQUIUM, INDEPENDENT_WORK, NONE }

enum class LessonType { SEMINAR, LECTURES }

data class SubjectDetailState(
    val subject: Subject,
    val maxScore: Int = 50,
    val overallScore: Float? = null,
    val averageColloquium: Float? = null,
    val averageSeminar: Float? = null,
    val independentWorkSum: Int = 0,
    val attendanceScore: Float = 10f,
    val showMore: Boolean = false,
    val limit: Int? = null,
    val activeGradeType: GradeType = GradeType.NONE,
    val isIncorrectInput: Boolean = false
)

//data class BottomSheetState(
//
//)

class SubjectDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val subjectId: Int = checkNotNull(savedStateHandle[SubjectDetailDestination.SUBJECT_ID_ARG])
    private val test = TestData.getSubjects()[subjectId - 1]
    private val _uiState = MutableStateFlow(
        SubjectDetailState(
            subject = test,
            averageSeminar = test.seminarGradesList.averageForLabel(),
            averageColloquium = test.colloquiumGradesList.averageForLabel(),
            limit = test.calculateLimit(),
            attendanceScore = test.calculateAttendanceScore(),
            independentWorkSum = test.independentWorkGradesList.sum(),
            overallScore = test.getOverallScore()
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onMoreClick() {}

    fun onAddClick(type: GradeType) {
        _uiState.update {
            it.copy(activeGradeType = type)
        }
    }

    fun onDismissRequest() {
        _uiState.update {
            it.copy(activeGradeType = GradeType.NONE)
        }
    }

    fun addGrade(type: GradeType, grade: Int) {
        when(type) {
            GradeType.SEMINAR -> {
                addSeminarGrade(grade)
                onDismissRequest()
            }

            GradeType.COLLOQUIUM -> {
                addColloquiumGrade(grade)
                onDismissRequest()
            }

            GradeType.INDEPENDENT_WORK -> {
                addIndependentWork(grade)
                onDismissRequest()
            }

            else -> onDismissRequest()
        }
    }

    fun addIndependentWork(grade: Int) {
        val newList = _uiState.value.subject.independentWorkGradesList.toMutableList()
        newList.add(grade)

        _uiState.update {
            it.copy(
                subject = it.subject.copy(independentWorkGradesList = newList),
                independentWorkSum = newList.sum(),
            )
        }

        updateOverallScore()
    }

    fun addColloquiumGrade(grade: Int) {
        val newList = _uiState.value.subject.colloquiumGradesList.toMutableList()
        newList.add(grade)

        _uiState.update {
            it.copy(
                subject = it.subject.copy(colloquiumGradesList = newList),
                averageColloquium = newList.averageForLabel(),
            )
        }

        updateOverallScore()
    }

    fun addSeminarGrade(grade: Int) {
        val newList = _uiState.value.subject.seminarGradesList.toMutableList()
        newList.add(grade)

        _uiState.update {
            it.copy(
                subject = it.subject.copy(seminarGradesList = newList),
                averageSeminar = newList.averageForLabel(),
            )
        }

        updateOverallScore()
    }

    private fun updateOverallScore() {
        _uiState.update { it.copy(overallScore = it.subject.getOverallScore()) }
    }

    fun addMissed() { updateMissed(1) }

    fun removeMissed() { updateMissed(-1) }

    private fun updateMissed(increment: Int) {
        val missedCount = _uiState.value.subject.missedLessons

        _uiState.update { it.copy(subject = it.subject.copy(missedLessons = missedCount + increment)) }

        updateAttendanceScore()
        updateOverallScore()
    }

    private fun updateAttendanceScore() {
        _uiState.update { it.copy(attendanceScore = it.subject.calculateAttendanceScore()) }
    }

    fun onGradeClick() {}

    fun onArrowClick() {
        _uiState.update {
            it.copy( showMore = !it.showMore )
        }
    }
}

fun Subject.calculateAttendanceScore(increment: Int? = null): Float {
    val newCount = if (increment != null) missedLessons + increment else missedLessons
    val attendanceScore = if (hours != null) 10.0f - ( newCount * 20.0f/ hours) else 10f

    return attendanceScore.roundTo(2)
}

fun Subject.calculateLimit(): Int? {
    if (hours == null) return null
    return hours/8
}

/**
 *  Extension function to round to N places
 */
fun Float.roundTo(decimals: Int): Float {
    if (decimals <= 0) return kotlin.math.round(this)

    val factor = 10.0.pow(decimals)
    return ((this * factor).roundToLong() / factor).toFloat()
}

fun List<Int>.averageForLabel(): Float {
    val size = if (isEmpty()) 1f else size.toFloat()

    return (sum() / size).roundTo(2)
}

fun Subject.getOverallScore(): Float {
    val seminarAndColloquium = seminarGradesList.averageForLabel() * 0.4f + colloquiumGradesList.averageForLabel() * 0.6f
    val result = seminarAndColloquium * 3 + independentWorkGradesList.sum() + calculateAttendanceScore()

    return result.roundTo(2)
}