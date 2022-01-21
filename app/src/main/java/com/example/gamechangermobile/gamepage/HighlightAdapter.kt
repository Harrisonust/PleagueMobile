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
    lateinit var mediaController:MediaController;
    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val videoView: VideoView = itemview.findViewById(R.id.highlight_video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.highlight_item, parent, false)
        mediaController = MediaController(parent.context)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighlightAdapter.ViewHolder, position: Int) {
        val highlight = highlightList[position]
        mediaController.setAnchorView(holder.videoView)
        holder.videoView.setMediaController(mediaController)
        holder.videoView.setVideoURI(highlight.uri)
        holder.videoView.start()
    }

    override fun getItemCount(): Int = highlightList.size
}