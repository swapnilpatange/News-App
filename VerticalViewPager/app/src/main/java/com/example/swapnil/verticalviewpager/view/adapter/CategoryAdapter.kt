package com.example.swapnil.verticalviewpager.view.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.swapnil.verticalviewpager.R
import com.example.swapnil.verticalviewpager.service.model.CategoryModel
import com.example.swapnil.verticalviewpager.view.activity.MainActivity
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter : Adapter<CategoryAdapter.CategoryHolder> {
    val categories: List<CategoryModel>
    val activity: Activity

    constructor(categories: List<CategoryModel>, activity: Activity) : super() {
        this.categories = categories
        this.activity = activity
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryHolder {
        var view: View = LayoutInflater.from(p0.context).inflate(R.layout.category_item, null)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bindItems(categories[position], activity)
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(category: CategoryModel, activity: Activity) {
            itemView.categoryName.text = category.categoryName
            Glide.with(activity).load(category.categoryImage).into(itemView.categoryImage)
            itemView.setOnClickListener { view ->
                val intent: Intent = Intent(activity, MainActivity::class.java)
                if (category.equals("trending"))
                    intent.putExtra("isTrending", true)
                else
                    intent.putExtra("isTrending", false)
                intent.putExtra("categoryName", category.categoryName)

                activity.startActivity(intent)
            }
        }
    }
}