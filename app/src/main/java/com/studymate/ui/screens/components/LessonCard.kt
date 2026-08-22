package com.studymate.ui.screens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.data.TestData
import com.studymate.ui.screens.LessonCardUIState
import com.studymate.ui.theme.StudyMateTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    name: String,
    teacher: String,
    type: String,
    location: String,
    startTime: LocalTime,
    isCardClicked: Boolean = false,
    onCardClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val smallPadding = 8.dp
    Card (
        modifier = modifier,
        onClick = { onCardClick() }
    ) {
        Column( modifier = Modifier.padding(8.dp) ) {
            SubjectName(
                name = name,
                onEditClick = onEditClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(smallPadding)
            )

            Text(
                text = teacher,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = smallPadding)
            )

            Spacer(Modifier.height(12.dp))

            AnimatedVisibility(visible = isCardClicked) {
                LessonStartEndTime(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(smallPadding),
                    startTime = startTime,
                    endTime = startTime.plusMinutes(90)
                )
            }

            LessonTypeLocationTime(
                type = type,
                location = location,
                startTime = startTime,
                showTime = !isCardClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(smallPadding)
            )
        }
    }
}

@Composable
private fun LessonStartEndTime(
    modifier: Modifier = Modifier,
    startTime: LocalTime,
    endTime: LocalTime
) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = startTime.format(timeFormatter),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.width(8.dp))

        LinearProgressIndicator(
            progress = { 0.6f } ,
            color = MaterialTheme.colorScheme.primary,
            gapSize = 0.dp,
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = endTime.format(timeFormatter),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SubjectName(
    modifier: Modifier = Modifier,
    name: String,
    onEditClick: () -> Unit,
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onEditClick,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit lesson",
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun LessonTypeLocationTime(
    modifier: Modifier = Modifier,
    type: String,
    location: String,
    startTime: LocalTime?,
    showTime: Boolean
) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Chip(label = type, textStyle = MaterialTheme.typography.labelSmall)

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = location,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (startTime != null) {
            AnimatedVisibility(visible = showTime) {
                Text(
                    text = startTime.format(timeFormatter),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    label: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color =  MaterialTheme.colorScheme.onPrimaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = textStyle,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}

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

// Preview section

@Preview
@Composable
fun LessonStartEndTimePreview(){
    StudyMateTheme {
        Surface {
            LessonStartEndTime(
                startTime = LocalTime.of(8,30),
                endTime = LocalTime.of(8,30).plusMinutes(90),
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun LessonStartEndTimeDarkPreview(){
    StudyMateTheme(darkTheme = true) {
        Surface {
            LessonStartEndTime(
                startTime = LocalTime.of(8,30),
                endTime = LocalTime.of(8,30).plusMinutes(90),
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun LessonCardPreview() {
    StudyMateTheme(darkTheme = false) {
        Surface {
            LessonCard(
                name = "Programming",
                teacher = "Abbas",
                type = "Lecture",
                location = "301",
                startTime = LocalTime.of(8,30),
                isCardClicked = true,
                onCardClick = {},
                onEditClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun LessonCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            LessonCard(
                name = "Programming",
                teacher = "Abbas",
                type = "Lecture",
                location = "301",
                startTime = LocalTime.of(8,30),
                isCardClicked = true,
                onCardClick = {},
                onEditClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
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

@Preview
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