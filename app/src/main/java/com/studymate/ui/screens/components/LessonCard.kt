package com.studymate.ui.screens.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.data.Lesson
import com.studymate.data.TestData
import com.studymate.ui.theme.StudyMateTheme


@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    name: String,
    teacher: String,
    type: String,
    location: String,
    startTime: String
) {
    val smallPadding = 8.dp
    ElevatedCard (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column( modifier = Modifier.padding(8.dp) ) {
            LessonName(
                name = name,
                onEditClick = {},
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

            LessonTypeLocationTime(
                type = type,
                location = location,
                startTime = startTime,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(smallPadding)
            )
        }
    }
}

@Composable
private fun LessonName(
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
    startTime: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LessonTypeChip(type = type)

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

        Text(
            text = startTime,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun LessonTypeChip(type: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun LessonsList(
    modifier: Modifier = Modifier,
    lessons: List<Lesson> = emptyList()
) {
    LazyColumn(modifier = modifier) {
        items(lessons.size) { lesson ->
            LessonCard(
                name = lessons[lesson].name,
                teacher = lessons[lesson].teacher,
                type = lessons[lesson].type,
                location = lessons[lesson].location,
                startTime = lessons[lesson].startTime,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

// Preview section

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
                startTime = "8:30",
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
                startTime = "8:30",
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
                lessons = TestData.getLessons()
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
                lessons = TestData.getLessons()
            )
        }
    }
}