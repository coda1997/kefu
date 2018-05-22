package com.example.overl.kefu.main

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.example.overl.kefu.GlideApp
import com.example.overl.kefu.R
import com.example.overl.kefu.views.ArticleActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity


/**
 * Created by overl on 2018/5/1.
 */
class NewsAdapter(context: Context?) : RecyclerArrayAdapter<NewsListEntity>(context) {
    override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
        return NewsViewHolder(parent)
    }

    init {
        this.context = context
    }

    inner class NewsViewHolder(viewGroup: ViewGroup?) : BaseViewHolder<NewsListEntity>(viewGroup, R.layout.item_person) {
        private val tv_title = itemView.find<TextView>(R.id.news_title)
        private val tv_excerpt = itemView.find<TextView>(R.id.news_excerpt)
        private val tv_icon = itemView.find<ImageView>(R.id.news_face)
        override fun setData(data: NewsListEntity?) {
            tv_title.text = data?.title
            tv_excerpt.text = data?.excerpt
            GlideApp.with(context).load(data?.iconUrl).placeholder(R.drawable.text_main1).to(tv_icon)
        }
    }
}

class VideoAdapter(context: Context?) : RecyclerArrayAdapter<VideoEntity>(context) {
    override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
        return VideoHloder(parent)
    }

    inner class VideoHloder(viewGroup: ViewGroup?) : BaseViewHolder<VideoEntity>(viewGroup, R.layout.item_video) {
        private val tv_title = itemView.find<TextView>(R.id.video_title)
        private val iv_cover = itemView.find<ImageView>(R.id.video_cover)

        override fun setData(data: VideoEntity?) {
            tv_title.text = data?.title
            GlideApp.with(context).load(data?.cover).placeholder(R.drawable.text_main1).to(iv_cover)
        }
    }

}

data class VideoEntity(val videoId: Int, val title: String = "", val cover: String = "", val src: String = "#")

data class NewsListEntity(val newID: Int, val title: String, val iconUrl: String = "", val publishDate: String, val excerpt: String = "")
