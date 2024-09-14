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
    fun provideBookDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "book_db"
        ).build().bookDao()

    @Provides
    @Singleton
    fun provideRepository(bookDao: BookDao) = MainRepository(bookDao)
}