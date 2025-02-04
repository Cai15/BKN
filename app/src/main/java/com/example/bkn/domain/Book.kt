package com.example.bkn.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String,
    val poster: String, //У нас будет приходить ссылка на картинку, так что теперь это String
    val description: String,
    var rating: Double = 0.0, //Приходит не целое число с API
    var isInFavorites: Boolean = false
) : Parcelable


