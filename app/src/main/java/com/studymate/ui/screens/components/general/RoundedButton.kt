package com.studymate.ui.screens.components.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    icon: ImageVector?,
    iconSize: Dp? = null,
    label: String? = null,
    labelStyle: TextStyle = LocalTextStyle.current,
    labelColor: Color =  Color.Unspecified,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit,
    enabled: Boolean = true,
    spacer: Dp = 4.dp,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp)
) {
    Button(
        onClick = onClick,
        shape = shape,
        colors = buttonColors,
        enabled = enabled,
        border = if(enabled) BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ) else null,
        modifier = modifier
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (enabled) tint else LocalContentColor.current,
                modifier = if(iconSize == null) Modifier else Modifier.size(iconSize)
            )

            Spacer(Modifier.width(spacer))
        }

        Text(
            text = label ?: "",
            style = labelStyle,
            color = if (enabled) labelColor else Color.Unspecified,
        )
    }
}

@Preview
@Composable
fun RoundedButtonPreview() {
    StudyMateTheme {
        Surface {
            RoundedButton(
                modifier = Modifier.padding(16.dp),
                onClick = {},
                label = "Apply",
                icon = Icons.Default.Done,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}