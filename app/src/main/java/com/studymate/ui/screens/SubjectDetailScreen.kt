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
import com.studymate.ui.theme.StudyMateTheme

object SubjectDetailDestination: NavigationDestination {
    override val route = "SubjectDetail"
    override val titleRes = null
    override val destinationIcon = null
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
            OverallScoreCard(
                modifier = Modifier.fillMaxWidth(),
                maxScore = state.maxScore,
                overallScore = state.overallScore ?: 0f
            )

            Spacer(Modifier.height(16.dp))

            if (state.limit != null) {
                Spacer(Modifier.height(16.dp))

                LimitCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    limit = state.limit,
                    missed = state.missedLessons ?: 0
                )
            }

            Spacer(Modifier.height(16.dp))

            GradesCard(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Grades for Seminar",
                icon = Icons.Outlined.People,
                gradesList = state.seminarGradesList
            )

            Spacer(Modifier.height(16.dp))

            GradesCard(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Grades for Colloquium",
                icon = Icons.Outlined.School,
                gradesList = state.colloquiumGradesList
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
                modifier = Modifier.padding(8.dp),
                state = SubjectDetailState(
                    id = TestData.getSubjects()[0].id,
                    name = TestData.getSubjects()[0].name,
                    seminarGradesList = TestData.getSubjects()[0].seminarGradesList,
                    colloquiumGradesList = TestData.getSubjects()[0].colloquiumGradesList,
                    missedLessons = TestData.getSubjects()[0].missedLessons,
                    limit = TestData.getSubjects()[0].limit,
                    overallScore = 40f
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
                modifier = Modifier.padding(8.dp),
                state = SubjectDetailState(
                    id = TestData.getSubjects()[0].id,
                    name = TestData.getSubjects()[0].name,
                    seminarGradesList = TestData.getSubjects()[0].seminarGradesList,
                    colloquiumGradesList = TestData.getSubjects()[0].colloquiumGradesList,
                    missedLessons = TestData.getSubjects()[0].missedLessons,
                    limit = TestData.getSubjects()[0].limit,
                    overallScore = 34.7f
                )
            )
        }
    }
}