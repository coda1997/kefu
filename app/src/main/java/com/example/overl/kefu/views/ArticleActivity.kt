package com.example.overl.kefu.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.example.overl.kefu.R
import org.jetbrains.anko.find

/**
 * Created by overl on 2018/5/22.
 */
class ArticleActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_detail)
        initView()
    }

    private fun initView() {
        val webView = find<WebView>(R.id.web_view)
        webView.loadData("","text/html","utf-8")
    }
}