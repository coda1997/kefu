package com.example.overl.kefu.mine

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.overl.kefu.R
import org.jetbrains.anko.find

/**
 * Created by overl on 2018/4/30.
 */
class Fragment4 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_main_4,container,false)
        view?.apply { initView(this) }//if view is null, skip initialization
        return view?:throw Exception()
    }

    //initialize fragment when drawing the fragment
    private fun initView(view: View){
        val headBlurImage = view.find<ImageView>(R.id.h_back)
        val headAvaImage = view.find<ImageView>(R.id.h_head)
    }

}