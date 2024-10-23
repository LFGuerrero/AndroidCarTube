package com.example.androidcartube

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcartube.databinding.VideoAdapterBinding
import com.example.androidcartube.repo.Item
import com.example.androidcartube.repo.YouTubeResponse

class VideoListAdapter(private val videoList: List<Item>, private val listener: OnVideoClickListener) :
    RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    class VideoViewHolder(val binding: VideoAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.binding.videoTitle.text = video.snippet.title

        // Carregar a miniatura com Glide (certifique-se de ter a dependência do Glide)
        Glide.with(holder.itemView.context)
            .load(video.snippet.thumbnails.default.url)
            .into(holder.binding.videoThumbnail)

        holder.itemView.setOnClickListener {
            listener.onVideoClick(video.id)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}