package sparespark.covid.statistics.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.presentation.provincereport.ProvinceReportScreen
import sparespark.covid.statistics.presentation.provinceslist.ProvincesListScreen
import sparespark.covid.statistics.presentation.regionslist.RegionsListScreen
import sparespark.covid.statistics.presentation.totalreport.TotalReportScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
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
                    TotalReportScreen(navController)
                }
                /*
                *  SCREEN 2 :
                *
                *
                * */
                composable(
                    route = NavScreens.RegionsListScreen.route
                ) {
                    RegionsListScreen(navController)
                }
                /*
                * SCREEN 3:
                *
                * */
                composable(
                    route = NavScreens.ProvincesListScreen.route +
                            "/{${Constants.PARAM_ISO_KEY}}"
                ) {
                    ProvincesListScreen(navController)
                }
                /*
                * SCREEN 4 :
                *
                * */
                composable(
                    route = NavScreens.ReportScreen.route +
                            "/{${Constants.PARAM_ISO_KEY}}" +
                            "/{${Constants.PARAM_PROVINCE_KEY}}"
                ) {
                    ProvinceReportScreen()
                }

            }
        }
    }
}

