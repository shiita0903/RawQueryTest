package jp.shiita.rawquerytest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MyDaoTest {
    private lateinit var dao: MyDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.getMyDao()
    }

    @Test
    fun test() {
        println("isEnhancedQueryEnabled = ${dao.isEnhancedQueryEnabled}")
    }
}
