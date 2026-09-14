package com.studymate.ui.screens.components.general

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.studymate.ui.screens.components.subjectScreen.LimitButton

@Composable
fun TwoButtons(
    modifier: Modifier = Modifier,
    leftLabel: String,
    leftLabelColor: Color = MaterialTheme.colorScheme.primary,
    leftColors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    leftIcon: ImageVector? = null,
    leftIconTint: Color = MaterialTheme.colorScheme.primary,
    leftOnClick: () -> Unit,
    leftEnabled: Boolean = true,
    rightLabel: String,
    rightLabelColor: Color = Color.Unspecified,
    rightColors: ButtonColors =  ButtonDefaults.buttonColors(),
    rightIcon: ImageVector? = null,
    rightIconTint: Color = MaterialTheme.colorScheme.onPrimary,
    rightOnClick: () -> Unit,
    rightEnabled: Boolean = true,
    spacer: Float? = null,
    spacerDp: Dp? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LimitButton(
            buttonColors = leftColors,
            icon = leftIcon,
            label = leftLabel,
            labelColor = leftLabelColor,
            tint = leftIconTint,
            onClick = leftOnClick,
            enabled = leftEnabled
        )

        if (spacer != null) Spacer(Modifier.weight(spacer))
        else if (spacerDp != null) Spacer(Modifier.width(spacerDp))

        LimitButton(
            buttonColors = rightColors,
            icon = rightIcon,
            label = rightLabel,
            labelColor = rightLabelColor,
            tint = rightIconTint,
            onClick = rightOnClick,
            enabled = rightEnabled,
        )
    }
}