package com.studymate.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.studymate.ui.theme.StudyMateTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonTopAppBar(
    modifier: Modifier,
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        } ,
        modifier = modifier
    )
}

@Preview
@Composable
fun LessonTopAppBarPreview() {
    StudyMateTheme {
        Surface {
            LessonTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Schedule"
            )
        }
    }
}

@Preview
@Composable
fun LessonTopAppBarDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            LessonTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Schedule"
            )
        }
    }
}