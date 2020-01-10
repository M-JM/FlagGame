package be.hub.jimmymiels.flaggame

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import be.hub.jimmymiels.flaggame.database.FinalScore
import be.hub.jimmymiels.flaggame.database.ScoreDatabase
import be.hub.jimmymiels.flaggame.database.ScoreDatabaseDao
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class ScoreDatabaseTest {

    private lateinit var scoreDao: ScoreDatabaseDao
    private lateinit var db: ScoreDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ScoreDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
       scoreDao = db.scoreDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertandgetScore() {
        val score = FinalScore()
       scoreDao.insert(score)
        val finalScore1 = scoreDao.getFirstScore()
        assertEquals(finalScore1?.finalScorevalue, -1)
    }
}