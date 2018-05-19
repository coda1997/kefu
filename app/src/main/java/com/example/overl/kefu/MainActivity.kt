package com.example.overl.kefu

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.overl.kefu.community.Fragment3
import com.example.overl.kefu.main.Fragment1
import com.example.overl.kefu.mine.Fragment4
import com.example.overl.kefu.news.Fragment2
import com.example.overl.kefu.scan.ScanActivity

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
        fragmentManager.beginTransaction().add(R.id.fragment_main, fragment1).show(fragment1).commit()
        fgs.add(fragment1);changeButtonAndImage(1)
        find<LinearLayout>(R.id.button_1).onClick {
            fragmentManager.selectFragment(fragment1, fgs)
            changeButtonAndImage(1)
        }
        find<LinearLayout>(R.id.button_2).onClick {
            fragmentManager.selectFragment(fragment2, fgs)
            changeButtonAndImage(2)
        }
        find<LinearLayout>(R.id.button_3).onClick {
            fragmentManager.selectFragment(fragment3, fgs)
            changeButtonAndImage(3)
        }
        find<LinearLayout>(R.id.button_4).onClick {
            fragmentManager.selectFragment(fragment4, fgs)
            changeButtonAndImage(4)
        }
        find<LinearLayout>(R.id.button_scan).onClick {
            startActivity<ScanActivity>()
        }

    }

    val image1: ImageView by lazy { find<ImageView>(R.id.button_1_im) }
    val image2: ImageView by lazy { find<ImageView>(R.id.button_2_im) }
    val image3: ImageView by lazy { find<ImageView>(R.id.button_3_im) }
    val image4: ImageView by lazy { find<ImageView>(R.id.button_4_im) }
    val text1: TextView by lazy { find<TextView>(R.id.button_1_tv) }
    val text2: TextView by lazy { find<TextView>(R.id.button_2_tv) }
    val text3: TextView by lazy { find<TextView>(R.id.button_3_tv) }
    val text4: TextView by lazy { find<TextView>(R.id.button_4_tv) }
    var preFragment:Int = 4
    private fun changeButtonAndImage(index: Int) {
        changeButtonBack(preFragment)
        when (index) {
            1 -> {
                image1.setImageResource(R.drawable.nav_ic_sel)
                text1.setTextColor(resources.getColor(R.color.green))
            }
            2 ->{
                image2.setImageResource(R.drawable.tab_ic_sel_2)
                text2.setTextColor(resources.getColor(R.color.green))
            }
            3->{
                image3.setImageResource(R.drawable.tab_ic_sel_3)
                text3.setTextColor(resources.getColor(R.color.green))
            }
            4->{
                image4.setImageResource(R.drawable.me)
                text3.setTextColor(resources.getColor(R.color.green))
            }
        }
        preFragment=index
    }
    private fun changeButtonBack(index: Int){
        when(index){
            1 -> {
                image1.setImageResource(R.drawable.tab_ic_nor_1)
                text1.setTextColor(resources.getColor(R.color.black))
            }
            2 ->{
                image2.setImageResource(R.drawable.tab_ic_nor_2)
                text2.setTextColor(resources.getColor(R.color.black))
            }
            3 -> {
                image3.setImageResource(R.drawable.tab_ic_nor_4)
                text3.setTextColor(resources.getColor(R.color.black))
            }
            4 ->{
                image4.setImageResource(R.drawable.tab_ic_nor_5)
                text4.setTextColor(resources.getColor(R.color.black))
            }
        }
    }
}

private fun FragmentManager.selectFragment(fragment: Fragment, fgs: MutableList<Fragment>) {
    val transaction = beginTransaction()
    fgs.forEach { transaction.hide(it) }
    if (!fgs.contains(fragment)) {
        transaction.add(R.id.fragment_main, fragment)
        fgs.add(fragment)
    }
    transaction.show(fragment)
    transaction.commit()
}
