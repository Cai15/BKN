package com.example.bkn.domain

import com.example.bkn.data.API
import com.example.bkn.data.Enity.TmdbResults
import com.example.bkn.data.MainRepository
import com.example.bkn.data.TmdbApi
import com.example.bkn.utils.Converter
import com.example.bkn.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val response: MainRepository, private val retrofitService: TmdbApi) {
    //В конструктор мы будм передавать коллбэк из вьюмоделе, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, котороую нужно загрузить (это для пагинации)
    fun getBooksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getBooks(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDTOList(response.body()?.tmdbBooks))
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
}