package com.example.overl.kefu.community

import android.app.Fragment
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
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
import com.example.overl.kefu.main.*
import com.example.overl.kefu.views.ViewPagerForScrollView
import com.jude.easyrecyclerview.EasyRecyclerView
import com.jude.easyrecyclerview.decoration.DividerDecoration
import com.jude.rollviewpager.Util
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by overl on 2018/4/30.
 */
class Fragment3 : Fragment(), ViewPager.OnPageChangeListener {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_main_3, container, false)
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

        val list = mutableListOf<View>()
        //module
        list.add(layoutInflater.inflate(R.layout.view_main_1,null,false).apply { initComView1(this) })
        list.add(layoutInflater.inflate(R.layout.view_main_1,null,false).apply { initComView1(this) })
        vp.adapter = AdapterMain2(list)
        vp.currentItem = 0;changeTab(1,view)
        view.find<LinearLayout>(R.id.tag_main_3_1).onClick {

            vp.currentItem = 0
            changeTab(1,view)
        }
        view.find<LinearLayout>(R.id.tag_main_3_2).onClick {

            vp.currentItem = 1
            changeTab(2,view)
        }
        vp.addOnPageChangeListener(this)

    }
    private fun changeTab(index:Int,view: View){
        when(index){
            1->{
                view.find<ImageView>(R.id.img_tab_1).setImageResource(R.drawable.com_menu_ic_sel_1)
                view.find<TextView>(R.id.tv_tab_1).setTextColor(resources.getColor(R.color.green))
                view.find<ImageView>(R.id.img_tab_2).setImageResource(R.drawable.com_menu_ic_nor_2)
                view.find<TextView>(R.id.tv_tab_2).setTextColor(resources.getColor(R.color.gray))
            }
            2->{
                view.find<ImageView>(R.id.img_tab_2).setImageResource(R.drawable.com_menu_ic_sel_2)
                view.find<TextView>(R.id.tv_tab_2).setTextColor(resources.getColor(R.color.green))
                view.find<ImageView>(R.id.img_tab_1).setImageResource(R.drawable.com_menu_ic_nor_1)
                view.find<TextView>(R.id.tv_tab_1).setTextColor(resources.getColor(R.color.gray))
            }
        }
    }


    private val handle = Handler()

    private fun initComView1(view: View?) {
        val adapter = NewsAdapter(context)
        adapter.setMore(R.layout.view_more){
            handle.postDelayed({
                Log.d("load","more")
            },2000)
        }
        val rv  = view?.find<EasyRecyclerView>(R.id.recyclerView_main_1)
        rv?.isNestedScrollingEnabled=false//???
        val layoutManager = LinearLayoutManager(context)
        rv?.setLayoutManager(layoutManager)
//        val itemDecoration = DividerDecoration(Color.GRAY, Util.dip2px(context,3f), Util.dip2px(context,72f),0)
//        itemDecoration.setDrawLastItem(false)
//        rv?.addItemDecoration(itemDecoration)
        val list = mutableListOf<NewsListEntity>()
        (0 ..10).forEach {
            list.add(NewsListEntity(it,"kefu",excerpt = "hello world",publishDate = ""))
        }
        adapter.addAll(list)
        rv?.setAdapterWithProgress(adapter)

    }

    private fun initTopImageBanner(view: View) {
        val vp = view.find<ViewPager>(R.id.rv_main_top_image)
        vp.pageMargin = 10
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

    }

}