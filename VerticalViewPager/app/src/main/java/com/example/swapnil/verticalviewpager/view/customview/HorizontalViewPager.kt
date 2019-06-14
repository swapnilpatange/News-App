package com.example.swapnil.verticalviewpager.view.customview

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet


class HorizontalViewPager : ViewPager {


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}

    override fun canScrollVertically(direction: Int): Boolean {
        return false
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }


}