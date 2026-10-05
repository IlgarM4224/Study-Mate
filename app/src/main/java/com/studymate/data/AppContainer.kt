package com.studymate.data

import android.content.Context

interface AppContainer {
    val subjectRepository: SubjectRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val subjectRepository: SubjectRepository by lazy {
        OfflineSubjectRepository(SubjectDatabase.getDatabase(context).subjectDao())
    }
}