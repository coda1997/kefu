package com.example.overl.kefu

import android.database.DataSetObserver
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView

/**
 * Created by overl on 2018/4/30.
 */
class MyImageAdapter() : PagerAdapter() {
    private lateinit var mItems:List<View>
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return mItems.size
    }
    constructor(items:List<View>) : this() {
        mItems=items
    }
    override fun instantiateItem(container: ViewGroup?, position: Int): Any {

        container?.addView(mItems[position])
        return mItems[position]
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(mItems[position])
    }

    fun addAddItems(items:List<View>){
        mItems=items
    }
}