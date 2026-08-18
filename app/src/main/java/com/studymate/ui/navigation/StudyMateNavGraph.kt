package com.studymate.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.studymate.ui.screens.ScheduleDestination
import com.studymate.ui.screens.ScheduleScreen
import com.studymate.ui.screens.SubjectDestination
import com.studymate.ui.screens.SubjectScreen

@Composable
fun StudyMateNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ScheduleDestination.route,
        modifier = modifier
    ) {
        composable(route = ScheduleDestination.route) {
            ScheduleScreen(
                navigateSubjectScreen = { navController.navigate(route = SubjectDestination.route) },
                modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
            )
        }

        composable(route = SubjectDestination.route) {
            SubjectScreen(
                navigateScheduleScreen = { navController.navigate(route = ScheduleDestination.route) } ,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
        }
    }
}