package com.example.swapnil.verticalviewpager.view.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.example.swapnil.verticalviewpager.R

class TypefaceTextView : android.support.v7.widget.AppCompatTextView {
    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView)
            val fontName = a.getString(R.styleable.TypefaceTextView_fontName)
            if (fontName != null) {
                val myTypeface = Typeface.createFromAsset(context.assets, "fonts/$fontName")
                typeface = myTypeface
            }
            a.recycle()
        }
    }

}
