package be.hub.jimmymiels.flaggame.countryDetailScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties

class CountryDetailViewModel( countryProperties: CountryProperties , app: Application) : AndroidViewModel(app) {

    private val _selectedCountry = MutableLiveData<CountryProperties>()
    val selectedCountry: LiveData<CountryProperties>
        get() = _selectedCountry

    init {
        _selectedCountry.value = countryProperties
    }

}
