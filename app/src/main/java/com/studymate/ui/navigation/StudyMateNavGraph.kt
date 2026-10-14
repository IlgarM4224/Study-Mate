package com.studymate.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.studymate.ui.screens.scheduleScreen.ScheduleDestination
import com.studymate.ui.screens.scheduleScreen.ScheduleScreen
import com.studymate.ui.screens.subjectAddScreen.SubjectAddDestination
import com.studymate.ui.screens.subjectAddScreen.SubjectAddScreen
import com.studymate.ui.screens.subjectDetailScreen.SubjectDetailDestination
import com.studymate.ui.screens.subjectDetailScreen.SubjectDetailScreen
import com.studymate.ui.screens.subjectEditScreen.SubjectEditDestination
import com.studymate.ui.screens.subjectEditScreen.SubjectEditScreen
import com.studymate.ui.screens.subjectScreen.SubjectDestination
import com.studymate.ui.screens.subjectScreen.SubjectScreen


/**
 * Top-level Navigation Host for the StudyMate application.
 *
 * Configures the app's navigation graph, defines screen routes, passes navigation arguments,
 * and handles backstack transitions between screens.
 *
 * @param navController Controller responsible for performing navigation actions and managing the back stack.
 * @param modifier Optional [Modifier] applied to the root [NavHost] container.
 */
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
        // Schedule Screen Route
        composable(route = ScheduleDestination.route) {
            ScheduleScreen(
                navigateSubjectScreen = {
                    navController.navigate(route = SubjectDestination.route) {launchSingleTop = true}
                },
                modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
            )
        }

        // Subjects Overview Screen Route
        composable(route = SubjectDestination.route) {
            SubjectScreen(
                navigateScheduleScreen = {
                    navController.navigate(ScheduleDestination.route) {
                        popUpTo(ScheduleDestination.route) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                navigateToDetailScreen = {
                    navController.navigate(route = "${SubjectDetailDestination.route}/${it}")
                },
                navigateToSubjectAddScreen = {
                    navController.navigate(route = SubjectAddDestination.route)
                },
                navigateToSubjectEditScreen = {
                    navController.navigate(route = "${SubjectEditDestination.route}/${it}")
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            )
        }

        // Subject Detail Screen Route
        composable(
            route = SubjectDetailDestination.routeWithArgs,
            // Defines expected route arguments (requires an Integer subject ID)
            arguments = listOf(navArgument(SubjectDetailDestination.SUBJECT_ID_ARG) { type = NavType.IntType })
        ) {
            SubjectDetailScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditScreen = {
                    navController.navigate(route = "${SubjectEditDestination.route}/${it}")
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
            )
        }

        // Subject Add Screen Route
        composable(route = SubjectAddDestination.route) {
            SubjectAddScreen(
                navigateBack = { navController.popBackStack(SubjectDestination.route, inclusive = false) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
            )
        }

        // Subject Edit Screen Route
        composable(
            route = SubjectEditDestination.routeWithArgs,
            arguments = listOf(navArgument(SubjectEditDestination.SUBJECT_ID_ARG) { type = NavType.IntType })
        ) {
            SubjectEditScreen(
                navigateBack = { navController.popBackStack() },
                navigateToSubjectScreen = { navController.popBackStack(SubjectDestination.route, inclusive = false) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
            )
        }
    }
}