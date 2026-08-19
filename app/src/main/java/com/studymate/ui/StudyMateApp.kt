package com.studymate.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.studymate.ui.navigation.StudyMateNavHost

@Composable
fun StudyMateApp(navController: NavHostController = rememberNavController()) {
    StudyMateNavHost(navController = navController)
}