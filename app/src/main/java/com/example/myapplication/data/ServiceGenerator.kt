package com.example.myapplication.data

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val BASE_URL = "http://everpromotion.com"
    private val API_KEY = ""

    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val okHttp = OkHttpClient.Builder()

    fun <S> createSerive(serviceClass: Class<S>): S {
        okHttp.addInterceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()
                .addHeader("Authorization", API_KEY)
                .header("Accept", "application/json")
                .method(request.method(), request.body())

            chain.proceed(requestBuilder.build())
        }
        return builder.build().create(serviceClass)
    }
}
