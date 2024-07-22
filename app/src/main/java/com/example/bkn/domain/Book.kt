package com.example.bkn.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String,
    val poster: String,
    val description: String,
   // var rating: Double = 0.0,
    var isInFavorites: Boolean = false
) : Parcelable


