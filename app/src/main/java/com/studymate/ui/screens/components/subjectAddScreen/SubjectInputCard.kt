package com.studymate.ui.screens.components.subjectAddScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun SubjectInputCard(
    modifier: Modifier,
    value: String = "",
    isError: Boolean = false,
    onValueChange: (String) -> Unit = {}
) {
    SubjectAddCard(
        label = "Main",
        modifier = modifier
    ) {
        TextFieldWhitName(
            name = "Subject name",
            placeholder = "required field",
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun SubjectInputCardPreview() {
    StudyMateTheme {
        Surface {
            SubjectInputCard(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun SubjectInputCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectInputCard(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}