# COVID-19- dialy statistics
## Total covid-19 daily statistics, with complete dashboard by regions and provinces.


####  Presentation

<div>
   <img src="https://user-images.githubusercontent.com/101206928/215871922-f6134925-4b41-43b6-a300-d027513e3d02.png" width="400">
    <img src="https://user-images.githubusercontent.com/101206928/215871934-55585d68-bd22-48ac-b15d-718c37af10d6.png" width="400">
</div>

#### Compose:
##### I am using Jetpack Compose for building UI, as it is very helpful for large screens and handles all different screen sizes, orientations, and form factors - adaptive layout changes based on the screen space available to it, Also used Flow coroutines in most of the Use Cases to emit multiple data over a period of time.


##### Lightweight Thread:
###### I am using coroutine as a concurrency design pattern that you simplify code that executes asynchronously, helpful in more than one field as memory leaks and background tasks executing.


##### About:
###### The app Module is the main part of the application whose dependencies have been injected by dagger-hilt classes implementation, also control how long these class will live and instance creation. also, I am using coinpaprika as a remote data source for status live data and a retrofit library for handling all requests and responses with network connections.


##### Use Cases:
###### In this project, I am counting on Use Case as a single action with a single feature to Use a repository to access API data and then forward the information to the ViewModel.


##### Download:
[Get the app](https://sites.google.com/view/sparespark-covid-19-statistics) -alpah-version


