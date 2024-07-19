package com.example.bkn.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bkn.App
import com.example.bkn.domain.Book
import com.example.bkn.domain.Interactor
import javax.inject.Inject


class HomeFragmentViewModel : ViewModel() {
    val BooksListLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        interactor.getBooksFromApi(1, object : ApiCallback {
            override fun onSuccess(Books: List<Book>) {
                BooksListLiveData.postValue(Books)
            }

            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(Books: List<Book>)
        fun onFailure()
    }
}