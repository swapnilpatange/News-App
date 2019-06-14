package com.example.swapnil.verticalviewpager.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Activity
import android.support.v4.view.ViewPager
import android.util.Log
import com.example.swapnil.verticalviewpager.R
import com.example.swapnil.verticalviewpager.service.model.Articles
import com.example.swapnil.verticalviewpager.view.adapter.ChildViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_vertical.view.*


class VerticalFragment : Fragment(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(position: Int, p1: Float, p2: Int) {
        tv!!.trigger(position);
    }

    override fun onPageSelected(position: Int) {

        Log.d("API123", "onPageSelected " + position);
    }

    var mActivity: Activity? = null
    var tv: ToggleVerticalViewPagerScrolling? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_vertical, container, false)

        rootView.nestedViewPager.adapter = ChildViewPagerAdapter(childFragmentManager, arguments!!.getSerializable("article") as Articles)

        rootView.nestedViewPager.addOnPageChangeListener(this)
        return rootView
    }


    override fun onDetach() {
        super.onDetach()

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }

        try {
            tv = mActivity as ToggleVerticalViewPagerScrolling
        } catch (e: ClassCastException) {
            throw ClassCastException("Error in retrieving data. Please try again")
        }


    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(article: Articles) =
                VerticalFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("article",article);
                        /*putString("date",article.publishedAt)
                        putString("")
                        putString("title", article.title)
                        putString("description", article.content)
                        putString("url", article.url)*/
                    }
                }
    }

    interface ToggleVerticalViewPagerScrolling {
        fun trigger(page: Int)

    }

}
