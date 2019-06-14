package com.example.swapnil.verticalviewpager.service.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.swapnil.verticalviewpager.service.model.NewsResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    val liveUserResponse: MutableLiveData<NewsResponse> = MutableLiveData()
    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            Log.e("retrofit","create")
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://newsapi.org/v2/")
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
    fun loadTrending(country: String, apiKey: String): MutableLiveData<NewsResponse>? {
        val retrofitCall  = create().getTrendingNews(country,apiKey)
        retrofitCall.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
            }
            override fun onResponse(call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {
                val list  = response.body()

                liveUserResponse?.value = list

            }
        })
        return liveUserResponse
    }

    fun loadNewsByCategory(categoryName: String, date: String, apiKey: String): MutableLiveData<NewsResponse>? {
        val retrofitCall  = create().getNewsByCategory(categoryName,date,"popularity",apiKey)
        retrofitCall.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
            }
            override fun onResponse(call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {
                val list  = response.body()

                liveUserResponse?.value = list

            }
        })
        return liveUserResponse
    }
}