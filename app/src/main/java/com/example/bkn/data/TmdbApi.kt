package com.example.bkn.data


import com.example.bkn.data.Enity.TmdbResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbApi {
    @GET("{id}")
    fun getBooks(
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
       // @Query("page") page: Int
    ): Call<TmdbResults>
}