package be.hub.jimmymiels.flaggame.gameScreen

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hub.jimmymiels.flaggame.apiCountry.CountryApi
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties
import kotlinx.coroutines.*
import java.util.*


class GameViewModel : ViewModel() {

    private val _randomflag1 = MutableLiveData<String>()
    val randomflag1: LiveData<String>
        get() = _randomflag1

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _randomflag = MutableLiveData<String>()
    val randomflag: LiveData<String>
        get() = _randomflag

    private val _country = MutableLiveData<CountryProperties>()
    val country:LiveData<CountryProperties>
    get() = _country

    private val _countries = MutableLiveData<List<CountryProperties>>()
    val countries:LiveData<List<CountryProperties>>
    get() = _countries

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCountries()
        getRandomCountry()
    }

    private fun getCountries() {
       coroutineScope.launch {
      var getPropertiesDeferred =  CountryApi.retrofitService.getProperties()
       try {
              var listResult = getPropertiesDeferred.await()
           _response.value = "Success: ${listResult.size} Mars properties retrieved"
           _country.value = listResult[0]
           _countries.value = listResult
            }
              catch (e: Exception) {
           _response.value = "Failure : ${e.message}"
       }
        }

   }

     fun getRandomCountry() {
        coroutineScope.launch {
            var getPropertiesDeferred =  CountryApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                var random = Random().nextInt(250)
                _randomflag.value = listResult[random].capital
                _randomflag1.value =listResult[random].imgSrcUrl

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

