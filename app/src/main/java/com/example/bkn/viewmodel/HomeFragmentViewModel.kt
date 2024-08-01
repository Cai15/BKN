package com.example.bkn.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bkn.App
import com.example.bkn.data.Enity.Book
import com.example.bkn.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject


class HomeFragmentViewModel : ViewModel() {
    val booksListLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getFilms()
    }

    fun getFilms() {
        interactor.getBooksFromApi(1, object : ApiCallback {
            override fun onSuccess(books: List<Book>) {
                booksListLiveData.postValue(books)
            }

            override fun onFailure() {
                Executors.newSingleThreadExecutor().execute {
                    booksListLiveData.postValue(interactor.getBooksFromDB())
                }
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(Books: List<Book>)
        fun onFailure()
    }
}