package be.hub.jimmymiels.flaggame.endScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import be.hub.jimmymiels.flaggame.database.FinalScore
import be.hub.jimmymiels.flaggame.database.ScoreDatabaseDao
import kotlinx.coroutines.*

class EndViewModel (
    val database: ScoreDatabaseDao, application: Application
) :AndroidViewModel(application) {

    private var viewModelJob = Job()





    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var currentScore = MutableLiveData<FinalScore?>()
    private val top10score = database.getTop10Score()

    init {
initializeCurrentScore()
    }
private fun initializeCurrentScore() {
    uiScope.launch {
        currentScore.value = getCurrentScoreFromDatabase()
    }
}

    private suspend fun getCurrentScoreFromDatabase():FinalScore? {
        return withContext(Dispatchers.IO) {
            var score = database.getFirstScore()
            if(score?.finalScorevalue == null) { score = null
        }
        score
        }
    }


}