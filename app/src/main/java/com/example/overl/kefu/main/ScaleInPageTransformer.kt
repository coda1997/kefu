package com.example.overl.kefu.main

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by overl on 2018/4/30.
 */
class ScaleInPageTransformer : ViewPager.PageTransformer{
    override fun transformPage(page: View?, position: Float) {
        if (page!=null){
            when {
                position < -1 -> page.scaleY=MIN_SCALE
                position<=0 -> page.scaleY=MIN_SCALE+(position+1)*(1-MIN_SCALE)
                position<=1 -> page.scaleY=MIN_SCALE+(1-position)*(1-MIN_SCALE)
                else -> page.scaleY = MIN_SCALE
            }
        }
    }

    private val MIN_SCALE = 0.7f


}