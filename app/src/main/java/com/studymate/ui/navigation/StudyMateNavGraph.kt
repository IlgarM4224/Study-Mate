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
import com.studymate.ui.screens.ScheduleDestination
import com.studymate.ui.screens.ScheduleScreen
import com.studymate.ui.screens.SubjectAddDestination
import com.studymate.ui.screens.SubjectAddScreen
import com.studymate.ui.screens.SubjectDestination
import com.studymate.ui.screens.SubjectDetailDestination
import com.studymate.ui.screens.SubjectDetailScreen
import com.studymate.ui.screens.SubjectScreen


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
                    navController.navigate(route = SubjectDestination.route)
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
                    navController.popBackStack()
                } ,
                navigateToDetailScreen = {
                    navController.navigate(route = "${SubjectDetailDestination.route}/${it}")
                },
                navigateToSubjectAddScreen = {
                    navController.navigate(route = SubjectAddDestination.route)
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
            arguments = listOf(navArgument(SubjectDetailDestination.SUBJECT_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            SubjectDetailScreen(
                navigateBack = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
            )
        }

        composable(route = SubjectAddDestination.route) {
            SubjectAddScreen(
                navigateBack = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            )
        }
    }
}