package com.studymate.ui.screens.components.subjectScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import com.studymate.data.GradeType
import com.studymate.ui.screens.BottomSheetState
import com.studymate.ui.screens.components.general.TwoButtons
import com.studymate.ui.theme.StudyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeBottomSheet(
    sheetState: BottomSheetState,
    onGradeChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    addGrade: (GradeType, Int) -> Unit,
    modifier: Modifier = Modifier,
    modalSheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = modalSheetState,
    ) {
        GradeBottomSheetContent(
            label = sheetState.getLabel(),
            gradeValue = sheetState.grade,
            isEntryValid = sheetState.isEntryValid,
            onGradeChange = onGradeChange,
            onCancel = onDismissRequest,
            onApply = {
                if (sheetState.isEntryValid) {
                    addGrade(sheetState.type, sheetState.grade.toInt())
                }
            }
        )
    }
}

@Composable
fun GradeBottomSheetContent(
    label: String,
    gradeValue: String,
    isEntryValid: Boolean,
    onGradeChange: (String) -> Unit,
    onCancel: () -> Unit,
    onApply: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = gradeValue,
            onValueChange = onGradeChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            isError = gradeValue.isNotEmpty() && !isEntryValid,
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
                gradeValue = "4",
                onGradeChange = {},
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
                gradeValue = "4",
                onGradeChange = {},
                label = "Seminar grade",
                isEntryValid = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}
