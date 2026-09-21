package com.studymate.ui.screens.components.subjectDetailScreen

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
import com.studymate.data.GradeType
import com.studymate.ui.screens.components.general.CardLabel
import com.studymate.ui.screens.components.general.RoundedIcon
import com.studymate.ui.theme.StudyMateTheme


@Composable
fun GradesCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    gradeType: GradeType,
    selectedGradeIndex: Int?,
    selectedGradeType: GradeType,
    showAddButton: Boolean,
    onAddClick: (GradeType) -> Unit = {},
    onGradeClick: (GradeType, Int) -> Unit,
    onDismissRequest: () -> Unit,
    onEditClick: (GradeType, Int) -> Unit,
    onDeleteClick: (GradeType, Int) -> Unit,
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
                selectedGradeType = selectedGradeType,
                showAddButton = showAddButton,
                selectedGradeIndex = selectedGradeIndex,
                onAddClick = onAddClick,
                onDismissRequest = onDismissRequest,
                onGradeClick = onGradeClick,
                onDeleteClick = onDeleteClick,
                onEditClick = onEditClick
            )
        }
    }
}

@Composable
private fun GradesRow(
    modifier: Modifier = Modifier,
    gradesList: List<Int> = emptyList(),
    gradeType: GradeType,
    selectedGradeIndex: Int?,
    selectedGradeType: GradeType,
    showAddButton: Boolean,
    onAddClick: (GradeType) -> Unit = {},
    onGradeClick: (GradeType, Int) -> Unit,
    onDismissRequest: () -> Unit,
    onEditClick: (GradeType, Int) -> Unit,
    onDeleteClick: (GradeType, Int) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        gradesList.forEachIndexed { id, grade ->
            Grade(
                grade = grade,
                gradeId = id,
                type = gradeType,
                expandDropdownMenu =  gradeType == selectedGradeType && selectedGradeIndex == id,
                onGradeClick = onGradeClick,
                onDismissRequest = onDismissRequest,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        if (showAddButton) {
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
}

@Composable
private fun Grade(
    modifier: Modifier = Modifier,
    grade: Int,
    gradeId: Int,
    type: GradeType,
    expandDropdownMenu: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    onGradeClick: (GradeType, Int) -> Unit,
    onDismissRequest: () -> Unit,
    onEditClick: (GradeType, Int) -> Unit,
    onDeleteClick: (GradeType, Int) -> Unit,
) {
    Column(modifier) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .clickable(onClick = { onGradeClick(type, gradeId) }),
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

        Spacer(Modifier.height(8.dp))

        GradeDropdownMenu(
            expanded = expandDropdownMenu,
            onDismissRequest = onDismissRequest,
            gradeId = gradeId,
            gradeType = type,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick
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
                gradesList = listOf(7,8,8),
                showAddButton = true,
                selectedGradeType = GradeType.SEMINAR,
                selectedGradeIndex = 0,
                onGradeClick = {_, _ -> },
                onDismissRequest = {},
                onEditClick = {_, _ ->},
                onDeleteClick = {_, _ ->}
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
                showAddButton = true,
                selectedGradeType = GradeType.SEMINAR,
                selectedGradeIndex = 0,
                gradesList = listOf(7,8,8),
                onGradeClick = {_, _ -> },
                onDismissRequest = {},
                onEditClick = {_, _ ->},
                onDeleteClick = {_, _ ->}
            )
        }
    }
}