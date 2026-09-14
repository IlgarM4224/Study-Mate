package com.studymate.ui.screens.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Preview
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studymate.ui.theme.StudyMateTheme

/**
 * A small rounded badge/chip that displays a label and an optional leading icon.
 *
 * Commonly used for tags, status indicators, or compact labels inside cards and lists.
 *
 * @param modifier Modifier applied to the root [Box]
 * @param label Text content of the chip
 * @param icon Optional leading icon displayed before the label
 * @param iconSize Optional size of the icon. If null, the default icon size is used
 * @param shape Shape of the chip background. Defaults to [RoundedCornerShape] with 8.dp radius
 * @param textStyle Typography style applied to the label. Defaults to bodyMedium
 * @param textColor Color of the label text. Defaults to onPrimaryContainer
 * @param tint Color applied to the optional icon. Defaults to onPrimaryContainer
 * @param backgroundColor Background color of the chip. Defaults to primaryContainer
 */
@Composable
fun Chip(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector? = null,
    iconSize: Dp? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color =  MaterialTheme.colorScheme.onPrimaryContainer,
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Optional leading icon
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tint,
                    modifier = if(iconSize == null) Modifier else Modifier.size(16.dp)
                )

                Spacer(Modifier.width(8.dp))
            }

            // Chip label
            Text(
                text = label,
                style = textStyle,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    StudyMateTheme {
        Surface {
            Chip(
                label = "test",
                icon = Icons.Outlined.Preview,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun ChipDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            Chip(
                label = "test",
                icon = Icons.Outlined.Preview,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}