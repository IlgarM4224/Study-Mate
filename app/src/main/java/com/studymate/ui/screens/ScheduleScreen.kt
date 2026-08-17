package com.studymate.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studymate.data.Day
import com.studymate.data.TestData
import com.studymate.ui.screens.components.LessonTopAppBar
import com.studymate.ui.screens.components.LessonsList
import com.studymate.ui.screens.components.WeekRowAndType
import com.studymate.ui.theme.StudyMateTheme


@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleScreenViewModel =  viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ScheduleScreenContent(
        modifier = modifier,
        lessonCardUIState = uiState.lessonCardUIState,
        weekUiState = uiState.weekUiState,
        onEditClick = { viewModel.onEditClick() },
        onCardClick = viewModel::onCardClick,
        onDayClick =  viewModel::onDayClick,
        onNextWeekClick = { viewModel.onNextWeekClick() },
        onCurrentWeekClick = { viewModel.onCurrentWeekClick() },
    )
}
@Composable
fun ScheduleScreenContent(
    modifier: Modifier = Modifier,
    lessonCardUIState: LessonCardUIState,
    weekUiState: WeekUiState,
    onEditClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    onDayClick: (Day) -> Unit,
    onNextWeekClick: () -> Unit,
    onCurrentWeekClick: () -> Unit
) {
    Scaffold(
        topBar = {
            LessonTopAppBar(
                title = "Schedule",
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LessonsList(
                lessonsState = lessonCardUIState ,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onEditClick = onEditClick,
                onCardClick = onCardClick,
                weekRowAndType = {
                    WeekRowAndType(
                        weekUiState = weekUiState,
                        onDayClick = onDayClick,
                        onNextWeekClick = onNextWeekClick,
                        onCurrentWeekClick = onCurrentWeekClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScheduleScreenContentLightPreview() {
    StudyMateTheme {
        Surface {
            ScheduleScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                lessonCardUIState = LessonCardUIState(lessons = TestData.getLessons()),
                weekUiState = WeekUiState(
                    week = TestData.getWeek(),
                    weekType = "Upper",
                    isCurrentWeek = false,
                    selectedDay = TestData.getWeek()[0],
                ),
                onEditClick = {},
                onCardClick = {},
                onNextWeekClick = {},
                onDayClick = {},
                onCurrentWeekClick = {}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScheduleScreenContentDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            ScheduleScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                lessonCardUIState = LessonCardUIState(lessons = TestData.getLessons()),
                weekUiState = WeekUiState(
                    week = TestData.getWeek(),
                    weekType = "Upper",
                    isCurrentWeek = true,
                    selectedDay = TestData.getWeek()[0],
                ),
                onEditClick = {},
                onCardClick = {},
                onNextWeekClick = {},
                onDayClick = {},
                onCurrentWeekClick = {}
            )
        }
    }
}