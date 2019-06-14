package com.example.swapnil.verticalviewpager.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.View
import com.example.swapnil.verticalviewpager.R
import com.example.swapnil.verticalviewpager.service.model.NewsResponse
import com.example.swapnil.verticalviewpager.view.fragment.VerticalFragment
import com.example.swapnil.verticalviewpager.view.adapter.VerticalViewPagerAdapter
import com.example.swapnil.verticalviewpager.viewmodel.AndroidViewModel
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), VerticalFragment.ToggleVerticalViewPagerScrolling {

    var apiKey: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiKey = resources.getString(R.string.apikey)
        val country: String = "in"
        if (intent.getBooleanExtra("isTrending", false))
            getTrendingNews(country,apiKey)
        else
            getNews(intent.getStringExtra("categoryName"),apiKey)

    }

    private fun getNews(categoryName: String?, apiKey: String) {
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: String = simpleDateFormat.format(Date())
        val mAndroidViewModel = ViewModelProviders.of(this@MainActivity).get(AndroidViewModel::class.java)
        mAndroidViewModel.getNewsByCategory(categoryName!!, date,apiKey)?.observe(this, Observer<NewsResponse> { newsResponse ->
            Log.d("", "")
            if (newsResponse!!.status.equals("ok")) {
                val verticalPagerAdapter: VerticalViewPagerAdapter = VerticalViewPagerAdapter(supportFragmentManager, newsResponse!!.articles)
                verticalViewPager.adapter = verticalPagerAdapter
            }
        })

    }

    private fun getTrendingNews(country:String,apiKey: String) {
        val mAndroidViewModel = ViewModelProviders.of(this@MainActivity).get(AndroidViewModel::class.java)
        mAndroidViewModel.getTrending(country,apiKey)?.observe(this, Observer<NewsResponse> { newsResponse ->
            if (newsResponse!!.status.equals("ok")) {
                val verticalPagerAdapter: VerticalViewPagerAdapter = VerticalViewPagerAdapter(supportFragmentManager, newsResponse!!.articles)
                verticalViewPager.adapter = verticalPagerAdapter
            }
        })
    }

    override fun trigger(page: Int) {
        if (page == 1) {
            verticalViewPager.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    return true
                }
            })
        } else {
            verticalViewPager.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    return false
                }
            })
        }
    }
}
