package jp.shiita.rawquerytest

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RawQuery
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteProgram
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
abstract class MyDao {
    val isEnhancedQueryEnabled: Boolean by lazy {
        "ENABLE_FTS3_PARENTHESIS" in selectCompileOptions()
    }

    @RawQuery
    protected abstract fun selectCompileOptions(
        query: SelectCompileOptionsQuery = SelectCompileOptionsQuery
    ): List<String>

    protected object SelectCompileOptionsQuery : SupportSQLiteQuery {
        override fun getSql(): String = "PRAGMA compile_options"

        override fun bindTo(statement: SupportSQLiteProgram?) = Unit

        override fun getArgCount(): Int = 0
    }
}

@Database(entities = [DummyEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getMyDao(): MyDao
}

@Entity
data class DummyEntity(@PrimaryKey val id: Int)
