package be.hub.jimmymiels.flaggame.endScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import be.hub.jimmymiels.flaggame.database.FinalScore
import be.hub.jimmymiels.flaggame.database.ScoreDatabaseDao
import kotlinx.coroutines.*

class EndViewModel (
    val database: ScoreDatabaseDao, application: Application
) :AndroidViewModel(application) {

    private var viewModelJob = Job()





    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var currentScore = MutableLiveData<FinalScore?>()
    val top10score = database.getTop10Score()

    init {
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
            endScore.finalScorevalue = 2
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