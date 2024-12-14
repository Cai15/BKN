package com.example.bkn.domain

import com.example.bkn.data.API
import com.example.bkn.data.Enity.Book
import com.example.bkn.data.Enity.TmdbResults
import com.example.bkn.data.MainRepository
import com.example.bkn.data.TmdbApi
import com.example.bkn.data.preferenes.PreferenceProvider
import com.example.bkn.utils.Converter
import com.example.bkn.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    fun getBooksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список книг
        retrofitService.getBooks(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список книг
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbBooks)
                //Кладем книги в бд
                list.forEach {
                    repo.putToDb(list)
                }
                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.geDefaultCategory()

    fun getBooksFromDB(): List<Book> = repo.getAllFromDB()
}