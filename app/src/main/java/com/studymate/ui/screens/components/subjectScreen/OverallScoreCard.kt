package com.studymate.ui.screens.components.subjectScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.screens.components.others.CardLabel
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun OverallScoreCard(
    modifier: Modifier = Modifier,
    overallScore: Float,
    averageColloquium: Float = 0f,
    averageSeminar: Float = 0f,
    independentWork: Int = 0,
    attendanceScore: Float = 0f,
    maxScore: Int,
) {
    ElevatedCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            CardLabel(leftLabel = "Overall score")

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OverallScoreDiagram(
                    overallScore = overallScore,
                    maxScore = maxScore,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )

                Spacer(Modifier.width(16.dp))

                OverallScoreDiagramComponents(
                    modifier = Modifier
                        .weight(1f),
                    colloquiumScore = averageColloquium,
                    seminarScore = averageSeminar,
                    independentWork = independentWork,
                    attendanceScore = attendanceScore
                )
            }
        }
    }
}

@Composable
private fun OverallScoreDiagramComponents(
    modifier: Modifier = Modifier,
    colloquiumScore: Float = 0f,
    seminarScore: Float = 0f,
    independentWork: Int = 0,
    attendanceScore: Float = 0f
) {
    Column(modifier = modifier) {
        OverallScoreElement(
            name = "Average Colloquium",
            score = colloquiumScore,
            icon = Icons.Outlined.School
        )

        Spacer(Modifier.height(8.dp))

        OverallScoreElement(
            name = "Average Seminar",
            score = seminarScore,
            icon = Icons.Outlined.Person
        )

        Spacer(Modifier.height(8.dp))

        OverallScoreElement(
            name = "Independent Work",
            score = independentWork.toFloat(),
            icon = Icons.Outlined.ContactPage
        )

        Spacer(Modifier.height(8.dp))

        OverallScoreElement(
            name = "Attendance Score",
            score = attendanceScore,
            icon = Icons.Outlined.CalendarMonth
        )
    }
}

@Composable
private fun OverallScoreElement(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    name: String,
    score: Float,
    maxScore: Int = 10
) {
    Row(modifier = modifier) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = iconDescription
            )

            Spacer(Modifier.width(4.dp))

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "$score / $maxScore",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun OverallScoreDiagram(
    modifier: Modifier = Modifier,
    overallScore: Float,
    maxScore: Int
) {
    val animatedProgress by animateFloatAsState(
        targetValue = overallScore / maxScore.toFloat(),
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "progress_animation"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
            strokeWidth = 8.dp,
            strokeCap = StrokeCap.Round,
            gapSize = 0.dp
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = overallScore.toString(),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.alignByBaseline()
            )

            Text(
                text = " / $maxScore",
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}

private val modifierForPreview = Modifier.fillMaxWidth().padding(16.dp)

@Preview(group = "Overall Score Card")
@Composable
fun OverallScoreCardPreview() {
    StudyMateTheme {
        Surface {
            OverallScoreCard(
                maxScore = 50,
                overallScore = 12f,
                modifier = modifierForPreview
            )
        }
    }
}

@Preview(group = "Overall Score Card")
@Composable
fun OverallScoreCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            OverallScoreCard(
                maxScore = 50,
                overallScore = 42.89f,
                modifier = modifierForPreview
            )
        }
    }
}

@Preview(group = "Overall Score Diagram Components")
@Composable
fun OverallScoreDiagramComponentsPreview() {
    StudyMateTheme {
        Surface {
            OverallScoreDiagramComponents(
                modifier = modifierForPreview
            )
        }
    }
}

@Preview(group = "Overall Score Diagram Components")
@Composable
fun OverallScoreDiagramComponentsDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            OverallScoreDiagramComponents(
                modifier = modifierForPreview
            )
        }
    }
}

@Preview(group = "Overall Score Element")
@Composable
fun OverallScoreElementPreview() {
    StudyMateTheme {
        Surface {
            OverallScoreElement(
                modifier = modifierForPreview,
                name = "Average Colloquium",
                score = 9.7f,
                icon = Icons.Outlined.School
            )
        }
    }
}

@Preview(group = "Overall Score Element")
@Composable
fun OverallScoreElementDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            OverallScoreElement(
                modifier = modifierForPreview,
                name = "Average Colloquium",
                score = 9.7f,
                icon = Icons.Outlined.School
            )
        }
    }
}

@Preview(group = "Overall Score Diagram")
@Composable
fun OverallScoreDiagramPreview() {
    StudyMateTheme {
        Surface {
            OverallScoreDiagram(
                overallScore = 41.4f,
                maxScore = 50,
                modifier = Modifier
                    .height(170.dp)
                    .aspectRatio(1f)
            )
        }
    }
}

@Preview(group = "Overall Score Diagram")
@Composable
fun OverallScoreDiagramDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            OverallScoreDiagram(
                overallScore = 41.4f,
                maxScore = 50,
                modifier = Modifier
                    .height(170.dp)
                    .aspectRatio(1f)
            )
        }
    }
}
