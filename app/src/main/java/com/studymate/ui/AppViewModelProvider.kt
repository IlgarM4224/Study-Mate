package com.studymate.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.studymate.StudyMateApplication
import com.studymate.ui.screens.SubjectAddScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SubjectAddScreenViewModel(studyMateApplication().container.subjectRepository)
        }
    }
}

fun CreationExtras.studyMateApplication(): StudyMateApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as StudyMateApplication)