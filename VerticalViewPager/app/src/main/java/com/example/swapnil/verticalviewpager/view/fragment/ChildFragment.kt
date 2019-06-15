package com.example.swapnil.verticalviewpager.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.swapnil.verticalviewpager.R
import com.example.swapnil.verticalviewpager.service.model.Articles
import kotlinx.android.synthetic.main.fragment_child.view.*
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_vertical.view.*
import java.text.SimpleDateFormat
import java.util.*


class ChildFragment : Fragment() {
    var options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootview: View = inflater.inflate(R.layout.fragment_child, container, false)
        if (arguments!!.getBoolean("isWebView")) {
            //rootview.nestedScrollView.visibility = View.VISIBLE
            rootview.rl.visibility = View.GONE
            rootview.button.visibility = View.GONE
            rootview.txtDescription.visibility = View.GONE

           /* rootview.webView.loadUrl((arguments!!.get("article") as Articles).url);
            rootview.webView.webViewClient = WebViewClient()*/
        } else {
            //rootview.nestedScrollView.visibility = View.GONE
            rootview.rl.visibility = View.VISIBLE
            rootview.txtDescription.visibility = View.VISIBLE
            rootview.button.visibility = View.VISIBLE
            rootview.txtTitle.text = (arguments!!.get("article") as Articles).title
            Glide.with(activity).load((arguments!!.get("article") as Articles).urlToImage).into(rootview.newsImage)
            var date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse((arguments!!.get("article") as Articles).publishedAt)
            val dateString:String = SimpleDateFormat("dd/MM/yyyy").format(date.time)
            rootview.date.text = dateString
            rootview.newsSource.text = (arguments!!.get("article") as Articles).source!!.name
            rootview.bottomTitle.text = (arguments!!.get("article") as Articles).title
            if ((arguments!!.get("article") as Articles).description != null)
                rootview.txtDescription.text = (arguments!!.get("article") as Articles).description
        }

        rootview.button.setOnClickListener {
            // rootview.nestedViewPager.currentItem=1
        }

        return rootview
    }


    companion object {

        @JvmStatic
        fun newInstance(article: Articles, iswebView: Boolean) =
                ChildFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("article", article)
                        putBoolean("isWebView", iswebView)
                    }
                }
    }
}
