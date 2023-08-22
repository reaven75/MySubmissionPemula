package com.example.mysubmissionpemula

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parpol(
    val name: String,
    val description: String,
    val photo: Int,
    val leader: String,
    val born: String,


) :Parcelable
