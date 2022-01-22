package com.example.gamechangermobile.gamepage

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Highlight

class HighlightAdapter(val highlightList: List<Highlight>):RecyclerView.Adapter<HighlightAdapter.ViewHolder>() {
//    lateinit var mediaController:MediaController;
    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        //val videoView: VideoView = itemview.findViewById(R.id.highlight_video)
        val videoView: WebView = itemview.findViewById(R.id.highlight_video)
        init {
            videoView.settings.javaScriptEnabled = true
            videoView.webChromeClient = WebChromeClient()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.highlight_item, parent, false)
//        mediaController = MediaController(parent.context)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighlightAdapter.ViewHolder, position: Int) {
        val highlight = highlightList[position]
        holder.videoView.loadData("<iframe width=\"100%\" height=\"100%\" src=\"" + highlight.uri + "\" frameborder=\"0\" allowfullscreen></iframe>", "text/html", "utf-8")
    }

    override fun getItemCount(): Int = highlightList.size
}