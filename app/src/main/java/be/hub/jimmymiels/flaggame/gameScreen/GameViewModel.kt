package be.hub.jimmymiels.flaggame.gameScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hub.jimmymiels.flaggame.apiCountry.CountryApi
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class GameViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getCountries()
    }

    private fun getCountries() {
        CountryApi.retrofitService.getProperties().enqueue(object : retrofit2.Callback<List<CountryProperties>> {
            override fun onFailure(call: Call<List<CountryProperties>>, t: Throwable) {
                _response.value = "Failure" + t.message
            }

            override fun onResponse(call: Call<List<CountryProperties>>, response: Response<List<CountryProperties>>) {
                _response.value = "Succes: ${response.body()?.size} countries retrieved"
            }

        }
        )
    }
}