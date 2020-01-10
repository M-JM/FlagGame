package be.hub.jimmymiels.flaggame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FinalScore::class], version = 1, exportSchema = false)
/**
 *  Database annotation is mandatory
 *  declare of entities and version
 *  entities = add all tables in the list
 *  Version start at 1 , when chaning schema , the version needs to updated or else the app will not work
 *  It is possible to export Schemas of the database , not useful in this project since we will no change the schema nor make a complex database
 * */
abstract class ScoreDatabase: RoomDatabase() {

    abstract val scoreDatabaseDao : ScoreDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ScoreDatabase? = null

        fun getInstance(context: Context) : ScoreDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScoreDatabase::class.java,
                        "score_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}