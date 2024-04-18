package com.app.syncstudyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.syncstudyapp.navigation.SetUpNavGraph
import com.app.syncstudyapp.ui.theme.SyncStudyAppTheme
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "MainActivity_창영"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SyncStudyAppTheme {
                // val networkDataRepository = NetworkDataRepository(networkDataApi = NetworkDataApi)
                // val networkViewModel = viewModel<HomeResponseViewModel>()
                AppEntryPoint()


            }
        }
    } // End of onCreate()
} // End of MainActivity class

@Composable
private fun AppEntryPoint() {
    val navController = rememberNavController()
    SetUpNavGraph(navController)
} // End of AppEntryPoint()