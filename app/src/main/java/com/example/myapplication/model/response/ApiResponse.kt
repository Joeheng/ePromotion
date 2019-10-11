package com.example.myapplication.model.response

import com.example.myapplication.model.Cardview
import com.google.gson.annotations.SerializedName

var cards : List<Cardview> = ArrayList()
data class ApiResponse(
    val count : Int,
    @SerializedName("promotions") val cards : List<Cardview>)

fun getItems(): List<Cardview>? {
    return cards
}

