package com.studymate.ui.screens.components.subjectScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.screens.components.others.CardLabel
import com.studymate.ui.screens.components.others.RoundedIcon
import com.studymate.ui.screens.components.others.TwoButtons
import com.studymate.ui.theme.StudyMateTheme


@Composable
fun LimitCard(
    modifier: Modifier,
    addMissed: () -> Unit = {},
    removeMissed: () -> Unit = {},
    limit: Int,
    missed: Int
) {
    val animatedProgress by animateFloatAsState(
        targetValue = missed.toFloat()/limit,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "progress_animation"
    )

    val progressColor = lerp(
        start = MaterialTheme.colorScheme.primary,
        stop = MaterialTheme.colorScheme.error,
        fraction = animatedProgress
    )

    ElevatedCard(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RoundedIcon(icon = Icons.Outlined.CalendarToday)

                Spacer(Modifier.width(8.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CardLabel(
                        leftLabel = "Missed Lessons",
                        rightLabel = "$missed/$limit",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        gapSize = 0.dp,
                        color = progressColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            TwoButtons(
                modifier = Modifier.fillMaxWidth(),
                leftLabel = "Remove Missed",
                leftEnabled = missed > 0,
                leftIcon = Icons.Default.Remove,
                leftOnClick = removeMissed,
                rightLabel = "Add Missed",
                rightOnClick = addMissed,
                rightIcon = Icons.Default.Add,
                rightEnabled = missed < limit,
                spacer = 1f,
            )
        }
    }
}

@Composable
fun LimitButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    icon: ImageVector?,
    label: String? = null,
    labelColor: Color =  Color.Unspecified,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
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
                tint = if (enabled) tint else LocalContentColor.current
            )

            Spacer(Modifier.width(4.dp))
        }

        Text(
            text = label ?: "",
            color = if (enabled) labelColor else Color.Unspecified,
        )
    }
}

@Preview(group = "Limit card")
@Composable
fun LimitCardPreview() {
    StudyMateTheme {
        Surface {
            LimitCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                limit = 10,
                missed = 3
            )
        }
    }
}

@Preview(group = "Limit card")
@Composable
fun LimitCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            LimitCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                limit = 10,
                missed = 3
            )
        }
    }
}
