package com.example.swapnil.verticalviewpager.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.swapnil.verticalviewpager.service.model.Articles
import com.example.swapnil.verticalviewpager.view.fragment.ChildFragment


class ChildViewPagerAdapter(fm: FragmentManager, internal var model: Articles) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ChildFragment.newInstance(model, false)
            1 -> return ChildFragment.newInstance(model, true)
            else -> return ChildFragment.newInstance(model, true)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Child Fragment $position"
    }

}