package sparespark.covid.statistics.presentation.totalreport

sealed class TotalReportEvent {

    object CheckForAppUpdates : TotalReportEvent()
    object CheckIfAppUpdatesIsResumed : TotalReportEvent()

}
