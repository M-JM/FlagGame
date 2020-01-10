package be.hub.jimmymiels.flaggame.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName ="final_score_table")
    data class FinalScore (
        @PrimaryKey (autoGenerate = true)
        var scoreId : Long = 0L,
        @ColumnInfo (name = "final_score_value")
        var finalScorevalue : Int = -1

    )
