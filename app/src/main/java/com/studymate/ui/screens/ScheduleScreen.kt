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
import com.studymate.data.Day
import com.studymate.data.Lesson
import com.studymate.data.TestData
import com.studymate.ui.screens.components.LessonTopAppBar
import com.studymate.ui.screens.components.LessonsList
import com.studymate.ui.screens.components.WeekRowAndType
import com.studymate.ui.theme.StudyMateTheme


@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleScreenViewModel = ScheduleScreenViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ScheduleScreenContend(
        modifier = modifier,
        lessons = uiState.lessons,
        week = uiState.week,
        weekType = uiState.weekType,
        onEditClick = { viewModel.onEditeClick() },
        onCardClick = { viewModel.onCardClick() },
        onDayClick = { viewModel.onDayClick()},
        onNextWeekClick = { viewModel.onNextWeekClick() },
        onCurrentWeekClick = { viewModel.onCurrentWeekClick() },
    )
}
@Composable
fun ScheduleScreenContend(
    modifier: Modifier = Modifier,
    lessons: List<Lesson> = emptyList(),
    week: List<Day> = emptyList(),
    weekType: String,
    onEditClick: () -> Unit,
    onCardClick: () -> Unit,
    onDayClick: () -> Unit,
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
                lessons = lessons,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onEditClick = onEditClick,
                onCardClick = onCardClick,
                weekRowAndType = {
                    WeekRowAndType(
                        week = week,
                        weekType = weekType,
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
fun ScheduleScreenContendLightPreview() {
    StudyMateTheme {
        Surface {
            ScheduleScreenContend(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                lessons = TestData.getLessons(),
                week = TestData.getWeek(),
                weekType = "Upper",
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
fun ScheduleScreenContendDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            ScheduleScreenContend(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                lessons = TestData.getLessons(),
                week = TestData.getWeek(),
                weekType = "Lower",
                onEditClick = {},
                onCardClick = {},
                onNextWeekClick = {},
                onDayClick = {},
                onCurrentWeekClick = {}
            )
        }
    }
}