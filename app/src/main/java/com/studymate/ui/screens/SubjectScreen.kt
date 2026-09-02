package com.studymate.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studymate.R
import com.studymate.data.Subject
import com.studymate.data.TestData
import com.studymate.ui.navigation.NavigationDestination
import com.studymate.ui.screens.components.StudyMateBottomAppBar
import com.studymate.ui.screens.components.StudyMateTopAppBar
import com.studymate.ui.screens.components.SubjectName
import com.studymate.ui.theme.StudyMateTheme

object SubjectDestination: NavigationDestination {
    override val route = "subjects"
    override val titleRes = R.string.subject_screen
    override val destinationIcon = Icons.Default.Book
}

@Composable
fun SubjectScreen(
    modifier: Modifier,
    viewModel: SubjectScreenViewModel = viewModel(),
    navigateScheduleScreen: () -> Unit = {},
    navigateToDetailScreen: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    SubjectScreenContent(
        state = state,
        onSubjectClick = navigateToDetailScreen,
        onEditClick = viewModel::onEditClick,
        onFabClick = { viewModel.onFabClick() },
        navigateScheduleScreen = navigateScheduleScreen,
        modifier = modifier
    )
}

@Composable
fun SubjectScreenContent(
    modifier: Modifier = Modifier,
    state: SubjectUiState,
    onSubjectClick: (Int) -> Unit = {},
    onEditClick: () -> Unit = {},
    onFabClick: () -> Unit = {},
    navigateScheduleScreen: () -> Unit = {},
) {
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            StudyMateTopAppBar(
                title = stringResource(SubjectDestination.titleRes),
                modifier = Modifier.fillMaxWidth()
            )
        },

        bottomBar = {
            StudyMateBottomAppBar(
                modifier = Modifier.clip(
                    RoundedCornerShape(topStart =  12.dp, topEnd = 12.dp)
                ),
                navigateScheduleScreen = navigateScheduleScreen,
                selectedDestination = SubjectDestination
            )
        },

        floatingActionButton = {
            AnimatedVisibility(
                visible = !listState.isScrollInProgress,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(innerPadding)
        ) {
            items(state.listOfSubjects.size) { id ->
                SubjectCard(
                    subject = state.listOfSubjects[id],
                    onClick = onSubjectClick,
                    onEditClick = onEditClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun SubjectCard(
    modifier: Modifier = Modifier,
    subject: Subject,
    onClick: (Int) -> Unit,
    onEditClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { onClick(subject.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SubjectName(
                name = subject.name,
                onEditClick = onEditClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                if (subject.teacherLecture != null) {
                    Text(
                        text = "Lecture: ${subject.teacherLecture}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                if (subject.teacherSeminar != null) {
                    Text(
                        text = "Seminar: ${subject.teacherSeminar}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SubjectScreenPreview() {
    StudyMateTheme {
        Surface {
            SubjectScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                state = SubjectUiState(listOfSubjects = TestData.getSubjects())
            )
        }
    }
}

@Preview
@Composable
fun SubjectScreenDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                state = SubjectUiState(listOfSubjects = TestData.getSubjects())
            )
        }
    }
}