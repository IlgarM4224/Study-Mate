package com.studymate.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.studymate.data.Lesson
import com.studymate.data.TestData
import com.studymate.ui.screens.components.LessonTopAppBar
import com.studymate.ui.screens.components.LessonsList
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    lessons: List<Lesson> = emptyList()
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
        LessonsList(
            lessons = lessons,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScheduleScreenLightPreview() {
    StudyMateTheme {
        Surface {
            ScheduleScreen(
                modifier = Modifier.fillMaxSize(),
                lessons = TestData.getLessons()
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScheduleScreenDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            ScheduleScreen(
                modifier = Modifier.fillMaxSize(),
                lessons = TestData.getLessons()
            )
        }
    }
}