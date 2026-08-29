package com.studymate.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.studymate.data.Subject
import com.studymate.data.TestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.roundToInt

data class SubjectDetailState(
    val subject: Subject,
    val maxScore: Int = 50,
    val overallScore: Float? = null,
    val averageColloquium: Float? = null,
    val averageSeminar: Float? = null,
    val independentWork: Int = 10,
    val attendanceScore: Float = 10f,
    val showMore: Boolean = false
)
class SubjectDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

private val subjectId: Int = checkNotNull(savedStateHandle[SubjectDetailDestination.SUBJECT_ID_ARG])

    private val _uiState = MutableStateFlow(
        SubjectDetailState(
            subject = TestData.getSubjects()[subjectId - 1],
            averageSeminar = getAverage(TestData.getSubjects()[subjectId - 1].seminarGradesList),
            averageColloquium = getAverage(TestData.getSubjects()[subjectId - 1].colloquiumGradesList),
            overallScore = getOverallScore(
                averageSeminar = getAverage(TestData.getSubjects()[subjectId - 1].seminarGradesList),
                averageColloquium = getAverage(TestData.getSubjects()[subjectId - 1].colloquiumGradesList),
                independentWork = 10,
                attendanceScore = 10f
            )
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onMoreClick() {}

    fun addColloquiumGrade() {
        val newList = _uiState.value.subject.colloquiumGradesList.toMutableList()
        newList.add(newList.size, 0)

        _uiState.update {
            it.copy(
                subject = it.subject.copy(colloquiumGradesList = newList),
                averageColloquium = getAverage(newList),
                overallScore = getOverallScore(
                    averageSeminar = it.averageSeminar ?: 0f,
                    averageColloquium =  getAverage(newList),
                    independentWork = 10,
                    attendanceScore = 10f
                )
            )
        }
    }

    fun addSeminarGrade() {
        val newList = _uiState.value.subject.seminarGradesList.toMutableList()
        newList.add(newList.size, 10)

        _uiState.update {
            it.copy(
                subject = it.subject.copy(seminarGradesList = newList),
                averageSeminar = getAverage(newList),
                overallScore = getOverallScore(
                    averageSeminar = getAverage(newList),
                    averageColloquium = it.averageColloquium ?: 0f,
                    independentWork = 10,
                    attendanceScore = 10f
                )
            )
        }
    }

    fun addMissed() {
        val newMissed = _uiState.value.subject.missedLessons?.plus(1)

        _uiState.update {
            it.copy(
                subject = it.subject.copy(missedLessons = newMissed)
            )
        }
    }

    fun removeMissed() {
        val newMissed = when(_uiState.value.subject.missedLessons) {
            null -> null
            0 -> 0
            else -> _uiState.value.subject.missedLessons?.minus(1)
        }

        _uiState.update {
            it.copy(
                subject = it.subject.copy(missedLessons = newMissed)
            )
        }
    }

    fun onGradeClick() {}

    fun onArrowClick() {
        _uiState.update {
            it.copy(
                showMore = !it.showMore
            )
        }
    }
}

fun Float.toLabel() = (if (this % 1f == 0f) toInt() else this).toString()

/**
 *  Extension function to round to N places
 */
fun Float.roundTo(decimals: Int): Float {
    val factor = 10.0.pow(decimals)
    return ((this * factor).roundToInt() / factor).toFloat()
}
fun getAverage(list: List<Int>): Float {
    val sum = list.sum()
    val size = if (list.isEmpty()) 1f else list.size.toFloat()

    return (sum / size).roundTo(2)
}

fun getOverallScore(
    averageSeminar: Float = 0f,
    averageColloquium: Float = 0f,
    independentWork: Int = 0,
    attendanceScore: Float = 0f,
): Float {
    val seminarAndColloquium = (averageSeminar * 0.4f + averageColloquium * 0.6f) * 3
    val result = seminarAndColloquium + independentWork + attendanceScore

    return result.roundTo(2)
}