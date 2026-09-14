package com.studymate.ui.screens.components.subjectScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.studymate.ui.screens.GradeType
import com.studymate.ui.screens.components.general.TwoButtons
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
    isEntryValid: Boolean = false,
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
            isEntryValid = isEntryValid
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
    isEntryValid: Boolean = false,
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
            isError = !isEntryValid,
        )

        Spacer(Modifier.height(16.dp))

        TwoButtons(
            leftLabel = "Cancel",
            leftIcon = Icons.Outlined.Cancel,
            leftOnClick = onCancel,
            rightLabel = "Apply",
            rightOnClick = onApply,
            rightIcon = Icons.Outlined.Done,
            rightEnabled = isEntryValid,
            spacer = 1f,
            modifier = Modifier.fillMaxWidth(),
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
                isEntryValid = true,
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
                isEntryValid = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}
