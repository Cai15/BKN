package com.example.bkn.utils

import com.example.bkn.data.Enity.TmdbBook
import com.example.bkn.domain.Book

object Converter {
    fun convertApiListToDTOList(list: List<TmdbBook>?): List<Book> {
        val result = mutableListOf<Book>()
        list?.forEach {
            result.add(
                Book(
                    title = it.title,
                    poster = it.posterPath,
                    description = it.overview,
                    //rating = it.voteAverage,
                    isInFavorites = false
                )
            )
        }
        return result
    }
}