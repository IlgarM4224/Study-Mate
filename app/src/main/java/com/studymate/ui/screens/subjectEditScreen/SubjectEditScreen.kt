package com.studymate.ui.screens.subjectEditScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studymate.R
import com.studymate.data.LessonType
import com.studymate.ui.AppViewModelProvider
import com.studymate.ui.navigation.NavigationDestination
import com.studymate.ui.screens.subjectAddScreen.SubjectAddScreenContent

object SubjectEditDestination : NavigationDestination {
    override val route = "SubjectEdit"
    override val titleRes = R.string.subject_edit_screen
    override val destinationIcon = null

    const val SUBJECT_ID_ARG = "subjectId"
    val routeWithArgs = "$route/{$SUBJECT_ID_ARG}"
}

@Composable
fun SubjectEditScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    viewModel: SubjectEditScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()

    SubjectAddScreenContent(
        modifier = modifier,
        title = stringResource(SubjectEditDestination.titleRes),
        navigateBack = navigateBack,
        changeTeacherCount = { viewModel.changeTeacherCount(it) },
        changeSubjectName = { viewModel.subjectNameChange(it) },
        changeTeacherName = { name, type: LessonType -> viewModel.teacherNameChange(name, type) },
        changeMetrics = { value, maxValue, isHours ->
            viewModel.metricInputChange(value, maxValue, isHours)
        },
        onSaveClick = viewModel::updateSubject,
        state = uiState
    )
}