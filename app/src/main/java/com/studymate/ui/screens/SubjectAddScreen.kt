package com.studymate.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studymate.R
import com.studymate.ui.navigation.NavigationDestination
import com.studymate.ui.screens.components.general.StudyMateTopAppBar
import com.studymate.ui.screens.components.subjectAddScreen.SubjectInputCard
import com.studymate.ui.screens.components.subjectAddScreen.SubjectMetricsInputCard
import com.studymate.ui.theme.StudyMateTheme

object SubjectAddDestination: NavigationDestination {
    override val route = "SubjectAdd"
    override val titleRes = R.string.subject_add_screen
    override val destinationIcon = null
}


@Composable
fun SubjectAddScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    viewModel: SubjectAddScreenViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    SubjectAddScreenContent(
        modifier = modifier,
        navigateBack = navigateBack,
        state = uiState
    )
}

@Composable
fun SubjectAddScreenContent(
    modifier: Modifier = Modifier,
    state: SubjectAddState,
    navigateBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            StudyMateTopAppBar(
                title = stringResource(SubjectAddDestination.titleRes),
                titleStyle = MaterialTheme.typography.displaySmall,
                canNavigateBack = true,
                onNavigateClick = navigateBack,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)

        ) {
            SubjectInputCard(
                value = state.subjectName,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            SubjectMetricsInputCard(
                hoursValue = "${state.hours ?: ""}",
                creditValue = "${state.creditScore ?: ""}",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun SubjectAddScreenContentPreview() {
    StudyMateTheme {
        Surface {
            SubjectAddScreenContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                state = SubjectAddState()
            )
        }
    }
}

@Preview
@Composable
fun SubjectAddScreenContentDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            SubjectAddScreenContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                state = SubjectAddState()
            )
        }
    }
}
