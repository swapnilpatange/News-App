package com.example.swapnil.verticalviewpager.view.customview

import android.support.v4.view.ViewPager
import android.view.View
import android.support.v4.view.ViewCompat.setTranslationY
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.support.v4.view.ViewCompat.setTranslationX
import android.support.v4.view.ViewCompat.setAlpha
import android.support.v4.view.ViewCompat.setScaleY
import android.support.v4.view.ViewCompat.setScaleX



class VerticalPageTransformerAnimate:ViewPager.PageTransformer {

    private val MIN_SCALE = 0.75f
    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.getWidth()
        val pageHeight = view.getHeight()
        var alpha = 0f
        if (0 <= position && position <= 1) {
            alpha = 1 - position
        } else if (-1 < position && position < 0) {
            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            val verticalMargin = pageHeight * (1 - scaleFactor) / 2
            val horizontalMargin = pageWidth * (1 - scaleFactor) / 2
            if (position < 0) {
                view.setTranslationX(horizontalMargin - verticalMargin / 2)
            } else {
                view.setTranslationX(-horizontalMargin + verticalMargin / 2)
            }

            view.setScaleX(scaleFactor)
            view.setScaleY(scaleFactor)

            alpha = position + 1
        }

        view.setAlpha(alpha)
        view.setTranslationX(view.getWidth() * -position)
        val yPosition = position * view.getHeight()
        view.setTranslationY(yPosition)

    }
}