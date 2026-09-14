package com.studymate.ui.screens.components.others

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
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
    onNavigateClick: () -> Unit = {},
    canNavigateBack: Boolean = false,
    showMore: Boolean = false,
    onMoreClick: () -> Unit = {},
    title: String?
) {
    TopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        } ,

        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },

        actions = {
            if (showMore) {
                IconButton(onClick = onMoreClick) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },

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
        tonalElevation = 0.dp,
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

@Preview(group = "Bottom App Bar")
@Composable
fun BottomAppBarPreview() {
    StudyMateTheme {
        Surface {
            StudyMateBottomAppBar(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(group = "Bottom App Bar")
@Composable
fun BottomAppBarDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            StudyMateBottomAppBar(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(group = "Top App Bar")
@Composable
fun TopAppBarPreview() {
    StudyMateTheme {
        Surface {
            StudyMateTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Schedule",
                canNavigateBack = true,
                showMore = true
            )
        }
    }
}

@Preview(group = "Top App Bar")
@Composable
fun TopAppBarDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            StudyMateTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Schedule",
                canNavigateBack = true,
                showMore = true
            )
        }
    }
}