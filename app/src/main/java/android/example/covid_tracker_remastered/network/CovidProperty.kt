package android.example.covid_tracker_remastered.network

data class CovidProperty(
    val country: String,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int
    )
