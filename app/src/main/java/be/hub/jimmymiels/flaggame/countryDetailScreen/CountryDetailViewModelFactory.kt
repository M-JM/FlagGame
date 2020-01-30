package be.hub.jimmymiels.flaggame.countryDetailScreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties

class CountryDetailViewModelFactory (

    private val countryProperties: CountryProperties,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CountryDetailViewModel::class.java)) {
                return CountryDetailViewModel(countryProperties, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}