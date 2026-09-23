package com.studymate.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

interface NavigationDestination {
    /**
     * Unique subjectName to define the path for a composable
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen
     */
    val titleRes: Int?

    /**
     * ImageVector icon for the bottom bar
     */
    val destinationIcon: ImageVector?
}