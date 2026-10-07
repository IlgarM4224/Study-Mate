package com.studymate.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.studymate.StudyMateApplication
import com.studymate.ui.screens.SubjectAddScreenViewModel
import com.studymate.ui.screens.SubjectDetailScreenViewModel
import com.studymate.ui.screens.SubjectScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SubjectAddScreenViewModel(studyMateApplication().container.subjectRepository)
        }

        initializer {
            SubjectScreenViewModel(studyMateApplication().container.subjectRepository)
        }

        initializer {
            SubjectDetailScreenViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                subjectRepository = studyMateApplication().container.subjectRepository
            )
        }
    }
}

fun CreationExtras.studyMateApplication(): StudyMateApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as StudyMateApplication)