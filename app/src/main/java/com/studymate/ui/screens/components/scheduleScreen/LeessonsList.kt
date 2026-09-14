package com.studymate.ui.screens.components.scheduleScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.data.TestData
import com.studymate.ui.screens.LessonCardUIState
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun LessonsList(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    lessonsState: LessonCardUIState,
    onCardClick: (Int) -> Unit,
    onEditClick: () -> Unit,
    weekRowAndType: @Composable () -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            weekRowAndType()
        }

        if(lessonsState.lessons?.isEmpty() ?: true) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No lessons",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            items(lessonsState.lessons.size) { id ->
                val les = lessonsState.lessons
                LessonCard(
                    name = les[id].name,
                    teacher = les[id].teacher,
                    type = les[id].type,
                    location = les[id].location,
                    startTime = les[id].startTime,
                    isCardClicked = id == lessonsState.clickedCardId,
                    onCardClick = { onCardClick(id) },
                    onEditClick = onEditClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(group = "Lessons List")
@Composable
fun LessonsListPreview(){
    StudyMateTheme {
        Surface {
            LessonsList(
                modifier = Modifier.fillMaxSize(),
                lessonsState = LessonCardUIState(lessons = TestData.getLessons()),
                onEditClick = {},
                onCardClick = {}
            )
        }
    }
}

@Preview(group = "Lessons List")
@Composable
fun LessonsListDarkPreview(){
    StudyMateTheme(darkTheme = true) {
        Surface {
            LessonsList(
                modifier = Modifier.fillMaxSize().statusBarsPadding(),
                lessonsState = LessonCardUIState(lessons = TestData.getLessons()),
                onEditClick = {},
                onCardClick = {}
            )
        }
    }
}