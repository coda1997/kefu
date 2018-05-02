package com.example.overl.kefu

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.LinearLayout
import com.example.overl.kefu.community.Fragment3
import com.example.overl.kefu.main.Fragment1
import com.example.overl.kefu.mine.Fragment4
import com.example.overl.kefu.news.Fragment2

import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    val fragment1: Fragment1 by lazy { Fragment1() }
    val fragment2: Fragment2 by lazy { Fragment2() }
    val fragment3: Fragment3 by lazy { Fragment3() }
    val fragment4: Fragment4 by lazy { Fragment4() }
    val fgs = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
//        Log.d("start Activity","main")
        initFragment()

    }

    private fun initFragment() {
        fragmentManager.beginTransaction().add(R.id.fragment_main,fragment1).show(fragment1).commit()
        fgs.add(fragment1)
        find<LinearLayout>(R.id.button_1).onClick {
            fragmentManager.selectFragment(fragment1,fgs)
        }
        find<LinearLayout>(R.id.button_2).onClick {
            fragmentManager.selectFragment(fragment2,fgs)
        }
        find<LinearLayout>(R.id.button_3).onClick {
            fragmentManager.selectFragment(fragment3,fgs)
        }
        find<LinearLayout>(R.id.button_4).onClick {
            fragmentManager.selectFragment(fragment4,fgs)
        }
        find<LinearLayout>(R.id.button_scan).onClick {
            startActivity<ScanActivity>()
        }

    }


}

private fun FragmentManager.selectFragment(fragment:Fragment,fgs:MutableList<Fragment>){
    val transaction = beginTransaction()
    fgs.forEach { transaction.hide(it) }
    if (!fgs.contains(fragment)) {
        transaction.add(R.id.fragment_main,fragment)
        fgs.add(fragment)
    }
    transaction.show(fragment)
    transaction.commit()
}
