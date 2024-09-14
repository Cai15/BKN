package com.example.bkn.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bkn.data.Enity.Book

//Помечаем, что это не просто интерфейс а Dao объект
@Dao
interface BookDao {
    //Запрос на всю таблицу
    @Query("SELECT * FROM cached_books")
    fun getCachedBooks(): List<Book>

    //Кладем списком в БД, в случае конфликта, перезаписываем
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Book>)
}