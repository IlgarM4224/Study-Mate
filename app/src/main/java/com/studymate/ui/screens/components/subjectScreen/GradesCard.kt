package com.studymate.ui.screens.components.subjectScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.screens.GradeType
import com.studymate.ui.screens.components.others.CardLabel
import com.studymate.ui.screens.components.others.RoundedIcon
import com.studymate.ui.theme.StudyMateTheme


@Composable
fun GradesCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    gradeType: GradeType,
    onAddClick: (GradeType) -> Unit = {},
    onGradeClick: () -> Unit = {},
    gradesList: List<Int> = emptyList()
) {
    ElevatedCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                RoundedIcon(icon = icon)

                Spacer(Modifier.width(8.dp))

                CardLabel(leftLabel = label)
            }

            Spacer(Modifier.height(8.dp))

            GradesRow(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth(),
                gradesList = gradesList,
                gradeType = gradeType,
                onAddClick = onAddClick,
                onGradeClick = onGradeClick
            )
        }
    }
}

@Composable
private fun GradesRow(
    modifier: Modifier = Modifier,
    gradesList: List<Int> = emptyList(),
    gradeType: GradeType,
    onAddClick: (GradeType) -> Unit = {},
    onGradeClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        gradesList.forEach{
            Grade(
                grade = it,
                onGradeClick = onGradeClick,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        OutlinedButton(
            onClick = { onAddClick(gradeType) },
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Composable
private fun Grade(
    modifier: Modifier = Modifier,
    grade: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    onGradeClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onGradeClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = grade.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                )
        )
    }
}



@Preview(group = "Grades Card")
@Composable
fun GradesCardPreview() {
    StudyMateTheme {
        Surface {
            GradesCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = "Grades for Seminar",
                gradeType = GradeType.SEMINAR,
                icon = Icons.Outlined.People,
                gradesList = listOf(7,8,8)
            )
        }
    }
}

@Preview(group = "Grades Card")
@Composable
fun GradesCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            GradesCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = "Grades for Colloquium",
                gradeType = GradeType.COLLOQUIUM,
                icon = Icons.Outlined.School,
                gradesList = listOf(7,8,8)
            )
        }
    }
}