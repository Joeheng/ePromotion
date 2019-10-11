package com.example.myapplication.data.service

import com.example.myapplication.model.response.ApiResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("/api/promotions.json")
    fun getApiwithRx():Flowable<ApiResponse>
}