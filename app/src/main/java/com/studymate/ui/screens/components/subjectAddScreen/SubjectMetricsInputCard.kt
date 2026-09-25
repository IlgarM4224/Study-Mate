package com.studymate.ui.screens.components.subjectAddScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.screens.ValidInput
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun SubjectMetricsInputCard(
    modifier: Modifier,
    hours: ValidInput<Int?> = ValidInput(null),
    creditScore: ValidInput<Int?> = ValidInput(null),
    onHoursValueChange: (String) -> Unit = {},
    onCreditValueChange: (String) -> Unit = {}
) {
    SubjectAddCard(
        label = "Additionally",
        modifier = modifier
    ) {
        Row {
            TextFieldWhitName(
                name = "Hours",
                placeholder = "0",
                value = "${hours.value ?: ""}",
                onValueChange = onHoursValueChange,
                isError = !hours.isValid,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(16.dp))

            TextFieldWhitName(
                name = "Credit",
                placeholder = "0",
                value = "${creditScore.value ?: ""}",
                onValueChange = onCreditValueChange,
                isError = !creditScore.isValid,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun SubjectMetricsInputCardPreview() {
    StudyMateTheme {
        Surface {
            SubjectMetricsInputCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun SubjectMetricsInputCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectMetricsInputCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}