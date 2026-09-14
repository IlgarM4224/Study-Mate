package com.studymate.ui.screens.components.scheduleScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.data.Day
import com.studymate.data.TestData
import com.studymate.ui.screens.WeekUiState
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun WeekRowAndType(
    modifier: Modifier,
    weekUiState: WeekUiState,
    onDayClick: (Day) -> Unit = {},
    onCurrentWeekClick: () -> Unit,
    onNextWeekClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        WeekType(
            type = weekUiState.weekType,
            isCurrentWeek = weekUiState.isCurrentWeek,
            onCurrentWeekClick = onCurrentWeekClick,
            onNextWeekClick = onNextWeekClick,
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(Modifier.height(4.dp))

        WeekRow(
            week = weekUiState.week,
            selectedDay = weekUiState.selectedDay,
            onDayClick = onDayClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
private fun WeekType(
    modifier: Modifier = Modifier,
    type: String,
    isCurrentWeek: Boolean = true,
    onCurrentWeekClick: () -> Unit,
    onNextWeekClick: () -> Unit
) {
    Column( modifier = modifier ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
        ){
            WeekChoiceButton(
                label = "Current",
                onClick = onCurrentWeekClick,
                isCurrentWeek = isCurrentWeek,
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
                modifier = Modifier.weight(1f)
            )

            WeekChoiceButton(
                label = "Next",
                onClick = onNextWeekClick,
                isCurrentWeek = !isCurrentWeek,
                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = "$type week",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
private fun WeekChoiceButton(
    label: String,
    onClick: () -> Unit,
    isCurrentWeek: Boolean,
    shape: RoundedCornerShape? = null,
    modifier: Modifier
) {
    val colorScheme = if (isCurrentWeek) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent
    Box(modifier = modifier) {
        TextButton(
            onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                containerColor = colorScheme,
            ),
            shape = shape ?: ButtonDefaults.textShape,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = label)
        }
    }
}

@Composable
private fun WeekRow(
    modifier: Modifier = Modifier,
    week: List<Day>,
    selectedDay: Day,
    onDayClick: (Day) -> Unit = {}
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .clip(RoundedCornerShape(12.dp))
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        week.forEach { day ->
            DayChip(
                day = day.day,
                date = day.date,
                selected = day == selectedDay,
                onClick = { onDayClick(day) },
            )
            Spacer(Modifier.width(4.dp))
        }
    }
}

// Day button
private data class DayChipColor(
    val text: Color,
    val background: Color
)
@Composable
private fun DayChip(
    modifier: Modifier = Modifier,
    day: String,
    date: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val colorScheme: DayChipColor = when (selected) {
        true -> DayChipColor(
            text = MaterialTheme.colorScheme.onPrimaryContainer,
            background = MaterialTheme.colorScheme.primaryContainer
        )
        false -> DayChipColor(
            text = MaterialTheme.colorScheme.onSurfaceVariant,
            background = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = colorScheme.background)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.labelMedium,
            color = colorScheme.text
        )

        Spacer(Modifier.padding(2.dp))

        Text(
            text = date,
            style = MaterialTheme.typography.labelSmall,
            color = colorScheme.text
        )
    }
}

/**
 * Week Row + Week Type
 */

@Preview(group = "Week Row And Type")
@Composable
fun WeekRowAndTypePreview() {
    StudyMateTheme {
        Surface {
            WeekRowAndType(
                weekUiState = WeekUiState(
                    week = TestData.getWeek(),
                    weekType = "Upper",
                    isCurrentWeek = true,
                    selectedDay = TestData.getWeek()[0],
                ),
                onDayClick = {},
                onNextWeekClick = {},
                onCurrentWeekClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Preview(group = "Week Row And Type")
@Composable
fun WeekRowAndTypeDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            WeekRowAndType(
                weekUiState = WeekUiState(
                    week = TestData.getWeek(),
                    weekType = "Upper",
                    isCurrentWeek = false,
                    selectedDay = TestData.getWeek()[0],
                ),
                onDayClick = {},
                onNextWeekClick = {},
                onCurrentWeekClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

/**
 * Week Type preview
 */

@Preview(showSystemUi = false, group = "Week Type")
@Composable
fun WeekTypePreview() {
    StudyMateTheme {
        Surface {
            WeekType(
                type = "Lower",
                isCurrentWeek = true,
                onCurrentWeekClick = {},
                onNextWeekClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showSystemUi = false, group = "Week Type")
@Composable
fun WeekTypeDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            WeekType(
                type = "Upper",
                isCurrentWeek = true,
                onCurrentWeekClick = {},
                onNextWeekClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

/**
 * Week Row preview
 */

@Preview(showSystemUi = false, group = "Week Row")
@Composable
fun WeekRowPreview() {
    StudyMateTheme {
        Surface {
            WeekRow(
                modifier = Modifier.fillMaxWidth(),
                week = TestData.getWeek(),
                selectedDay = TestData.getWeek()[0]
            )
        }
    }
}

@Preview(showSystemUi = false, group = "Week Row")
@Composable
fun WeekRowDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            WeekRow(
                modifier = Modifier.fillMaxWidth(),
                week = TestData.getWeek(),
                selectedDay = TestData.getWeek()[2]
            )
        }
    }
}

/**
 * Day Chip preview
 */

@Preview(group = "Day Chip")
@Composable
fun SelectedDayChipPreview() {
    StudyMateTheme {
        Surface {
            DayChip(
                day = "Mn",
                date = "13 may",
                selected = true,
                onClick = {},
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(group = "Day Chip")
@Composable
fun SelectedDayChipDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            DayChip(
                day = "Mn",
                date = "13 may",
                selected = true,
                onClick = {},
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(group = "Day Chip")
@Composable
fun DayChipPreview() {
    StudyMateTheme {
        Surface {
            DayChip(
                day = "Mn",
                date = "13 may",
                selected = false,
                onClick = {},
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(group = "Day Chip")
@Composable
fun DayChipDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            DayChip(
                day = "Mn",
                date = "13 may",
                selected = false,
                onClick = {},
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}