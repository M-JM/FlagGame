package be.hub.jimmymiels.flaggame.gameScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hub.jimmymiels.flaggame.apiCountry.CountryApi
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class GameViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _country = MutableLiveData<CountryProperties>()
    val country:LiveData<CountryProperties>
    get() = _country

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCountries()
    }

    private fun getCountries() {
       coroutineScope.launch {
      var getPropertiesDeferred =  CountryApi.retrofitService.getProperties()
       try {
              var listResult = getPropertiesDeferred.await()
           _response.value = "Success: ${listResult.size} Mars properties retrieved"
           _country.value = listResult[0]
            }
              catch (e: Exception) {
           _response.value = "Failure : ${e.message}"
       }
        }
   }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

