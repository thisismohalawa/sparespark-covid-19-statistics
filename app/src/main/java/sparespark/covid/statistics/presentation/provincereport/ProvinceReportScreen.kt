package sparespark.covid.statistics.presentation.provincereport

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
import sparespark.covid.statistics.R
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.toStringDecimalFormatting
import sparespark.covid.statistics.presentation.components.MainErrorMessage
import sparespark.covid.statistics.presentation.components.MainTitle
import sparespark.covid.statistics.presentation.components.SubTitle
import sparespark.covid.statistics.presentation.totalreport.components.TearDrop
import sparespark.covid.statistics.presentation.window.WindowSize


@Composable
fun ProvinceReportScreen(
    windowSize: WindowSize,
    viewModel: ProvinceReportViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        /*
        * Views..
        * */
        if (state.isLoading) CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp),
            color = Color.Black,
            strokeWidth = 8.dp
        )
        if (state.error.isNotBlank()) MainErrorMessage(state.error, windowSize)
        /*
        * Data..
        *
        * */
        state.report?.reportData?.let { report ->
            if (report.isEmpty()) MainErrorMessage(Constants.EMPTY_DATA, windowSize)
            else LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    MainTitle(
                        title = stringResource(R.string.covid_statistics) +
                                "\nAt " +
                                (report[0].region?.province ?: "Unknown Provinces") +
                                "," +
                                (report[0].region?.name ?: "Unknown Region"),
                        windowSize
                    )
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
                                report[0].active?.let { toStringDecimalFormatting(it) },
                                "Active",
                                isTopShape = false,
                                isEndedShape = true,
                                windowSize
                            )
                            TearDrop(
                                report[0].confirmed?.let { toStringDecimalFormatting(it) },
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
                                report[0].recovered?.let { toStringDecimalFormatting(it) },
                                "Recovered",
                                isTopShape = true,
                                isEndedShape = true,
                                windowSize
                            )
                            TearDrop(
                                report[0].deaths?.let { toStringDecimalFormatting(it) },
                                "Deaths",
                                isTopShape = true,
                                isEndedShape = false,
                                windowSize
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        if (report[0].last_update?.isNotBlank() == true)
                            SubTitle(title = "Last update ${report[0].last_update}", windowSize)
                    }
                }
            }
        }
    }
}