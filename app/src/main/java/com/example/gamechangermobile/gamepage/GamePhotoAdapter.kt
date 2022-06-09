package com.example.gamechangermobile.gamepage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import java.util.concurrent.Executors


class GamePhotoAdapter(private val photoList: ArrayList<String>) :
    RecyclerView.Adapter<GamePhotoAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val imageview: ImageView = itemview.findViewById(R.id.game_photo_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.game_photo_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            Toast.makeText(view.context, "dummy", Toast.LENGTH_SHORT).show()
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap?
        executor.execute {
            val imageURL = photoList[position]
            try {
                val inputStream = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(inputStream)
                handler.post {
                    holder.imageview.setImageBitmap(image)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int = photoList.size

}