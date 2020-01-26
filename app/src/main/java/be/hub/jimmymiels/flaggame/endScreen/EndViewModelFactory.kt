package be.hub.jimmymiels.flaggame.endScreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.hub.jimmymiels.flaggame.database.ScoreDatabaseDao
import java.lang.IllegalArgumentException

class EndViewModelFactory(
    private val dataSource: ScoreDatabaseDao,
    private val application:Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(EndViewModel::class.java)) {
            return  EndViewModel(dataSource,application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}
