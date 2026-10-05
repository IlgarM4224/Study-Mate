package com.studymate

import android.app.Application
import com.studymate.data.AppContainer
import com.studymate.data.AppDataContainer

class StudyMateApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

