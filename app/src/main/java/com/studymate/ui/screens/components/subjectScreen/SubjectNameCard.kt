package com.studymate.ui.screens.components.subjectScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studymate.R
import com.studymate.data.LessonType
import com.studymate.ui.screens.components.general.CardLabel
import com.studymate.ui.screens.components.general.RoundedIcon
import com.studymate.ui.screens.components.scheduleScreen.LessonTypeChip
import com.studymate.ui.theme.StudyMateTheme

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