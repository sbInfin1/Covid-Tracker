# Covid-Tracker
Android app to track the spread of Covid-19

## Overview
This is a simple Android app written in `Kotlin` language to help users be aware of the various numbers associated with the spread of `Covid-19` in various 
parts of the world. It makes use of the API endpoint https://corona.lmao.ninja/v2/countries?yesterday&sort to fetch a `JSON` array containing the covid related
information of different countries. The HTTP GET request to fetch the JSON is handled by the `Retrofit` library. The `Gson` library is used to parse the JSON and 
extract the information in the form of a list of Kotlin data class objects. This list is then displayed using `RecylerView` and `Data binding`.
