package com.studymate.ui.screens.components.general

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studymate.ui.theme.StudyMateTheme

/**
 * A horizontal row containing two buttons side by side.
 *
 * Commonly used for paired actions (e.g. Cancel / Confirm).
 * Supports optional icons, custom colors, enabled/disabled states,
 * and flexible spacing between the buttons.
 *
 * @param modifier Modifier applied to the root [Row]
 * @param leftLabel Text label for the left button
 * @param leftLabelColor Color of the left button's text. Defaults to primary
 * @param leftColors Button colors for the left button. Defaults to outlined style
 * @param leftIcon Optional icon displayed on the left button
 * @param leftIconTint Tint color applied to the left button's icon. Defaults to primary
 * @param leftOnClick Callback invoked when the left button is clicked
 * @param leftEnabled Whether the left button is enabled. Defaults to true
 * @param rightLabel Text label for the right button
 * @param rightLabelColor Color of the right button's text. Defaults to Unspecified (uses button theme)
 * @param rightColors Button colors for the right button. Defaults to filled style
 * @param rightIcon Optional icon displayed on the right button
 * @param rightIconTint Tint color applied to the right button's icon. Defaults to onPrimary
 * @param rightOnClick Callback invoked when the right button is clicked
 * @param rightEnabled Whether the right button is enabled. Defaults to true
 * @param spacer Optional weight-based flexible space between the buttons
 * @param spacerDp Optional fixed-width space between the buttons (used if [spacer] is null)
 */
@Composable
fun TwoButtons(
    modifier: Modifier = Modifier,
    leftOnClick: () -> Unit,
    leftLabel: String,
    leftLabelColor: Color = MaterialTheme.colorScheme.primary,
    leftColors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    leftIcon: ImageVector? = null,
    leftIconTint: Color = MaterialTheme.colorScheme.primary,
    leftEnabled: Boolean = true,
    rightOnClick: () -> Unit,
    rightLabel: String,
    rightLabelColor: Color = Color.Unspecified,
    rightColors: ButtonColors =  ButtonDefaults.buttonColors(),
    rightIcon: ImageVector? = null,
    rightIconTint: Color = MaterialTheme.colorScheme.onPrimary,
    rightEnabled: Boolean = true,
    spacer: Float? = null,
    spacerDp: Dp? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedButton(
            buttonColors = leftColors,
            icon = leftIcon,
            label = leftLabel,
            labelColor = leftLabelColor,
            tint = leftIconTint,
            onClick = leftOnClick,
            enabled = leftEnabled,
        )

        if (spacer != null) Spacer(Modifier.weight(spacer))
        else if (spacerDp != null) Spacer(Modifier.width(spacerDp))

        RoundedButton(
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

@Preview
@Composable
fun TwoButtonsPreview() {
    StudyMateTheme {
        Surface {
            TwoButtons(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                leftLabel = "Remove Missed",
                leftOnClick = {},
                leftIcon = Icons.Default.Remove,
                rightLabel = "Add Missed",
                rightOnClick = {},
                rightIcon = Icons.Default.Add,
                spacer = 1f
            )
        }
    }
}