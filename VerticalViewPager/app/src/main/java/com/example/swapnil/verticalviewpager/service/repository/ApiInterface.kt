package com.example.swapnil.verticalviewpager.service.repository

import com.example.swapnil.verticalviewpager.service.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getTrendingNews(@Query("country")  country:String,@Query("apikey") apikey:String): Call<NewsResponse>

    @GET("everything")
    fun getNewsByCategory(@Query("q")  q:String,@Query("from") from:String,@Query("sortBy")sortBy:String, @Query("apikey") apikey:String): Call<NewsResponse>
}