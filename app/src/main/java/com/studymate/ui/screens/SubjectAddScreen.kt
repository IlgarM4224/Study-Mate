package com.studymate.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studymate.R
import com.studymate.data.LessonType
import com.studymate.ui.navigation.NavigationDestination
import com.studymate.ui.screens.components.general.RoundedButton
import com.studymate.ui.screens.components.general.StudyMateTopAppBar
import com.studymate.ui.screens.components.subjectAddScreen.SubjectInputCard
import com.studymate.ui.screens.components.subjectAddScreen.SubjectMetricsInputCard
import com.studymate.ui.screens.components.subjectAddScreen.TeacherInputCard
import com.studymate.ui.theme.StudyMateTheme

object SubjectAddDestination: NavigationDestination {
    override val route = "SubjectAdd"
    override val titleRes = R.string.subject_add_screen
    override val destinationIcon = null
}


@Composable
fun SubjectAddScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    viewModel: SubjectAddScreenViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    SubjectAddScreenContent(
        modifier = modifier,
        navigateBack = navigateBack,
        changeTeacherCount = { viewModel.changeTeacherCount(it) },
        changeSubjectName = { viewModel.subjectNameChange(it) },
        changeTeacherName = { name, type -> viewModel.teacherNameChange(name, type) },
        changeMetrics = { value, maxValue, isHours ->
            viewModel.metricInputChange(value, maxValue, isHours)
        },
        state = uiState
    )
}

@Composable
fun SubjectAddScreenContent(
    modifier: Modifier = Modifier,
    state: SubjectAddState,
    changeTeacherCount: (Int) -> Unit = {},
    changeSubjectName: (String) -> Unit = {},
    changeTeacherName: (String, LessonType) -> Unit = {_, _ ->},
    changeMetrics: (String, Int, Boolean) -> Unit = {_, _, _ ->},
    navigateBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            StudyMateTopAppBar(
                title = stringResource(SubjectAddDestination.titleRes),
                titleStyle = MaterialTheme.typography.displaySmall,
                canNavigateBack = true,
                onNavigateClick = navigateBack,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            SubjectInputCard(
                value = state.subjectName.value,
                isError = !state.subjectName.isValid,
                onValueChange = changeSubjectName,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            SubjectMetricsInputCard(
                hours = state.hours,
                creditScore = state.creditScore,
                onHoursValueChange = { changeMetrics(it, state.maxHoursValue, true) },
                onCreditValueChange = { changeMetrics(it, state.maxCreditScore, false) },
                modifier = Modifier.fillMaxWidth()
            )

            TeacherInputCard(
                teachersCount = state.teacherCount,
                changeTeacherCount = changeTeacherCount,
                lectureTeacherValue = state.teacherLecture,
                seminarTeacherValue = state.teacherSeminar,
                onLectureTeacherValueChange = { changeTeacherName(it, LessonType.LECTURES) },
                onSeminarTeacherValueChange = { changeTeacherName(it, LessonType.SEMINAR) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )

            Spacer(Modifier.weight(1f))

            RoundedButton(
                label = "Save subject",
                labelStyle = MaterialTheme.typography.titleLarge,
                onClick = {
                    navigateBack()
                },
                icon = Icons.Default.Done,
                iconSize = 36.dp,
                tint = MaterialTheme.colorScheme.onPrimary,
                spacer = 8.dp,
                enabled = state.isValidInput,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun SubjectAddScreenContentPreview() {
    StudyMateTheme {
        Surface {
            SubjectAddScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                state = SubjectAddState(teacherCount = 2)
            )
        }
    }
}

@Preview
@Composable
fun SubjectAddScreenContentDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectAddScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                state = SubjectAddState()
            )
        }
    }
}
