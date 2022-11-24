package sparespark.covid.statistics.presentation.totalreport

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import sparespark.covid.statistics.R
import sparespark.covid.statistics.core.toStringDecimalFormatting
import sparespark.covid.statistics.presentation.NavScreens
import sparespark.covid.statistics.presentation.components.MainErrorMessage
import sparespark.covid.statistics.presentation.components.MainTitle
import sparespark.covid.statistics.presentation.components.SubTitle
import sparespark.covid.statistics.presentation.totalreport.components.ActionButton
import sparespark.covid.statistics.presentation.totalreport.components.TearDrop
import sparespark.covid.statistics.presentation.window.WindowSize


@Composable
fun TotalReportScreen(
    navController: NavController,
    windowSize: WindowSize,
    viewModel: TotalReportViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        /*
        * Views..
        * */
        if (state.isLoading)
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(30.dp),
                color = Color.Black,
                strokeWidth = 8.dp
            )
        if (state.error.isNotBlank())
            MainErrorMessage(state.error, windowSize)
        /*
        * Data
        * */
        state.report?.totalReportData?.let { reportData ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    MainTitle(title = stringResource(R.string.covid_statistics), windowSize)
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            TearDrop(
                                reportData.active?.let { toStringDecimalFormatting(it) },
                                "Active",
                                isTopShape = false,
                                isEndedShape = true,
                                windowSize
                            )
                            TearDrop(
                                reportData.confirmed?.let { toStringDecimalFormatting(it) },
                                "Confirmed",
                                isTopShape = false,
                                isEndedShape = false,
                                windowSize
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            TearDrop(
                                reportData.recovered?.let { toStringDecimalFormatting(it) },
                                "Recovered",
                                isTopShape = true,
                                isEndedShape = true,
                                windowSize
                            )
                            TearDrop(
                                reportData.deaths?.let { toStringDecimalFormatting(it) },
                                "Deaths",
                                isTopShape = true,
                                isEndedShape = false,
                                windowSize
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        if (reportData.last_update?.isNotBlank() == true)
                            SubTitle(title = "Last update ${reportData.last_update}", windowSize)
                        Spacer(modifier = Modifier.height(10.dp))
                        ActionButton(stringResource(id = R.string.dash_by_region), windowSize) {
                            navController.navigate(NavScreens.RegionsListScreen.route)
                        }
                        /*
                        *
                        *
                        *
                        *
                        * */
                    }
                }
            }
        }
    }
}