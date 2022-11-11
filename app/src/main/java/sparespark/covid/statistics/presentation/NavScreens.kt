package sparespark.covid.statistics.presentation

sealed class NavScreens(val route: String) {

    object TotalReportScreen : NavScreens("total_report_screen")
    object RegionsListScreen : NavScreens("regions_list_screen")
    object ProvincesListScreen : NavScreens("provinces_list_screen")
    object ReportScreen : NavScreens("report_screen")
}
