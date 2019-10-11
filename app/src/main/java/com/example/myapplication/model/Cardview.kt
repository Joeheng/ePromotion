package com.example.myapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cardview(
    val id: Int,
    @SerializedName("src") val img: String,
    @SerializedName("shop_name") val title:String,
    val description : String,
    @SerializedName("title") val promotion_Name : String
) : Serializable
