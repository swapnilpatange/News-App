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
import android.webkit.WebViewClient
import com.example.swapnil.verticalviewpager.R
import com.example.swapnil.verticalviewpager.service.model.Articles
import com.example.swapnil.verticalviewpager.view.adapter.ChildViewPagerAdapter
import com.example.swapnil.verticalviewpager.view.adapter.VerticalViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_child.view.*
import kotlinx.android.synthetic.main.fragment_vertical.view.*


class VerticalFragment : Fragment() {
    var rootView: View? = null
    var articles: ArrayList<Articles>? = null
    private var mLastPositionOffset: Float = 0f


    /*  override fun onPageScrollStateChanged(p0: Int) {

      }

      override fun onPageScrolled(position: Int, p1: Float, p2: Int) {
          tv!!.trigger(position);
      }

      override fun onPageSelected(position: Int) {

          Log.d("API123", "onPageSelected " + position);
      }
  */
    var mActivity: Activity? = null

    //var tv: ToggleVerticalViewPagerScrolling? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_vertical, container, false)

        articles = arguments!!.get("article") as ArrayList<Articles>
        if (arguments!!.get("position") == 0) {
            rootView!!.nestedViewPager.adapter = ChildViewPagerAdapter(childFragmentManager, arguments!!.get("article") as ArrayList<Articles>)
            rootView!!.webView.visibility = View.GONE
            //rootView.nestedViewPager.addOnPageChangeListener(this)
        } else {
            rootView!!.nestedViewPager.visibility = View.GONE
            rootView!!.webView.loadUrl("");
            rootView!!.webView.webViewClient = WebViewClient()
        }
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
            // tv = mActivity as ToggleVerticalViewPagerScrolling
        } catch (e: ClassCastException) {
            throw ClassCastException("Error in retrieving data. Please try again")
        }


    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(article: ArrayList<Articles>, position: Int) =
                VerticalFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList("article", article);
                        putInt("position", position)
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

    fun loadUrl(position: Int) {
        rootView!!.nestedViewPager.visibility = View.GONE
        rootView!!.webView.visibility = View.VISIBLE
        rootView!!.webView.loadUrl(articles!!.get(position).url);
        rootView!!.webView.webViewClient = WebViewClient()
    }
}
