package com.studymate.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studymate.data.GradeType
import com.studymate.data.TestData
import com.studymate.ui.navigation.NavigationDestination
import com.studymate.ui.screens.components.subjectDetailScreen.GradeBottomSheet
import com.studymate.ui.screens.components.subjectDetailScreen.GradesCard
import com.studymate.ui.screens.components.subjectDetailScreen.LimitCard
import com.studymate.ui.screens.components.subjectDetailScreen.OverallScoreCard
import com.studymate.ui.screens.components.general.StudyMateTopAppBar
import com.studymate.ui.screens.components.subjectDetailScreen.SubjectNameCard
import com.studymate.ui.theme.StudyMateTheme
import com.studymate.util.averageForLabel

object SubjectDetailDestination: NavigationDestination {
    override val route = "SubjectDetail"
    override val titleRes = null
    override val destinationIcon = null
    const val SUBJECT_ID_ARG = "subjectId"
    val routeWithArgs = "$route/{$SUBJECT_ID_ARG}"
}

@Composable
fun SubjectDetailScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    viewModel: SubjectDetailScreenViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    SubjectDetailScreenContent(
        modifier = modifier,
        state = uiState,
        navigateBack = navigateBack,
        onAddClick = { viewModel.onAddClick(it)},
        onDismissRequest = { viewModel.onDismissRequest() },
        addGrade = { type, grade ->
            viewModel.addGrade(type, grade)
        },
        changeGrade = { type,grade, index->
            viewModel.changeGrade(index,grade, type)
        },
        onValueChange = { viewModel.onValueChange(it)},
        onMoreClick = { viewModel.onMoreClick() },
        onGradeClick = { type, gradeId ->
            viewModel.onGradeClick(type, gradeId)
        },
        onGradeEditClick = { type, gradeId ->
            viewModel.onGradeEditClick(type, gradeId)
        },
        onGradeDeleteClick = { type, gradeId ->
            viewModel.onGradeDeleteClick(type, gradeId)
        },
        onGradeDismissRequest = { viewModel.onGradeDismissRequest() },
        addMissedLesson = { viewModel.addMissed() },
        onArrowClick = { viewModel.onArrowClick() },
        removeMissedLesson = { viewModel.removeMissed() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDetailScreenContent(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onAddClick: (GradeType) -> Unit = {},
    onDismissRequest: () -> Unit = {},
    addGrade: (GradeType, Int) -> Unit,
    changeGrade: (GradeType, Int, Int) -> Unit,
    onGradeClick: (GradeType, Int) -> Unit,
    onValueChange: (String) -> Unit = {},
    onGradeDismissRequest: () -> Unit = {},
    onGradeEditClick: (GradeType, Int) -> Unit = {_, _ ->},
    onGradeDeleteClick: (GradeType, Int) -> Unit = {_, _ ->},
    addMissedLesson: () -> Unit = {},
    removeMissedLesson: () -> Unit = {},
    onArrowClick: () -> Unit = {},
    state: SubjectDetailState
) {
    Scaffold(
        modifier = modifier,

        topBar = {
            StudyMateTopAppBar(
                title = SubjectDetailDestination.titleRes ,
                canNavigateBack = true,
                onNavigateClick = navigateBack,
                showMore = true,
                onMoreClick = onMoreClick,
                modifier = Modifier.fillMaxWidth()
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            SubjectNameCard(
                subjectName = state.subject.name,
                subjectCredit = state.subject.creditScore,
                subjectHours = state.subject.hours,
                showMore = state.showMore,
                lecture = state.subject.teacherLecture,
                seminar = state.subject.teacherSeminar,
                onArrowClick = onArrowClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OverallScoreCard(
                modifier = Modifier.fillMaxWidth(),
                maxScore = state.maxScore,
                overallScore = state.overallScore ?: 0f,
                averageColloquium = state.averageColloquium ?: 0f,
                averageSeminar = state.averageSeminar ?: 0f,
                independentWork = state.independentWorkSum,
                attendanceScore = state.attendanceScore
            )

            if (state.limit != null) {
                Spacer(Modifier.height(16.dp))

                LimitCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    limit = state.limit,
                    missed = state.subject.missedLessons,
                    addMissed = addMissedLesson,
                    removeMissed = removeMissedLesson
                )
            }

            Spacer(Modifier.height(16.dp))

            GradesCard(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Grades for Seminar",
                gradeType = GradeType.SEMINAR,
                icon = Icons.Outlined.People,
                onAddClick = onAddClick,
                onGradeClick = onGradeClick,
                showAddButton = true,
                onDeleteClick = onGradeDeleteClick,
                onEditClick = onGradeEditClick,
                onDismissRequest = onGradeDismissRequest,
                selectedGradeIndex = state.sheetState.selectedGradeIndex,
                selectedGradeType = state.sheetState.type,
                gradesList = state.subject.seminarGradesList
            )

            Spacer(Modifier.height(16.dp))

            GradesCard(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Grades for Colloquium",
                gradeType = GradeType.COLLOQUIUM,
                icon = Icons.Outlined.School,
                onAddClick = onAddClick,
                gradesList = state.subject.colloquiumGradesList,
                showAddButton = true,
                onDeleteClick = onGradeDeleteClick,
                onEditClick = onGradeEditClick,
                onDismissRequest = onGradeDismissRequest,
                selectedGradeIndex = state.sheetState.selectedGradeIndex,
                selectedGradeType = state.sheetState.type,
                onGradeClick = onGradeClick
            )

            Spacer(Modifier.height(16.dp))

            GradesCard(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Grades for Independent work",
                gradeType = GradeType.INDEPENDENT_WORK,
                icon = Icons.Outlined.ContactPage,
                onAddClick = onAddClick,
                gradesList = state.subject.independentWorkGradesList,
                showAddButton = state.independentWorkSum < 10,
                onDeleteClick = onGradeDeleteClick,
                onEditClick = onGradeEditClick,
                onDismissRequest = onGradeDismissRequest,
                selectedGradeIndex = state.sheetState.selectedGradeIndex,
                selectedGradeType = state.sheetState.type,
                onGradeClick = onGradeClick,
            )

            AnimatedVisibility(visible = state.activeGradeType != GradeType.NONE) {
                GradeBottomSheet(
                    modifier = Modifier.fillMaxWidth(),
                    sheetState = state.sheetState,
                    onDismissRequest = onDismissRequest,
                    onValueChange = onValueChange,
                    addGrade = addGrade,
                    changeGrade = changeGrade
                )
            }
        }
    }
}

@Preview
@Composable
fun SubjectDetailScreenPreview() {
    StudyMateTheme {
        Surface {
            val test = TestData.getSubjects()[0]
            SubjectDetailScreenContent(
                modifier = Modifier.padding(16.dp),
                state = SubjectDetailState(
                    subject = TestData.getSubjects()[0],
                    sheetState = BottomSheetState(),
                    showMore = false,
                    limit = 9,
                    overallScore = 40f,
                    averageColloquium = test.colloquiumGradesList.averageForLabel(),
                    averageSeminar = test.seminarGradesList.averageForLabel(),
                ),
                addGrade = { _, _ -> },
                onGradeClick = { _, _ -> },
                changeGrade = { _, _, _ -> }
            )
        }
    }
}

@Preview
@Composable
fun SubjectDetailScreenDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            val test = TestData.getSubjects()[0]
            SubjectDetailScreenContent(
                modifier = Modifier.padding(16.dp),
                state = SubjectDetailState(
                    subject = TestData.getSubjects()[0],
                    sheetState = BottomSheetState(),
                    showMore = false,
                    limit = 9,
                    overallScore = 40f,
                    averageColloquium = test.colloquiumGradesList.averageForLabel(),
                    averageSeminar = test.seminarGradesList.averageForLabel(),
                ),
                addGrade = { _, _ -> },
                onGradeClick = { _, _ -> },
                changeGrade = { _, _, _ -> }
            )
        }
    }
}