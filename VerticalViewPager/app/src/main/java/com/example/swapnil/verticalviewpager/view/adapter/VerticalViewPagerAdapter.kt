package com.example.swapnil.verticalviewpager.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.example.swapnil.verticalviewpager.service.model.Articles
import com.example.swapnil.verticalviewpager.view.fragment.VerticalFragment
import java.util.HashMap


class VerticalViewPagerAdapter : FragmentPagerAdapter {

    constructor(fm: FragmentManager, dataModels: ArrayList<Articles>) : super(fm) {
        this.articles = ArrayList()
        this.articles = dataModels
        this.fragmentManager=fm
        this.mfragmentTags= HashMap<Int, String>()
    }

    var fragmentManager: FragmentManager? = null
    internal var articles: ArrayList<Articles>

    private var mfragmentTags: HashMap<Int, String>? = null

    override fun getItem(position: Int): Fragment {
        return VerticalFragment.newInstance(articles,position)

    }

    override fun getCount(): Int {
        return 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val obj = super.instantiateItem(container, position)
        if (obj is Fragment) {
            val tag = obj.tag
            if (tag != null) {
                mfragmentTags!!.put(position, tag)
            }
        }
        return obj
    }

    fun getFragment(position: Int): Fragment? {
        val tag = mfragmentTags!!.get(position) ?: return null
        return fragmentManager!!.findFragmentByTag(tag)
    }

}