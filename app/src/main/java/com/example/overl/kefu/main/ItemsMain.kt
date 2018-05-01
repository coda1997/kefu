package com.example.overl.kefu.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.ImageView

/**
 * Created by overl on 2018/4/30.
 */
class TopImage(context: Context?) : ImageView(context) {
    override fun onDraw(canvas: Canvas?) {
        if (drawable==null){
            return
        }
        TODO()
    }

}
data class ClassItem(val name:String, val bitmap: Bitmap)
data class ImageItem(val tag:Int,val bitmap: Bitmap)
