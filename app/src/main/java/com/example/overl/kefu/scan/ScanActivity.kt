package com.example.overl.kefu.scan

import android.app.Activity
import android.os.Bundle
import android.view.Window
import com.example.overl.kefu.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by overl on 2018/4/30.
 */
class ScanActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_scan)
        initView()
    }

    private fun initView() {
        linearLayout {
            textView {
                text="Scan activity"
            }
            button(text = "back to main").onClick {
                finish()
            }
        }

    }
}