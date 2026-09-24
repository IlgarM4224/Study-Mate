package com.studymate.ui.screens.components.subjectAddScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.screens.components.general.SegmentedToggle
import com.studymate.ui.screens.components.general.ToggleOption
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun TeacherInputCard(
    modifier: Modifier,
    teachersCount: Int = 1,
    lectureTeacherValue: String? = null,
    seminarTeacherValue: String? = null,
    changeTeacherCount: (Int) -> Unit,
    onLectureTeacherValueChange: (String) -> Unit,
    onSeminarTeacherValueChange: (String) -> Unit,
) {
    SubjectAddCard(
        modifier = modifier,
        label = "Teachers"
    ) {
        SegmentedToggle(
            options = listOf(ToggleOption("1 teacher"), ToggleOption("2 teachers")),
            selectedIndex = if (teachersCount == 1) 0 else 1,
            onSelect = changeTeacherCount
        )

        Spacer(Modifier.height(12.dp))

        TextFieldWhitName(
            modifier = Modifier.fillMaxWidth(),
            name = if(teachersCount == 1) "Teacher" else "Lecture teacher",
            placeholder = "Full name",
            value = lectureTeacherValue,
            onValueChange = onLectureTeacherValueChange,
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = if(teachersCount == 1) ImeAction.Done else ImeAction.Next
            ),
        )

        if (teachersCount == 2) Spacer(Modifier.height(12.dp))

        AnimatedVisibility(
            visible = teachersCount == 2,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            TextFieldWhitName(
                modifier = Modifier.fillMaxWidth(),
                name = "Seminar teacher",
                placeholder = "Full name",
                value = seminarTeacherValue,
                onValueChange = onSeminarTeacherValueChange,
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
            )
        }

    }
}

@Preview
@Composable
fun TeacherInputCardPreview() {
    StudyMateTheme {
        Surface {
            TeacherInputCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                teachersCount = 2,
                changeTeacherCount = {},
                onLectureTeacherValueChange = {},
                onSeminarTeacherValueChange = {}
            )
        }
    }
}

@Preview
@Composable
fun TeacherInputCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            TeacherInputCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                teachersCount = 2,
                changeTeacherCount = {},
                onLectureTeacherValueChange = {},
                onSeminarTeacherValueChange = {}
            )
        }
    }
}