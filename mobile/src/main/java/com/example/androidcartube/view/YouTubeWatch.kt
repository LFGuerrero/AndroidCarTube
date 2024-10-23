package com.example.androidcartube.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcartube.databinding.ActivityWatchBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class YouTubeWatch : AppCompatActivity() {
    private lateinit var binding: ActivityWatchBinding
    private lateinit var youTubePlayerView: YouTubePlayerView

    private val videoId by lazy { this.intent.getStringExtra("videoId") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView() {
        youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId!!, 0f)
            }
        })

        binding.backbutton.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun newInstance(context: Context, videoId: String): Intent {
            val intent = Intent(context, YouTubeWatch::class.java)
            intent.putExtra("videoId", videoId)
            return intent
        }
    }
}