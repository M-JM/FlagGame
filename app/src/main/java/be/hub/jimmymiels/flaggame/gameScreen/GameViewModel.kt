package be.hub.jimmymiels.flaggame.gameScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hub.jimmymiels.flaggame.apiCountry.CountryApi
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class GameViewModel : ViewModel() {

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


    var queries = mutableListOf<String>()

    private val _randomAnswers  = MutableLiveData<ArrayList<String>>()
    val randomAnswers: LiveData<ArrayList<String>>
    get() =_randomAnswers

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
                var random = Random().nextInt(249)
                _response.value = "Success: ${listResult.size} Mars properties retrieved"
                //_country.value = listResult[random]
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


                //_option1.value = _correctAnswer.value!!
                // _option2.value = listResult[random].name
                // _option3.value = listResult[random].name
                // _option4.value = listResult[random].name
                // _randomAnswers.value!![0] =_correctAnswer.value!!
                // queries[1] =_correctAnswer.value!!
                //  _testsize.value = _randomAnswers.value!!.size.toString()
                // queries[2] =_correctAnswer.value!!
                // queries[3] =_correctAnswer.value!!
                //queries[1] = _countries.value!![random].name
                //queries[2] = _countries.value!![random2].name
                //queries[3] = _countries.value!![random3].name

                //_testsize.value = _randomAnswers.value!!.size
               // getRandomAnswers()

               // queries.shuffle()

                getRandomAnswers()

            } catch (e: Exception) {
                _response.value = "Failure : ${e.message}"

            }


        }
    }

    fun getRandomAnswers(){

    var chars  =_countries.value!!.shuffled().take(3).toMutableList()

        if (chars.isNotEmpty())
        {
            chars.add(_country.value!!)
        }
chars.shuffle()

       // var random = Random().nextInt(249)
       // var random2 = Random().nextInt(249)
       // var random3 = Random().nextInt(249)

        _option1.value = chars[0].name
        _option2.value = chars[1].name
        _option3.value = chars[2].name
        _option4.value = chars[3].name
        _testsize.value = chars.size.toString()


    }

    fun Onbutton() {

        getRandomCountry()
        getRandomAnswers()

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

