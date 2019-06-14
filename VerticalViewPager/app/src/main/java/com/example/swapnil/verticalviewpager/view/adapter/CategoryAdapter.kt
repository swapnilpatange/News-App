package com.example.swapnil.verticalviewpager.view.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swapnil.verticalviewpager.R
import com.example.swapnil.verticalviewpager.view.activity.MainActivity
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter : Adapter<CategoryAdapter.CategoryHolder> {
    val categories: List<String>
    val activity: Activity

    constructor(categories: List<String>, activity: Activity) : super() {
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
        fun bindItems(category: String, activity: Activity) {
            itemView.categoryName.text = category
            itemView.setOnClickListener { view ->
                val intent: Intent = Intent(activity, MainActivity::class.java)
                if (category.equals("trending"))
                    intent.putExtra("isTrending", true)
                else
                    intent.putExtra("isTrending", false)
                intent.putExtra("categoryName", category)

                activity.startActivity(intent)
            }
        }
    }
}