package com.studymate.ui.screens.components.subjectDetailScreen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studymate.data.GradeType

@Composable
fun GradeDropdownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    onDismissRequest: () -> Unit = {},
    gradeId: Int = 0,
    gradeType: GradeType,
    onEditClick: (GradeType, Int) -> Unit = {_, _ ->},
    onDeleteClick: (GradeType, Int) -> Unit = {_, _ ->},
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = "Change",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) },
            onClick = { onEditClick(gradeType,gradeId) }
        )

        DropdownMenuItem(
            text = {
                Text(
                    text = "Delete",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            },
            onClick = { onDeleteClick(gradeType, gradeId) }
        )
    }
}