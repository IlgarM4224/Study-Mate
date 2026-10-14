package com.studymate.ui.screens.subjectDetailScreen

import com.studymate.data.GradeType
import com.studymate.data.Subject

/**
 * Represents the complete UI state for the Subject Detail screen.
 *
 * Encapsulates the core [Subject] domain data, calculated grade metrics,
 * attendance stats, and UI presentation flags for bottom sheets and expandable sections.
 *
 * @property subject Core domain entity holding raw data about the subject (hours, grade lists, etc.).
 * @property maxScore Maximum achievable score threshold for this subject (defaults to 50).
 */
data class SubjectDetailState(
    val subject: Subject,
    val state: MutableSubjectDetailState,
    val maxScore: Int = 50
)

/**
 * @property sheetState State holder for the bottom sheet dialog used during grade entry.
 * @property overallScore Calculated total weighted score. `null` if insufficient data.
 * @property averageColloquium Calculated average grade for colloquiums. `null` if no grades exist.
 * @property averageSeminar Calculated average grade for seminars. `null` if no grades exist.
 * @property independentWorkSum Total sum of accumulated points from independent work assignments.
 * @property attendanceScore Calculated attendance score based on total hours and missed classes (max 10.0).
 * @property showMore Flag controlling the expansion/collapse of secondary details in the UI.
 * @property limit Maximum number of allowable missed lessons based on course hours. `null` if hours are undefined.
 * @property activeGradeType The grade category currently selected for addition or interaction.
*/
data class MutableSubjectDetailState(
    val sheetState: BottomSheetState = BottomSheetState(),
    val overallScore: Float? = null,
    val averageColloquium: Float? = null,
    val averageSeminar: Float? = null,
    val independentWorkSum: Int = 0,
    val attendanceScore: Float = 10f,
    val showMore: Boolean = false,
    val showMoreActions: Boolean = false,
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
    val selectedGradeIndex: Int? = null,
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
