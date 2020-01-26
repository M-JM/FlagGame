package be.hub.jimmymiels.flaggame.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDatabaseDao {

    @Insert
    // parameter , instance final of class FinalScore)
    fun insert(final : FinalScore)

    @Update
    fun update(final: FinalScore)

    // Select the 10 highest finalScore from Database
    @Query("SELECT * FROM final_score_table ORDER BY final_score_value DESC LIMIT 10")
    fun getTop10Score(): LiveData<List<FinalScore>>?
    // LiveData<List<FinalScore>> can be  null since no score can be present upon first launch or when calling all clear. Add ? to end

    // Clear all finalScore in database
    @Query("DELETE FROM final_score_table")
    fun clear()

    @Query("SELECT * FROM final_score_table ORDER BY scoreId DESC LIMIT 1")
    fun getFirstScore(): FinalScore?
}

