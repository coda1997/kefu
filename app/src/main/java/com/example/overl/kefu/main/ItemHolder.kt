package com.example.overl.kefu.main

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.overl.kefu.R
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by overl on 2018/5/1.
 */
open class NormalItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var newsTitle: TextView = itemView.find(R.id.base_swipe_item_title)
    internal var newsIcon: ImageView = itemView.find(R.id.base_swipe_item_icon)
    init {
        itemView.find<LinearLayout>(R.id.base_swipe_item_container).onClick {
            //onclick item event here
        }
    }
}

class GroupItemHolder(itemView: View) :NormalItemHolder(itemView){
    internal var newsTime :TextView = itemView.find(R.id.base_swipe_group_item_time)
}

class NewsListAdapter(private val context: Context, private val dataList:List<NewsListEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val NORMAL_ITEM = 0
        val GROUP_ITEM = 1
    }
    private val layoutFlater = LayoutInflater.from(context)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val entity = dataList[position]
        if (holder is GroupItemHolder){
            bindGroupItem(entity,holder)
        }else if (holder is NormalItemHolder){
            bindNormalItem(entity,holder.newsTitle,holder.newsIcon)
        }
    }

    private fun bindNormalItem(entity: NewsListEntity, newsTitle: TextView, newsIcon: ImageView) {
        if (entity.iconUrl==""){

            if (newsIcon.visibility!=View.GONE){
                newsIcon.visibility=View.GONE
            }
        }else{
        }

    }

    private fun bindGroupItem(entity: NewsListEntity, holder: GroupItemHolder) {


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType== NORMAL_ITEM){
            NormalItemHolder(layoutFlater.inflate(R.layout.fragment_base_swipe_item,parent,false))
        }else{
            GroupItemHolder(layoutFlater.inflate(R.layout.fragment_base_swipe_group_item,parent,false))
        }

    }

    override fun getItemCount(): Int =dataList.size

}

data class NewsListEntity (val newID:Int,val title:String,val iconUrl:String="",val publishDate:String,val recommendAmount:Int=0,
                           val commentAmount:Int=0)
