package com.example.bkn.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bkn.data.dao.BookDao
import com.example.bkn.data.Enity.Book


@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}