package be.hub.jimmymiels.flaggame.apiCountry

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize class CountryProperties (

    val name : String,
    val capital : String,
    @Json( name="flag") val imgSrcUrl : String,
    val subregion : String

    ): Parcelable

