package com.example.overl.kefu.main

import android.annotation.SuppressLint
import android.app.Fragment
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.overl.kefu.MyImageAdapter
import com.example.overl.kefu.R
import com.example.overl.kefu.views.ViewPagerForScrollView
import com.jude.easyrecyclerview.EasyRecyclerView
import com.jude.easyrecyclerview.decoration.DividerDecoration
import com.jude.rollviewpager.Util
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by overl on 2018/4/30.
 */
class Fragment1 : Fragment(), ViewPager.OnPageChangeListener {
    private var offset = 0
    private var currIndex = 0
    private var bmpWidth = 0
    private var one = 0
    private var two = 0
    lateinit var image1: ImageView
    lateinit var image2: ImageView
    lateinit var image3: ImageView
    lateinit var text1: TextView
    lateinit var text2: TextView
    lateinit var text3: TextView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_main, container, false)
        view?.apply { initView(this) }//if view is null, skip initialization
        return view ?: throw Exception()
    }

    //initialize fragment when drawing the fragment
    private fun initView(view: View) {
        image1 = view.find(R.id.tag_main_1_1_im)
        image2 = view.find(R.id.tag_main_1_2_im)
        image3 = view.find(R.id.tag_main_1_3_im)
        text1 = view.find(R.id.tag_main_1_1_tv)
        text2 = view.find(R.id.tag_main_1_2_tv)
        text3 = view.find(R.id.tag_main_1_3_tv)
        initTopImageBanner(view)
        initMainViewPagerAndTagHost(view)

    }

    private fun initMainViewPagerAndTagHost(view: View) {
        val vp = view.find<ViewPagerForScrollView>(R.id.main_viewpager)
        bmpWidth = BitmapFactory.decodeResource(resources, R.drawable.line).width
        val dsm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dsm)
        val screenW = dsm.widthPixels
        offset = (screenW / 3 - bmpWidth) / 2
        val matrix = Matrix()
        matrix.postTranslate(offset.toFloat(), 0f)
        view.find<ImageView>(R.id.img_cursor).imageMatrix = matrix
        one = offset * 2 + bmpWidth - 30
        two = one * 2 - 30

        val list = mutableListOf<View>()
        //module
        list.add(layoutInflater.inflate(R.layout.view_main_1, null, false).apply { initMainView1(this) })
        list.add(layoutInflater.inflate(R.layout.view_main_1, null, false).apply { initMainView1(this) })
        list.add(layoutInflater.inflate(R.layout.view_main_3, null, false).apply { initMainView3(this) })
        vp.adapter = AdapterMain2(list)
        vp.currentItem = 0
        tagChange(1,0)
        view.find<LinearLayout>(R.id.tag_main_1_1).onClick {
            vp.currentItem = 0
        }
        view.find<LinearLayout>(R.id.tag_main_1_2).onClick {
            vp.currentItem = 1
        }
        view.find<LinearLayout>(R.id.tag_main_1_3).onClick {
            vp.currentItem = 2
        }
        vp.addOnPageChangeListener(this)

    }

    private fun initMainView3(view: View?) {
        val adapter = VideoAdapter(context)
        val rv = view?.find<EasyRecyclerView>(R.id.recyclerView_main_3)
        rv?.isNestedScrollingEnabled = false

        val layoutManager = GridLayoutManager(context, 2)
        rv?.setLayoutManager(layoutManager)
        val list = mutableListOf<VideoEntity>()
        (0 until 10).forEach {
            list.add(VideoEntity(it, "kefu video"))
        }
        adapter.addAll(list)
        rv?.setAdapterWithProgress(adapter)
    }

    private val handle = Handler()
    private fun initMainView1(view: View?) {
        val adapter = NewsAdapter(context)
        adapter.setMore(R.layout.view_more) {
            handle.postDelayed({
                Log.d("load", "more")
            }, 2000)
        }
        val rv = view?.find<EasyRecyclerView>(R.id.recyclerView_main_1)
        rv?.isNestedScrollingEnabled = false//???
        val layoutManager = LinearLayoutManager(context)
        rv?.setLayoutManager(layoutManager)
        val itemDecoration = DividerDecoration(Color.GRAY, Util.dip2px(context, 16f), Util.dip2px(context, 72f), 0)
        itemDecoration.setDrawLastItem(false)
        rv?.addItemDecoration(itemDecoration)
        val list = mutableListOf<NewsListEntity>()
        (0..10).forEach {
            list.add(NewsListEntity(it, "kefu", excerpt = "hello world", publishDate = ""))
        }
        adapter.addAll(list)
        rv?.setAdapterWithProgress(adapter)
    }


    @SuppressLint("InflateParams")
    private fun initTopImageBanner(view: View) {
        val vp = view.find<ViewPager>(R.id.rv_main_top_image)
        vp.pageMargin = 30
        vp.setPageTransformer(true, ScaleInPageTransformer())
        val lists = mutableListOf<View>()
        val layoutInflater = layoutInflater
        (0..2).forEach {
            val imageView = layoutInflater.inflate(R.layout.top_image, null)
            imageView?.apply { lists.add(this) }
        }
        lists.forEach {
            val iv = it.find<BannerImageView>(R.id.top_image_view)
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.text_main1)
            // val matrix = Matrix().apply { reset();setScale(1f,1f,160f,200f) }
            iv.setImageBitmap(bitmap)
        }
        val imageItemsAdapter = MyImageAdapter(lists)
        vp.adapter = imageItemsAdapter
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        val animation: Animation = when (position) {
            0 -> {
                if (currIndex == 1) {
                    tagChange(1, 0)
                    TranslateAnimation(one.toFloat(), 0f, 0f, 0f)
                } else {
                    tagChange(2, 0)
                    TranslateAnimation(two.toFloat(), 0f, 0f, 0f)
                }
            }
            1 -> {
                if (currIndex == 0) {
                    tagChange(0, 1)
                    TranslateAnimation(offset.toFloat(), one.toFloat(), 0f, 0f)
                } else {
                    tagChange(2, 1)
                    TranslateAnimation(two.toFloat(), one.toFloat(), 0f, 0f)
                }
            }
            else -> {
                if (currIndex == 0) {
                    tagChange(0, 2)
                    TranslateAnimation(offset.toFloat(), two.toFloat(), 0f, 0f)
                } else {
                    tagChange(1, 2)
                    TranslateAnimation(one.toFloat(), two.toFloat(), 0f, 0f)
                }
            }
        }
        currIndex = position
        animation.fillAfter = true
        animation.duration = 300
        view.find<ImageView>(R.id.img_cursor).startAnimation(animation)

    }

    private fun tagChange(from: Int, to: Int) {
        when (to) {
            0 -> {
                image1.setImageResource(R.drawable.menu_ic_sel)
                text1.setTextColor(resources.getColor(R.color.green))
            }
            1 -> {
                image2.setImageResource(R.drawable.menu_ic_sel_2)
                text2.setTextColor(resources.getColor(R.color.green))
            }
            else -> {
                image3.setImageResource(R.drawable.menu_ic_sel_3)
                text3.setTextColor(resources.getColor(R.color.green))
            }
        }
        when (from) {
            0 -> {
                image1.setImageResource(R.drawable.menu_ic_nor_1)
                text1.setTextColor(resources.getColor(R.color.gray))
            }
            1 -> {
                image2.setImageResource(R.drawable.menu_ic_nor_2)
                text2.setTextColor(resources.getColor(R.color.gray))
            }
            else -> {
                image3.setImageResource(R.drawable.menu_ic_nor_3)
                text3.setTextColor(resources.getColor(R.color.gray))
            }
        }
    }
}
