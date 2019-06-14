package com.example.swapnil.verticalviewpager.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.swapnil.verticalviewpager.service.model.Articles
import com.example.swapnil.verticalviewpager.view.fragment.VerticalFragment


class VerticalViewPagerAdapter(fm: FragmentManager, dataModels: List<Articles>) : FragmentPagerAdapter(fm) {

    internal var articles: List<Articles> = ArrayList()

    init {
        this.articles = dataModels
    }

    override fun getItem(position: Int): Fragment {
        return VerticalFragment.newInstance(articles.get(position))

    }

    override fun getCount(): Int {
        return articles.size
    }
}