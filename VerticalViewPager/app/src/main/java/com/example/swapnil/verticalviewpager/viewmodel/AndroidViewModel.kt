package com.example.swapnil.verticalviewpager.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.swapnil.verticalviewpager.service.repository.RetrofitService
import com.example.swapnil.verticalviewpager.service.model.NewsResponse

class AndroidViewModel: ViewModel() {

    private val mService  = RetrofitService()
    fun getTrending(country: String, apiKey: String): MutableLiveData<NewsResponse>? {
        return mService.loadTrending(country,apiKey)
    }
    fun getNewsByCategory(categoryName: String, date: String, apiKey: String) : MutableLiveData<NewsResponse>? {
        return mService.loadNewsByCategory(categoryName,date,apiKey)
    }
}