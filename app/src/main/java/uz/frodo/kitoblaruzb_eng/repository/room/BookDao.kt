package uz.frodo.kitoblaruzb_eng.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(bookEntity: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun getAllBooks():Flow<List<BookEntity>>
}