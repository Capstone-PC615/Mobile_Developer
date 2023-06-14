package com.capstone.trashsortapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place (
    val placeName: String,
    val placeImages: Int,
    val placeDescription: String,
    val placeClasses: String
): Parcelable