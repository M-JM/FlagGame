package be.hub.jimmymiels.flaggame.endScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.hub.jimmymiels.flaggame.database.FinalScore
import be.hub.jimmymiels.flaggame.database.ScoreDatabaseDao
import be.hub.jimmymiels.flaggame.gameScreen.GameFragment
import be.hub.jimmymiels.flaggame.gameScreen.GameFragmentDirections
import kotlinx.coroutines.*

class EndViewModel (
    val database: ScoreDatabaseDao, application: Application
) :AndroidViewModel(application) {

    private var viewModelJob = Job()
    var endscore  = 0

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var currentScore = MutableLiveData<FinalScore?>()
    val top10score = database.getTop10Score()

    private val _score = MutableLiveData<String>()
    val score: LiveData<String>
        get() = _score

    init {
        onEndGame()

    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

      fun insert (final: FinalScore) {
        uiScope.launch {
        withContext(Dispatchers.IO){
            database.insert(final)
        }
    }
    }

    fun onEndGame() {
        uiScope.launch {
            val endScore = FinalScore()
            insert(endScore)
            endScore.finalScorevalue = endscore
            _score.value = "Your current score is $endscore"
        }
    }

    fun onClear() {
        uiScope.launch {
            // Clear the database table.
            clear()

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}