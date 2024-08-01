package com.example.bkn.di.modules


import android.content.Context
import androidx.room.Room
import com.example.bkn.data.MainRepository
import com.example.bkn.data.dao.BookDao
import com.example.bkn.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: BookDao) = MainRepository(filmDao)
}