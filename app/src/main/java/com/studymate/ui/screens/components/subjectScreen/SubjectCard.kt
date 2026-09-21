package com.studymate.ui.screens.components.subjectScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studymate.data.LessonType
import com.studymate.data.Subject
import com.studymate.ui.screens.components.scheduleScreen.LessonTypeChip
import com.studymate.ui.screens.components.scheduleScreen.SubjectName

@Composable
fun SubjectCard(
    modifier: Modifier = Modifier,
    subject: Subject,
    onClick: (Int) -> Unit,
    onEditClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { onClick(subject.id) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            SubjectName(
                name = subject.name,
                onEditClick = onEditClick,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ){
                if (subject.teacherLecture != null) {
                    LessonTypeChip(
                        text = subject.teacherLecture,
                        iconSize = 24.dp,
                        textStyle = MaterialTheme.typography.bodySmall,
                        type = LessonType.LECTURES,
                        shape = RoundedCornerShape(16.dp)
                    )
                }

                Spacer(Modifier.width(16.dp))

                if (subject.teacherSeminar != null) {
                    LessonTypeChip(
                        text = subject.teacherSeminar,
                        iconSize = 24.dp,
                        textStyle = MaterialTheme.typography.bodySmall,
                        type = LessonType.SEMINAR,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
        }
    }
}