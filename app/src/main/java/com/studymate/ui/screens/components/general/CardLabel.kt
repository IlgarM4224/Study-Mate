package com.studymate.ui.screens.components.general

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
import com.studymate.ui.theme.StudyMateTheme



/**
 * A composable that displays a horizontal label row typically used inside cards.
 *
 * Shows a primary left label and an optional right-side chip/badge.
 * The left label takes available space and truncates with ellipsis if needed,
 * while the right chip stays visible on the trailing edge.
 *
 * @param modifier Modifier applied to the root [Row]
 * @param leftLabel Primary text displayed on the left side
 * @param rightLabel Optional text for the right-side chip/badge. If null, the chip is not shown
 * @param textStyle Text style applied to the left label. Defaults to bodyLarge from the current theme
 * @param leftLabelColor Color of the left label. Defaults to onSurface from the current theme
 * @param rightLabelColor Color of the text inside the right chip. Defaults to primary from the current theme
 * @param leftMaxLine Maximum number of lines for the left label before truncation. Defaults to 1
 */
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
        // Main left label; weight(1f, fill = false) allows it to take remaining space without pushing the chip out
        Text(
            text = leftLabel,
            style = textStyle,
            color = leftLabelColor,
            maxLines = leftMaxLine,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )

        // Renders an optional badge chip on the right side when a label string is provided
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