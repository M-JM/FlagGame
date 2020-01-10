package be.hub.jimmymiels.flaggame.apiCountry

import com.squareup.moshi.Json

data class CountryProperties (


    val name : String,
    val capital : String,
    @Json( name="flag") val imgSrcUrl : String,
    val subregion : String

    )

