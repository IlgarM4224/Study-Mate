package com.studymate.ui.screens.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun RoundedIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconSize: Dp = 36.dp,
    iconPadding: Dp = 4.dp,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    tint: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(iconSize)
                .clip(shape)
                .background(backgroundColor)
                .padding(iconPadding)
        )
    }
}

@Preview
@Composable
fun RoundedIconPreview() {
    StudyMateTheme {
        Surface {
            RoundedIcon(
                icon = Icons.Outlined.School,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoundedIconDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            RoundedIcon(
                icon = Icons.Outlined.School,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}