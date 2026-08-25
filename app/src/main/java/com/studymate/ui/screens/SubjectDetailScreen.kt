package com.studymate.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.School
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
import com.studymate.data.TestData
import com.studymate.ui.navigation.NavigationDestination
import com.studymate.ui.screens.components.GradesCard
import com.studymate.ui.screens.components.LimitCard
import com.studymate.ui.screens.components.OverallScoreCard
import com.studymate.ui.screens.components.SubjectNameCard
import com.studymate.ui.theme.StudyMateTheme

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
    viewModel: SubjectDetailScreenViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    SubjectDetailScreenContent(
        modifier = modifier,
        state = uiState
    )
}

@Composable
fun SubjectDetailScreenContent(
    modifier: Modifier = Modifier,
    state: SubjectDetailState
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            SubjectNameCard(
                modifier = Modifier.fillMaxWidth(),
                subjectName = state.subject.name
            )

            Spacer(Modifier.height(16.dp))

            OverallScoreCard(
                modifier = Modifier.fillMaxWidth(),
                maxScore = state.maxScore,
                overallScore = state.overallScore ?: 0f,
                averageColloquium = state.averageColloquium ?: 0f,
                averageSeminar = state.averageSeminar ?: 0f,
                independentWork = state.independentWork,
                attendanceScore = state.attendanceScore
            )

            if (state.subject.limit != null) {
                Spacer(Modifier.height(16.dp))

                LimitCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    limit = state.subject.limit,
                    missed = state.subject.missedLessons ?: 0
                )
            }

            Spacer(Modifier.height(16.dp))

            GradesCard(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Grades for Seminar",
                icon = Icons.Outlined.People,
                gradesList = state.subject.seminarGradesList
            )

            Spacer(Modifier.height(16.dp))

            GradesCard(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Grades for Colloquium",
                icon = Icons.Outlined.School,
                gradesList = state.subject.colloquiumGradesList
            )
        }
    }
}

@Preview
@Composable
fun SubjectDetailScreenPreview() {
    StudyMateTheme {
        Surface {
            SubjectDetailScreenContent(
                modifier = Modifier.padding(16.dp),
                state = SubjectDetailState(
                    subject = TestData.getSubjects()[0],
                    overallScore = 40f,
                    averageColloquium = getAverage(TestData.getSubjects()[0].colloquiumGradesList),
                    averageSeminar = getAverage(TestData.getSubjects()[0].seminarGradesList)
                )
            )
        }
    }
}

@Preview
@Composable
fun SubjectDetailScreenDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectDetailScreenContent(
                modifier = Modifier.padding(16.dp),
                state = SubjectDetailState(
                    subject = TestData.getSubjects()[0],
                    overallScore = 34.7f,
                    averageColloquium = getAverage(TestData.getSubjects()[0].colloquiumGradesList),
                    averageSeminar = getAverage(TestData.getSubjects()[0].seminarGradesList)
                )
            )
        }
    }
}