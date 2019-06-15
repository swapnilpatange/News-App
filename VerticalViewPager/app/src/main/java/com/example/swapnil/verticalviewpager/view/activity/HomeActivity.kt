package com.example.swapnil.verticalviewpager.view.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.swapnil.verticalviewpager.R
import com.example.swapnil.verticalviewpager.view.adapter.CategoryAdapter

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.swapnil.verticalviewpager.service.model.CategoryModel


class HomeActivity : AppCompatActivity() {
    var categories: List<CategoryModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        categories= listOf(CategoryModel("trending",R.drawable.trending),CategoryModel("India",R.drawable.politics),
                CategoryModel("Bollywood",R.drawable.bollywood), CategoryModel("Cricket",R.drawable.cricket),
                CategoryModel("Sports",R.drawable.sport),CategoryModel("Business",R.drawable.trending),
                CategoryModel("Indian Politics",R.drawable.politics),CategoryModel("Technology",R.drawable.politics),CategoryModel("Fashion",R.drawable.fashion),
                CategoryModel("health",R.drawable.politics))
       // categories = listOf("trending","India","Bollywood","Cricket", "Sports", "Business", "Indian Politics", "Technology","Fashion","health")
        categoryList.adapter = CategoryAdapter(categories!!, this)
        categoryList.layoutManager = GridLayoutManager(this, 2)
        categoryList.addItemDecoration(SpacesItemDecoration(20))
    }

    inner class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View,
                                    parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
            if (parent.getChildLayoutPosition(view) <= 1) {
                outRect.top = 2*space
            } else {
                outRect.top = space
            }
        }
    }


}
