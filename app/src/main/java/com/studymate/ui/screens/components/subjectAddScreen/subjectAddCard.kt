package com.studymate.ui.screens.components.subjectAddScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studymate.ui.theme.StudyMateTheme

@Composable
fun SubjectAddCard(
    modifier: Modifier,
    label: String,
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "• $label",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(8.dp))

            content()
        }
    }
}

@Composable
fun TextFieldWhitName(
    modifier: Modifier,
    name: String,
    placeholder: String = "",
    value: String?,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(Modifier.height(4.dp))

        OutlinedTextField(
            value = value ?: "",
            onValueChange = onValueChange,
            placeholder = {Text(text = placeholder, color = MaterialTheme.colorScheme.outline)} ,
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = keyboardOptions,
            modifier = Modifier.fillMaxWidth()
        )
    }
}



@Preview(group = "SubjectAddCard")
@Composable
fun SubjectAddCardPreview() {
    StudyMateTheme {
        Surface {
            SubjectAddCard(
                label = "Main",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Hello world")
            }
        }
    }
}

@Preview(group = "SubjectAddCard")
@Composable
fun SubjectAddCardDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectAddCard(
                label = "Main",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Hello world")
            }
        }
    }
}

@Preview(group = "Text Field Whit Name")
@Composable
fun TextFieldWhitNamePreview() {
    StudyMateTheme {
        Surface {
            TextFieldWhitName(
                name = "Subject name",
                value = "Programming basics",
                onValueChange = {},
                isError = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(group = "Text Field Whit Name")
@Composable
fun TextFieldWhitNameDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            TextFieldWhitName(
                name = "Subject name",
                value = "Programming basics",
                onValueChange = {},
                isError = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}