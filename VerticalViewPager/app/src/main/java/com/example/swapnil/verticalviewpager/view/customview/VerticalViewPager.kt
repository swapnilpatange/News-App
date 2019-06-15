package com.example.swapnil.verticalviewpager.view.customview

import android.content.Context
import android.view.MotionEvent
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.Field


class VerticalViewPager : ViewPager {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
        val mMinimumVelocity: Field
        mMinimumVelocity = ViewPager::class.java.getDeclaredField("mMinimumVelocity")
        mMinimumVelocity.setAccessible(true)
        mMinimumVelocity.set(this, -1)
    }

    private inner class VerticalPageTransformer : ViewPager.PageTransformer {
        override fun transformPage(view: View, position: Float) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.alpha = 1f
                // Counteract the default slide transition
                view.translationX = view.width * -position

                //set Y position to swipe in from top
                val yPosition = position * view.height
                view.translationY = yPosition
                view.scaleX = 1f
                view.scaleY = 1f

            } else if (position <= 1) { // [0,1]
                view.alpha = 1f

                // Counteract the default slide transition
                view.translationX = view.width * -position


                // Scale the page down (between MIN_SCALE and 1)
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
            }
        }


        private val MIN_SCALE = 0.75f

    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private fun swapXY(ev: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()

        val newX = ev.y / height * width
        val newY = ev.x / width * height

        ev.setLocation(newX, newY)

        return ev
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercepted = super.onInterceptTouchEvent(swapXY(ev))
        swapXY(ev)
        return intercepted
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapXY(ev))
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }
}
