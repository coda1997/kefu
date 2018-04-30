package com.example.overl.kefu

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by overl on 2018/4/30.
 */
class Fragment3 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_main_3,container,false)
        view?.apply { initView(this) }//if view is null, skip initialization
        return view?:throw Exception()
    }

    //initialize fragment when drawing the fragment
    private fun initView(view: View){

    }

}