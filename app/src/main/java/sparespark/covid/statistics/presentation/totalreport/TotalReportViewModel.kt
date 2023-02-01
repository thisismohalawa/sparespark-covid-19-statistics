package sparespark.covid.statistics.presentation.totalreport

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.covid.statistics.core.Constants
import sparespark.covid.statistics.core.Constants.DEBUG_TAG
import sparespark.covid.statistics.core.Constants.UPDATE_REQUEST_CODE
import sparespark.covid.statistics.core.Resource
import sparespark.covid.statistics.core.activity
import sparespark.covid.statistics.domain.usecases.GetTotalReportUseCase
import javax.inject.Inject

@HiltViewModel
class TotalReportViewModel @Inject constructor(
    private val getTotalReportUseCase: GetTotalReportUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    // check for updates
    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(app) }

    /*
    * ViewModel major responsibility is holding UI state, all business logic
    * will be implemented in use cases...
    *
    *
    *
    * */
    private val _state = mutableStateOf(TotalReportViewState())
    val state: State<TotalReportViewState> = _state

    init {

        getTotalReport()
    }

    fun eventTrigger(event: TotalReportEvent) {
        when (event) {
            is TotalReportEvent.CheckForAppUpdates -> checkForAppUpdates()

            is TotalReportEvent.CheckIfAppUpdatesIsResumed -> checkIfAppUpdatesIsResumed()
        }
    }

    private fun getTotalReport() {
        getTotalReportUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = TotalReportViewState(isLoading = true)
                is Resource.Error -> _state.value =
                    TotalReportViewState(error = result.message ?: Constants.ERROR_OCCURRED)
                is Resource.Success -> _state.value = TotalReportViewState(report = result.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun checkForAppUpdates() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && it.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE
                )
            ) {
                try {

                    appUpdateManager.startUpdateFlowForResult(
                        it,
                        AppUpdateType.IMMEDIATE,
                        app.activity() as Activity,
                        UPDATE_REQUEST_CODE
                    )

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

            } else Log.d(DEBUG_TAG, "checkForAppUpdates...latest version. ")
        }.addOnFailureListener {
            Log.d(
                DEBUG_TAG,
                "App update exception : ${it.message} \n" + "cause : ${it.cause.toString()} "
            )
        }
    }

    private fun checkIfAppUpdatesIsResumed() {

    }
}