package com.example.bkn.data


import com.example.bkn.data.Enity.TmdbResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TmdbApi {
    @GET("3/movie/popular")
    fun getBooks(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResults>
}