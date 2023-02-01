package sparespark.covid.statistics.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Constants.DEBUG_TAG
import sparespark.covid.statistics.core.Constants.UPDATE_REQUEST_CODE
import sparespark.covid.statistics.presentation.provincereport.ProvinceReportScreen
import sparespark.covid.statistics.presentation.provinceslist.ProvincesListScreen
import sparespark.covid.statistics.presentation.regionslist.RegionsListScreen
import sparespark.covid.statistics.presentation.totalreport.TotalReportScreen
import sparespark.covid.statistics.presentation.window.rememberWindowSize

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val window = rememberWindowSize()

            NavHost(
                navController = navController, startDestination = NavScreens.TotalReportScreen.route
            ) {
                /*
                *  SCREEN 1 :
                *
                * */
                composable(
                    route = NavScreens.TotalReportScreen.route
                ) {
                    TotalReportScreen(navController, window)
                }
                /*
                *  SCREEN 2 :
                *
                *
                * */
                composable(
                    route = NavScreens.RegionsListScreen.route
                ) {
                    RegionsListScreen(navController, window)
                }
                /*
                * SCREEN 3:
                *
                * */
                composable(
                    route = NavScreens.ProvincesListScreen.route + "/{${Constants.PARAM_ISO_KEY}}"
                ) {
                    ProvincesListScreen(navController, window)
                }
                /*
                * SCREEN 4 :
                *
                * */
                composable(
                    route = NavScreens.ReportScreen.route + "/{${Constants.PARAM_ISO_KEY}}" + "/{${Constants.PARAM_PROVINCE_KEY}}"
                ) {
                    ProvinceReportScreen(window)
                }

            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED)
            Log.d(DEBUG_TAG, "onActivityResult : result canceled... ")
    }
}

