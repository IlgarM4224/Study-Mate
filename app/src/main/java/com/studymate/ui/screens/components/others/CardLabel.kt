package com.studymate.ui.screens.components.others

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.screens.components.scheduleScreen.Chip
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun CardLabel(
    modifier: Modifier = Modifier,
    leftLabel: String,
    rightLabel: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    leftLabelColor: Color = MaterialTheme.colorScheme.onSurface,
    rightLabelColor: Color = MaterialTheme.colorScheme.primary,
    leftMaxLine: Int = 1
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = leftLabel,
            style = textStyle,
            color = leftLabelColor,
            maxLines = leftMaxLine,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )

        if(rightLabel != null) {
            Chip(
                label = rightLabel,
                textStyle = MaterialTheme.typography.bodyMedium,
                textColor = rightLabelColor,
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardLabelPreview() {
    StudyMateTheme {
        Surface {
            CardLabel(
                leftLabel = "Missed Lessons",
                rightLabel = "4/9",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardLabelDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            CardLabel(
                leftLabel = "Missed Lessons",
                rightLabel = "4/9",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}