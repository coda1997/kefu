package com.example.overl.kefu.news

import android.app.Fragment
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overl.kefu.MyImageAdapter
import com.example.overl.kefu.R
import com.example.overl.kefu.views.ViewPagerForScrollView
import com.example.overl.kefu.main.*
import com.jude.easyrecyclerview.EasyRecyclerView
import com.jude.easyrecyclerview.decoration.DividerDecoration
import com.jude.rollviewpager.Util
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * Created by overl on 2018/4/30.
 */
class Fragment2 : Fragment(),ViewPager.OnPageChangeListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_main_2, container, false)
        view?.apply { initView(this) }//if view is null, skip initialization
        return view ?: throw Exception()
    }

    //initialize fragment when drawing the fragment
    private fun initView(view: View) {
        Log.d("fragment", "2 start")
        initTopImageBanner(view)
        initNewsViewPager(view)
    }

    private fun initNewsViewPager(view: View) {
        val vp = view.find<ViewPagerForScrollView>(R.id.news_viewpager)
        val list = mutableListOf<View>().apply {
            val view1 = layoutInflater.inflate(R.layout.view_main_1, null, false)
            initNews(view1)
            add(view1)
        }

        vp.adapter = AdapterMain2(list)
        vp.currentItem=0
        Log.d("new viewpager", "count:  ${list.size}")
        vp.addOnPageChangeListener(this)
    }

    private fun initNews(view: View?) {
        val adapter = NewsAdapter(context)
        adapter.setMore(R.layout.view_more){
            Handler().postDelayed({
                Log.d("load","more")
            },2000)
        }
        val rv = view?.find<EasyRecyclerView>(R.id.recyclerView_main_1)
        toast(rv.toString())
        rv?.isNestedScrollingEnabled = false
        val linearLayoutManager = LinearLayoutManager(context)
        rv?.setLayoutManager(linearLayoutManager)

        val itemDecoration = DividerDecoration(Color.GRAY, Util.dip2px(context, 16f), Util.dip2px(context, 72f), 0)
        itemDecoration.setDrawLastItem(false)
        rv?.addItemDecoration(itemDecoration)
        val list = mutableListOf<NewsListEntity>()
        (0..5).forEach {
            list.add(NewsListEntity(it, "kefu", excerpt = "hello world", publishDate = ""))
        }

        adapter.addAll(list)
        rv?.setAdapterWithProgress(adapter)
    }

    private fun initTopImageBanner(view: View) {
        val vp: ViewPager = view.find(R.id.rv_news_top_image)
        vp.pageMargin = 10
        vp.setPageTransformer(true,ScaleInPageTransformer())
        val list = mutableListOf<View>()
        (0..2).forEach {
            val iv = layoutInflater.inflate(R.layout.top_image, null)
            iv?.apply { list.add(this) }
        }
        list.forEach {
            val iv = it.find<BannerImageView>(R.id.top_image_view)
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.text_main1)
            iv.setImageBitmap(bitmap)
        }
        vp.adapter = MyImageAdapter(list)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        Log.d("new viewpager","page changed")
    }

}
