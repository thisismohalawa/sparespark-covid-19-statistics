package sparespark.covid.statistics.presentation.provinceslist

import androidx.compose.foundation.ExperimentalFoundationApi
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
import sparespark.covid.statistics.presentation.NavScreens
import sparespark.covid.statistics.presentation.components.MainErrorMessage
import sparespark.covid.statistics.presentation.components.MainTitle
import sparespark.covid.statistics.presentation.provinceslist.components.ProvincesGridList
import sparespark.covid.statistics.presentation.window.WindowSize

@ExperimentalFoundationApi
@Composable
fun ProvincesListScreen(
    navController: NavController,
    windowSize: WindowSize,
    viewModel: ProvincesListViewModel = hiltViewModel()
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
        *
        * */
        state.provincesResponse?.provincesData?.let { provinces ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainTitle(title = stringResource(R.string.select_province), windowSize)
                Spacer(modifier = Modifier.height(10.dp))

                if (provinces.isNotEmpty())
                    ProvincesGridList(provinces = provinces, windowSize) {
                        if (it.iso?.isNotBlank() == true &&
                            it.province?.isNotBlank() == true
                        )
                            navController.navigate(
                                NavScreens.ReportScreen.route +
                                        "/${it.iso}" +
                                        "/${it.province}"
                            )
                    }
            }
        }
    }
}