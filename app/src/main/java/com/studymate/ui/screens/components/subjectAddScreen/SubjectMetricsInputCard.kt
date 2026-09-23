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
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun SubjectMetricsInputCard(
    modifier: Modifier,
    hoursValue: String = "",
    creditValue: String = "",
    isHoursError: Boolean = false,
    isCreditError: Boolean = false,
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
                value = hoursValue,
                onValueChange = onHoursValueChange,
                isError = isHoursError,
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
                value = creditValue,
                onValueChange = onCreditValueChange,
                isError = isCreditError,
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