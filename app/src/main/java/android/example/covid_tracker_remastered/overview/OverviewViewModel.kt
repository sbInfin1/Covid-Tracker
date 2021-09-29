package android.example.covid_tracker_remastered.overview

import android.example.covid_tracker_remastered.network.CovidApi
import android.example.covid_tracker_remastered.network.CovidProperty
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    private val TAG = "OverviewViewModel"
    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<List<CovidProperty>>()

    val property: LiveData<List<CovidProperty>>
        get() = _property

    init {
        getCovidProperties()
    }

    /**
     * Get the Covid information from the given API Retrofit Service and updates the
     * [CovidProperty] [LiveData].
     */
    private fun getCovidProperties() {

        CovidApi.retrofitService.getProperties().enqueue(object: Callback<List<CovidProperty>> {
            override fun onResponse(
                call: Call<List<CovidProperty>>,
                response: Response<List<CovidProperty>>
            ) {
                val dataSetSize = response.body()?.size
                _status.value = "Success: ${dataSetSize} Covid data retrieved"
                Log.i(TAG, "Status: ${_status.value}")

                if (dataSetSize != null) {
                    if(dataSetSize > 0){
                        _property.value = response.body()
                        Log.i(TAG, "property.size() = ${_property.value?.size}")
                    }
                }
            }

            override fun onFailure(call: Call<List<CovidProperty>>, t: Throwable) {
                _status.value = "Failure: " + t.message
                Log.i(TAG, "Status: ${_status.value}")
            }
        })
    }
}