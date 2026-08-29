package com.studymate.ui.screens.components


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studymate.ui.screens.toLabel
import com.studymate.ui.theme.StudyMateTheme


@Composable
fun SubjectNameCard(
    modifier: Modifier = Modifier,
    onArrowClick: () -> Unit = {},
    showMore: Boolean = false,
    subjectName: String,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RoundedIcon(
                    icon = Icons.Outlined.Book,
                    iconSize = 40.dp,
                    backgroundColor = MaterialTheme.colorScheme.secondaryContainer
                )

                Spacer(Modifier.width(4.dp))

                CardLabel(
                    leftLabel = subjectName,
                    textStyle = MaterialTheme.typography.headlineSmall
                )

                Spacer(Modifier.weight(1f))

                IconButton(onClick = onArrowClick) {
                    Icon(
                        imageVector = if (showMore) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OverallScoreDiagram(
                    overallScore = overallScore,
                    maxScore = maxScore,
                    modifier = Modifier
                        .size(160.dp)
                        .weight(1f)
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
                    text = "${score.toLabel()} / $maxScore",
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
        ) {
            Text(
                text = overallScore.toLabel(),
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

@Composable
fun GradesCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    onAddClick: () -> Unit = {},
    onGradeClick: () -> Unit = {},
    gradesList: List<Int> = emptyList()
) {
    ElevatedCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                RoundedIcon(icon = icon)

                Spacer(Modifier.width(8.dp))

                CardLabel(leftLabel = label)
            }

            Spacer(Modifier.height(8.dp))

            GradesRow(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth(),
                gradesList = gradesList,
                onAddClick = onAddClick,
                onGradeClick = onGradeClick
            )
        }
    }
}

@Composable
private fun GradesRow(
    modifier: Modifier = Modifier,
    gradesList: List<Int> = emptyList(),
    onAddClick: () -> Unit = {},
    onGradeClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        gradesList.forEach{
            Grade(
                grade = it,
                onGradeClick = onGradeClick,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        OutlinedButton(
            onClick = onAddClick,
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Composable
private fun Grade(
    modifier: Modifier = Modifier,
    grade: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    onGradeClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onGradeClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = grade.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                )
        )
    }
}

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
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LimitButton(
                    buttonColors = ButtonDefaults.outlinedButtonColors(),
                    icon = Icons.Default.Remove,
                    label = "Remove Missed",
                    labelColor = MaterialTheme.colorScheme.primary,
                    tint = MaterialTheme.colorScheme.primary,
                    onClick = removeMissed,
                    enabled = missed > 0
                )

                Spacer(Modifier.weight(1f))

                LimitButton(
                    icon = Icons.Default.Add,
                    label = "Add Missed",
                    onClick = addMissed,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    enabled = missed < limit
                )
            }
        }
    }
}

@Composable
private fun LimitButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    icon: ImageVector,
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
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) tint else LocalContentColor.current
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = label ?: "",
            color = if (enabled) labelColor else Color.Unspecified,
        )
    }
}
@Composable
private fun RoundedIcon(
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

@Composable
private fun CardLabel(
    modifier: Modifier = Modifier,
    leftLabel: String,
    rightLabel: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    leftLabelColor: Color = MaterialTheme.colorScheme.onSurface,
    rightLabelColor: Color = MaterialTheme.colorScheme.primary,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = leftLabel,
            style = textStyle,
            color = leftLabelColor
        )

        if(rightLabel != null) {
            Chip(
                label = rightLabel,
                textColor = rightLabelColor
            )
        }
    }
}


@Preview(group = "Subject Name Card")
@Composable
fun SubjectNameCardPreview() {
    StudyMateTheme {
        Surface {
            SubjectNameCard(
                subjectName = "Programming basics",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(group = "Subject Name Card")
@Composable
fun SubjectNameCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectNameCard(
                subjectName = "Programming basics",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(group = "Overall Score Card")
@Composable
fun OverallScoreCardPreview() {
    StudyMateTheme {
        Surface {
            OverallScoreCard(
                maxScore = 50,
                overallScore = 12f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(group = "Grades Card")
@Composable
fun GradesCardPreview() {
    StudyMateTheme {
        Surface {
            GradesCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = "Grades for Seminar",
                icon = Icons.Outlined.People,
                gradesList = listOf(7,8,8)
            )
        }
    }
}

@Preview(group = "Grades Card")
@Composable
fun GradesCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            GradesCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = "Grades for Colloquium",
                icon = Icons.Outlined.School,
                gradesList = listOf(7,8,8)
            )
        }
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

