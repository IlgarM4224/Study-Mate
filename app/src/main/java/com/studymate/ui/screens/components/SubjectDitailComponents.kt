package com.studymate.ui.screens.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.studymate.R
import com.studymate.ui.screens.GradeType
import com.studymate.ui.screens.LessonType
import com.studymate.ui.theme.StudyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismissRequest: () -> Unit,
    label: String,
    gradeType: GradeType = GradeType.SEMINAR,
    addGrade: (GradeType, Int) -> Unit,
    isError: Boolean = false,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        val state = rememberTextFieldState()
        val gradeValue = state.text.toString()

        GradeBottomSheetContent(
            label = label,
            state = state,
            onCancel = onDismissRequest,
            onApply = {
                if (gradeValue.isDigitsOnly()) {
                    addGrade(gradeType, gradeValue.toInt())
                }
            },
            isError = isError
        )
    }
}

@Composable
fun GradeBottomSheetContent(
    modifier: Modifier = Modifier,
    state: TextFieldState = rememberTextFieldState(),
    label: String,
    onCancel: () -> Unit,
    onApply: () -> Unit,
    isError: Boolean = false,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            shape = RoundedCornerShape(8.dp),
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            state = state,
            isError = isError,
        )

        Spacer(Modifier.height(16.dp))

        TwoButtons(
            leftLabel = "Cancel",
            leftIcon = Icons.Outlined.Cancel,
            leftOnClick = onCancel,
            rightLabel = "Apply",
            rightOnClick = onApply,
            rightIcon = Icons.Outlined.Done,
            spacer = 1f,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
@Composable
fun SubjectNameCard(
    modifier: Modifier = Modifier,
    onArrowClick: () -> Unit = {},
    showMore: Boolean = false,
    subjectName: String,
    subjectHours: Int? = null,
    subjectCredit: Int? = null,
    lecture: String? = null,
    seminar: String? = null
) {
    Card(modifier = modifier) {
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

                Spacer(Modifier.width(8.dp))

                CardLabel(
                    leftLabel = subjectName,
                    leftLabelColor = MaterialTheme.colorScheme.primary,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    leftMaxLine = if (showMore) 2 else 1,
                    modifier = Modifier.weight(1f),
                )

                IconButton(onClick = onArrowClick) {
                    Icon(
                        imageVector = if (showMore) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(visible = showMore) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    SubjectInfo(
                        hours = subjectHours,
                        credit = subjectCredit,
                        cardColors = CardDefaults.cardColors().copy(
                            containerColor = MaterialTheme.colorScheme.outlineVariant,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    SubjectTeachersCard(
                        cardColors = CardDefaults.cardColors().copy(
                            containerColor = MaterialTheme.colorScheme.outlineVariant,
                        ),
                        lecture = lecture,
                        seminar = seminar,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun SubjectInfo(
    modifier: Modifier = Modifier,
    hours: Int? = null,
    credit: Int? = null,
    cardColors: CardColors = CardDefaults.cardColors()
) {
    Card(modifier = modifier, colors = cardColors) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(8.dp)
        ) {
            SubjectInfoElement(
                icon = Icons.Outlined.Timer,
                label = "Hours",
                value = hours,
                modifier = Modifier.weight(1f)
            )

            VerticalDivider(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            SubjectInfoElement(
                icon = Icons.Outlined.CreditCard,
                label = "Credit",
                value = credit,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SubjectInfoElement(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconSize: Dp = 30.dp,
    tint: Color = MaterialTheme.colorScheme.primary,
    label: String,
    labelStyle: TextStyle = MaterialTheme.typography.bodySmall,
    labelColor: Color =  Color.Unspecified,
    value: Int? = null,
    valueStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    valueColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = null,
            modifier = Modifier.size(iconSize)
        )

        Spacer(Modifier.width(8.dp))

        Column {
            Text(
                text = label,
                style = labelStyle,
                color = labelColor
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = value?.toString() ?: stringResource(R.string.no_info),
                style = valueStyle,
                color = valueColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )
        }
    }
}

@Composable
private fun SubjectTeachersCard(
    modifier: Modifier = Modifier,
    cardColors: CardColors = CardDefaults.cardColors(),
    lecture: String? = null,
    seminar: String? = null
) {
    Card(
        modifier = modifier,
        colors = cardColors
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RoundedIcon(
                    icon = Icons.Outlined.Person,
                    backgroundColor = Color.Unspecified
                )

                Spacer(Modifier.width(8.dp))

                CardLabel(leftLabel = "Teachers")
            }

            Spacer(Modifier.height(16.dp))

            SubjectTeacher(
                modifier = Modifier.fillMaxWidth(),
                type = LessonType.LECTURES,
                name = lecture ?: stringResource(R.string.no_info)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp)
            )

            SubjectTeacher(
                modifier = Modifier.fillMaxWidth(),
                type = LessonType.SEMINAR,
                name = seminar ?: stringResource(R.string.no_info)
            )
        }
    }
}


@Composable
private fun SubjectTeacher(
    modifier: Modifier = Modifier,
    type: LessonType,
    name: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LessonTypeChip(
            type = type,
            textStyle = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
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

@Composable
fun GradesCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    gradeType: GradeType,
    onAddClick: (GradeType) -> Unit = {},
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
                gradeType = gradeType,
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
    gradeType: GradeType,
    onAddClick: (GradeType) -> Unit = {},
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
            onClick = { onAddClick(gradeType) },
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

@Composable
private fun TwoButtons(
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

@Composable
private fun LimitButton(
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

@Preview(group = "Grade Bottom Sheet")
@Composable
fun GradeBottomSheetPreview() {
    StudyMateTheme {
        Surface {
            GradeBottomSheetContent(
                onApply = {},
                onCancel = {},
                label = "Seminar grade",
                isError = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}

@Preview(group = "Grade Bottom Sheet")
@Composable
fun GradeBottomSheetDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            GradeBottomSheetContent(
                onCancel = {},
                onApply = {},
                label = "Seminar grade",
                isError = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}

@Preview(group = "Subject Teacher")
@Composable
fun SubjectTeachersCardPreview() {
    StudyMateTheme {
        Surface {
            SubjectTeachersCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                lecture = "Hicran",
                seminar = "Ramzi"
            )
        }
    }
}

@Preview(group = "Subject Teacher")
@Composable
fun SubjectTeachersCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectTeachersCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                lecture = "Hicran",
                seminar = "Ramzi"
            )
        }
    }
}

@Preview(group = "Subject Teacher")
@Composable
fun SubjectTeacherPreview() {
    StudyMateTheme {
        Surface {
            SubjectTeacher(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                type = LessonType.LECTURES,
                name = "Hicran"
            )
        }
    }
}

@Preview(group = "Subject Teacher")
@Composable
fun SubjectTeacherDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectTeacher(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                type = LessonType.LECTURES,
                name = "Hicran"
            )
        }
    }
}


@Preview(group = "Subject Info")
@Composable
fun SubjectInfoPreview() {
    StudyMateTheme {
        Surface {
            SubjectInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(group = "Subject Info")
@Composable
fun SubjectInfoDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                showMore = true,
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
                subjectHours = 60,
                subjectCredit = 6,
                lecture = "Hicran",
                seminar = "Ramzi",
                showMore = true,
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
                gradeType = GradeType.SEMINAR,
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
                gradeType = GradeType.COLLOQUIUM,
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

