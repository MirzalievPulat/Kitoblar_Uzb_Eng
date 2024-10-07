package uz.frodo.kitoblaruzb_eng.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import uz.frodo.kitoblaruzb_eng.repository.room.BookDao
import uz.frodo.kitoblaruzb_eng.repository.room.RoomDb
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