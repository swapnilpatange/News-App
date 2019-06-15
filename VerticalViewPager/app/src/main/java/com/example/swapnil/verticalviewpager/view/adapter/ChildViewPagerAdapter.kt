package com.example.swapnil.verticalviewpager.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.swapnil.verticalviewpager.service.model.Articles
import com.example.swapnil.verticalviewpager.view.fragment.ChildFragment


class ChildViewPagerAdapter : FragmentPagerAdapter {

   var articles:List<Articles> = ArrayList()

    constructor(fm: FragmentManager, articles: List<Articles>) : super(fm){
        this.articles=articles
    }

    override fun getCount(): Int {
        return articles.size
    }

    override fun getItem(position: Int): Fragment {
        return  ChildFragment.newInstance(articles.get(position),false)
        /*when (position) {
            0 -> return ChildFragment.newInstance(model, false)
            1 -> return ChildFragment.newInstance(model, true)
            else -> return ChildFragment.newInstance(model, true)
        }*/
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Child Fragment $position"
    }

}