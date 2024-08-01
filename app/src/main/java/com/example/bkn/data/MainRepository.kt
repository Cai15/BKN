package com.example.bkn.data

import com.example.bkn.data.Enity.Book
import com.example.bkn.data.dao.BookDao
import java.util.concurrent.Executors

class MainRepository(private val filmDao: BookDao) {

    fun putToDb(books: List<Book>) {
        //Запросы в бд должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(books)
        }
    }

    fun getAllFromDB(): List<Book> {
        return filmDao.getCachedFilms()
    }
}