package com.studymate.ui.screens.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.navigation.NavigationDestination
import com.studymate.ui.screens.ScheduleDestination
import com.studymate.ui.screens.SubjectDestination
import com.studymate.ui.theme.StudyMateTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyMateTopAppBar(
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

@Composable
fun StudyMateBottomAppBar(
    modifier: Modifier = Modifier,
    selectedDestination: NavigationDestination = ScheduleDestination,
    navigateScheduleScreen: () -> Unit = {},
    navigateSubjectScreen: () -> Unit = {},
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            selected = selectedDestination == ScheduleDestination,
            onClick = navigateScheduleScreen,
            icon = {
                Icon(
                    imageVector = ScheduleDestination.destinationIcon,
                    contentDescription = "Schedule screen"
                )
            }
        )

        NavigationBarItem(
            selected = selectedDestination == SubjectDestination,
            onClick = navigateSubjectScreen,
            icon = {
                Icon(
                    imageVector = SubjectDestination.destinationIcon,
                    contentDescription = "Subject screen"
                )
            }
        )
    }
}

@Preview
@Composable
fun BottomAppBarPreview() {
    StudyMateTheme {
        Surface {
            StudyMateBottomAppBar(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun BottomAppBarDarkPreview() {
    StudyMateTheme {
        Surface {
            StudyMateBottomAppBar(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    StudyMateTheme {
        Surface {
            StudyMateTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Schedule"
            )
        }
    }
}

@Preview
@Composable
fun TopAppBarDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            StudyMateTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Schedule"
            )
        }
    }
}