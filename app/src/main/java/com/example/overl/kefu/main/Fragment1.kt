package com.example.overl.kefu.main

import android.annotation.SuppressLint
import android.app.Fragment
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.overl.kefu.R
import com.example.overl.kefu.ViewPagerForScrollView
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_main, container, false)
        view?.apply { initView(this) }//if view is null, skip initialization
        return view ?: throw Exception()
    }

    //initialize fragment when drawing the fragment
    private fun initView(view: View) {
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
        matrix.postTranslate(offset.toFloat(),0f)
        view.find<ImageView>(R.id.img_cursor).imageMatrix = matrix
        one = offset*2+bmpWidth
        two = one*2

        val list = mutableListOf<View>()
        //module
        list.add(layoutInflater.inflate(R.layout.view_main_1,null,false))
        list.add(layoutInflater.inflate(R.layout.view_main_2,null,false))
        list.add(layoutInflater.inflate(R.layout.view_main_3,null,false))
        vp.adapter = AdapterMain2(list)
        vp.currentItem = 0
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

    @SuppressLint("InflateParams")
    private fun initTopImageBanner(view: View) {
        val vp = view.find<ViewPager>(R.id.rv_main_top_image)
        vp.pageMargin = 60
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
        val imageItemsAdapter = AdapterMain1(lists)
        vp.adapter = imageItemsAdapter
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        val animation: Animation = when (position) {
            0 -> {
                if (currIndex == 1) TranslateAnimation(one.toFloat(), 0f, 0f, 0f)
                else TranslateAnimation(two.toFloat(), 0f, 0f, 0f)
            }
            1 -> {
                if (currIndex == 0) TranslateAnimation(offset.toFloat(), one.toFloat(), 0f, 0f)
                else TranslateAnimation(two.toFloat(), one.toFloat(), 0f, 0f)
            }
            else -> {
                if (currIndex == 0) TranslateAnimation(offset.toFloat(), two.toFloat(), 0f, 0f)
                else TranslateAnimation(one.toFloat(), two.toFloat(), 0f, 0f)
            }
        }
        currIndex = position
        animation.fillAfter = true
        animation.duration = 300
        view.find<ImageView>(R.id.img_cursor).startAnimation(animation)
    }
}
