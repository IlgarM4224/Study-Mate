package com.studymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.studymate.data.TestData
import com.studymate.ui.screens.ScheduleScreen
import com.studymate.ui.theme.StudyMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyMateTheme{
                Surface {
                    ScheduleScreen(
                        modifier = Modifier.fillMaxSize(),
                        lessons = TestData.getLessons()
                    )
                }
            }
        }
    }
}
