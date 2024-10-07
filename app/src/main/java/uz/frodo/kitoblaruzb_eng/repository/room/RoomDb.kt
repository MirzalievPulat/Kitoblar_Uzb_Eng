package uz.frodo.kitoblaruzb_eng.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
abstract class RoomDb:RoomDatabase() {
    abstract fun dao(): BookDao
}