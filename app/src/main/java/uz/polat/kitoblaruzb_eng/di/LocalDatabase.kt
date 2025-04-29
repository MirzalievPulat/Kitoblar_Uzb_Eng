package uz.polat.kitoblaruzb_eng.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.polat.kitoblaruzb_eng.repository.room.BookDao
import uz.polat.kitoblaruzb_eng.repository.room.RoomDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabase {

    @[Singleton Provides]
    fun providesRoomDb(@ApplicationContext context: Context):RoomDb =
        Room.databaseBuilder(context,RoomDb::class.java,"Book.db").build()

    @[Singleton Provides]
    fun providesBookDao(db: RoomDb):BookDao = db.dao()
}