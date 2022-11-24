package sparespark.covid.statistics.presentation.regionslist

import androidx.compose.foundation.layout.*
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
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.presentation.NavScreens
import sparespark.covid.statistics.presentation.components.MainErrorMessage
import sparespark.covid.statistics.presentation.components.MainTitle
import sparespark.covid.statistics.presentation.regionslist.components.RegionsGridList
import sparespark.covid.statistics.presentation.window.WindowSize

@Composable
fun RegionsListScreen(
    navController: NavController,
    windowSize: WindowSize,
    viewModel: RegionsListViewModel = hiltViewModel()

) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        /*
        * Views..
        *
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
            MainErrorMessage(msg = state.error, windowSize)
        /*
        * Data..
        *
        * */
        state.regionsResponse?.regionsData?.let { regionsList ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainTitle(title = stringResource(R.string.select_region), windowSize)
                Spacer(modifier = Modifier.height(10.dp))

                if (regionsList.isNotEmpty())
                    RegionsGridList(regionsList) {
                        if (it.iso?.isNotBlank() == true)
                            navController.navigate(
                                NavScreens.ProvincesListScreen.route +
                                        "/${it.iso}"
                            )
                    }
                else MainErrorMessage(Constants.EMPTY_DATA, windowSize)
            }
        }
    }
}