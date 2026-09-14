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


/**
 * Types of graded work: seminar, colloquium, independent work.
 * NONE is used as the default state (when nothing is selected).
 */
enum class GradeType { SEMINAR, COLLOQUIUM, INDEPENDENT_WORK, NONE }


/**
 * Types of conducted classes.
 */
enum class LessonType { SEMINAR, LECTURES }


/**
 * Represents the complete UI state for the Subject Detail screen.
 *
 * Encapsulates the core [Subject] domain data, calculated grade metrics,
 * attendance stats, and UI presentation flags for bottom sheets and expandable sections.
 *
 * @property subject Core domain entity holding raw data about the subject (hours, grade lists, etc.).
 * @property sheetState State holder for the bottom sheet dialog used during grade entry.
 * @property maxScore Maximum achievable score threshold for this subject (defaults to 50).
 * @property overallScore Calculated total weighted score. `null` if insufficient data.
 * @property averageColloquium Calculated average grade for colloquiums. `null` if no grades exist.
 * @property averageSeminar Calculated average grade for seminars. `null` if no grades exist.
 * @property independentWorkSum Total sum of accumulated points from independent work assignments.
 * @property attendanceScore Calculated attendance score based on total hours and missed classes (max 10.0).
 * @property showMore Flag controlling the expansion/collapse of secondary details in the UI.
 * @property limit Maximum number of allowable missed lessons based on course hours. `null` if hours are undefined.
 * @property activeGradeType The grade category currently selected for addition or interaction.
 */
data class SubjectDetailState(
    val subject: Subject,
    val sheetState: BottomSheetState,
    val maxScore: Int = 50,
    val overallScore: Float? = null,
    val averageColloquium: Float? = null,
    val averageSeminar: Float? = null,
    val independentWorkSum: Int = 0,
    val attendanceScore: Float = 10f,
    val showMore: Boolean = false,
    val limit: Int? = null,
    val activeGradeType: GradeType = GradeType.NONE,
)

/**
 * State of the BottomSheet used for entering new grades.
 */
data class BottomSheetState(
    val grade: String = "",
    val type: GradeType = GradeType.NONE,
    val isEntryValid: Boolean = false,
    val activeGradeType: GradeType = GradeType.NONE,
) {
    /**
     * Returns the UI label depending on the selected grade type.
     */
    fun getLabel(): String {
       return when (type) {
           GradeType.INDEPENDENT_WORK -> "Independent Work grade"
           GradeType.COLLOQUIUM -> "Colloquium grade"
           GradeType.SEMINAR -> "Seminar grade"
           else -> ""
        }
    }
}

/**
 * ViewModel for managing the logic and state of the subject detail screen.
 */
class SubjectDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    // Extract the subject ID from the passed navigation arguments
    private val subjectId: Int = checkNotNull(savedStateHandle[SubjectDetailDestination.SUBJECT_ID_ARG])
    private val test = TestData.getSubjects()[subjectId - 1]

    // Internal mutable state
    private val _uiState = MutableStateFlow(
        SubjectDetailState(
            subject = test,
            sheetState = BottomSheetState(),
            averageSeminar = test.seminarGradesList.averageForLabel(),
            averageColloquium = test.colloquiumGradesList.averageForLabel(),
            limit = test.calculateLimit(),
            attendanceScore = test.calculateAttendanceScore(),
            independentWorkSum = test.independentWorkGradesList.sum(),
            overallScore = test.getOverallScore()
        )
    )

    // Public immutable state for UI observation
    val uiState = _uiState.asStateFlow()

    fun onMoreClick() {} // TODO: Implement logic for clicking the ":" button

    /**
     * Opens the BottomSheet to add a grade of a specific type.
     */
    fun onAddClick(type: GradeType) {
        _uiState.update {
            it.copy(
                sheetState = it.sheetState.copy(
                    type = type,
                    grade = "",
                    isEntryValid = false
                ),
                activeGradeType = type,
            )
        }
    }

    fun onGradeChange(newGrade: String) {
        val isValid = isValidGrade(newGrade) && newGrade.isNotEmpty()

        _uiState.update {
            it.copy(
                sheetState = it.sheetState.copy(
                    grade = newGrade,
                    isEntryValid = isValid
                )
            )
        }
    }

    /**
     * Closes the BottomSheet, resetting the active grade type.
     */
    fun onDismissRequest() {
        _uiState.update {
            it.copy(activeGradeType = GradeType.NONE)
        }
    }

    /**
     * Routes the grade addition to the appropriate function based on the type.
     */
    fun addGrade(type: GradeType, grade: Int) {
        when(type) {
            GradeType.SEMINAR -> addSeminarGrade(grade)
            GradeType.COLLOQUIUM -> addColloquiumGrade(grade)
            GradeType.INDEPENDENT_WORK -> addIndependentWork(grade)
            else -> onDismissRequest()
        }

        onDismissRequest() // Close the sheet after adding
    }

    /**
     * Adds an independent work grade and updates the sum.
     */
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

    /**
     * Adds a colloquium grade and recalculates the average.
     */
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

    /**
     * Adds a seminar grade and recalculates the average.
     */
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

    /**
     * Recalculates the overall score for the subject.
     */
    private fun updateOverallScore() {
        _uiState.update { it.copy(overallScore = it.subject.getOverallScore()) }
    }

    // Increments the missed classes counter by 1
    fun addMissed() { updateMissed(1) }

    // Decrements the missed classes counter by 1
    fun removeMissed() { updateMissed(-1) }

    /**
     * Universal function to modify the number of missed classes.
     * Updates the attendance score and overall score after the change.
     */
    private fun updateMissed(increment: Int) {
        val missedCount = _uiState.value.subject.missedLessons

        _uiState.update { it.copy(subject = it.subject.copy(missedLessons = missedCount + increment)) }

        updateAttendanceScore()
        updateOverallScore()
    }

    /**
     * Recalculates the attendance score in the UI state.
     */
    private fun updateAttendanceScore() {
        _uiState.update { it.copy(attendanceScore = it.subject.calculateAttendanceScore()) }
    }

    fun onGradeClick() {} // TODO: Implement logic for clicking a grade

    /**
     * Toggles the flag for showing additional information (expandable list).
     */
    fun onArrowClick() {
        _uiState.update {
            it.copy( showMore = !it.showMore )
        }
    }
}

fun isValidGrade(grade: String): Boolean {
    val numericGrade = grade.toIntOrNull() ?: return false

    return numericGrade in 0..10
}


/**
 * Calculates the attendance score.
 * A penalty proportional to the number of missed classes and total hours is subtracted from 10 points.
 */
fun Subject.calculateAttendanceScore(increment: Int? = null): Float {
    val newCount = if (increment != null) missedLessons + increment else missedLessons
    // Formula: 10 - (missed * 20 / total hours). If hours are null, returns 10 points.
    val attendanceScore = if (hours != null) 10.0f - ( newCount * 20.0f/ hours) else 10f

    return attendanceScore.roundTo(2)
}

/**
 * Calculates the allowed limit of missed classes.
 * The limit is 25% of the total course hours. Since each lesson is 2 hours long,
 * the formula to get the max number of missed lessons is: (hours * 0.25) / 2 = hours / 8.
 */
fun Subject.calculateLimit(): Int? {
    if (hours == null) return null
    return hours/8
}

/**
 * Extension function for Float, allowing it to be rounded to a given number of decimal places.
 */
fun Float.roundTo(decimals: Int): Float {
    if (decimals <= 0) return kotlin.math.round(this)

    val factor = 10.0.pow(decimals)
    return ((this * factor).roundToLong() / factor).toFloat()
}

/**
 * Calculates the average value from a list of grades, protecting against division by zero.
 * The result is rounded to 2 decimal places.
 */
fun List<Int>.averageForLabel(): Float {
    val size = if (isEmpty()) 1f else size.toFloat()

    return (sum() / size).roundTo(2)
}

/**
 * The main formula for calculating the overall (final) score for the subject.
 * Seminar weight is 40%, colloquium weight is 60%.
 * The result is multiplied by 3, then independent work points and attendance score are added.
 */
fun Subject.getOverallScore(): Float {
    val seminarAndColloquium = seminarGradesList.averageForLabel() * 0.4f + colloquiumGradesList.averageForLabel() * 0.6f
    val result = seminarAndColloquium * 3 + independentWorkGradesList.sum() + calculateAttendanceScore()

    return result.roundTo(2)
}