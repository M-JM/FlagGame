package be.hub.jimmymiels.flaggame.gameScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hub.jimmymiels.flaggame.R
import be.hub.jimmymiels.flaggame.apiCountry.CountryApi
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties

import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class GameViewModel : ViewModel() {

    var radiochecked = MutableLiveData<Int>()


    private val _testsize = MutableLiveData<String>()
    val testsize: LiveData<String>
        get() = _testsize

    private val _option1 = MutableLiveData<String>()
    val option1: LiveData<String>
        get() = _option1

    private val _option2 = MutableLiveData<String>()
    val option2: LiveData<String>
        get() = _option2

    private val _option3 = MutableLiveData<String>()
    val option3: LiveData<String>
        get() = _option3

    private val _option4 = MutableLiveData<String>()
    val option4: LiveData<String>
        get() = _option4



    private val _randomAnswers = MutableLiveData<ArrayList<String>>()
    val randomAnswers: LiveData<ArrayList<String>>
        get() = _randomAnswers

    private val _randomflag1 = MutableLiveData<String>()
    val randomflag1: LiveData<String>
        get() = _randomflag1

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _correctAnswer = MutableLiveData<String>()
    val correctAnswer: LiveData<String>
        get() = _correctAnswer

    private val _randomflag = MutableLiveData<String>()
    val randomflag: LiveData<String>
        get() = _randomflag

    private val _country = MutableLiveData<CountryProperties>()
    val country: LiveData<CountryProperties>
        get() = _country

    private val _countries = MutableLiveData<List<CountryProperties>>()
    val countries: LiveData<List<CountryProperties>>
        get() = _countries

    private val _correctanswer = MutableLiveData<List<CountryProperties>>()
    val correctanswer: LiveData<List<CountryProperties>>
        get() = _correctanswer

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCountries()
        getRandomCountry()



    }

    private fun getCountries() {
        coroutineScope.launch {
            var getPropertiesDeferred = CountryApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success: ${listResult.size} countries properties retrieved"
                _countries.value = listResult

            } catch (e: Exception) {
                _response.value = "Failure : ${e.message}"
            }
        }

    }

    fun getRandomCountry() {
        coroutineScope.launch {
            var getPropertiesDeferred = CountryApi.retrofitService.getProperties()


            try {
                var listResult = getPropertiesDeferred.await()
                var random = Random().nextInt(249)
                _correctAnswer.value = listResult[random].name
                _correctanswer.value = listOf(listResult[random])
                _randomflag1.value = listResult[random].imgSrcUrl
                _country.value = listResult[random]

                getRandomAnswers()

            } catch (e: Exception) {
                _response.value = "Failure : ${e.message}"

            }


        }
    }

    fun getRandomAnswers() {

        var chars = _countries.value!!.shuffled().take(3).toMutableList()

        if (chars.isNotEmpty()) {
            chars.add(_country.value!!)
        }
        chars.shuffle()

        _option1.value = chars[0].name
        _option2.value = chars[1].name
        _option3.value = chars[2].name
        _option4.value = chars[3].name
     }

    fun resolveGame(){

        val checkedId = radiochecked.value
        var selectedvalue = ""
        when(checkedId) {
            R.id.radioButton -> selectedvalue = _option1.value.toString()
            R.id.radioButton2 -> selectedvalue = _option2.value.toString()
            R.id.radioButton3 -> selectedvalue = _option3.value.toString()
            R.id.radioButton4 -> selectedvalue = _option4.value.toString()
        }
        if(_correctAnswer.value!!.toString() == selectedvalue)
        {_testsize.value = "yes"} else {_testsize.value = "no"}

    }

    fun Onbutton() {

        getRandomCountry()

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

