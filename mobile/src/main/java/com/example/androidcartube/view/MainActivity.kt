package com.example.androidcartube.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcartube.OnVideoClickListener
import com.example.androidcartube.VideoListAdapter
import com.example.androidcartube.databinding.ActivityMainBinding
import com.example.androidcartube.repo.YouTubeResponse
import com.example.androidcartube.shared.YouTubeWatch
import com.example.androidcartube.viewmodel.YouTubeViewModel

class MainActivity : AppCompatActivity(), OnVideoClickListener {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: YouTubeViewModel by viewModels<YouTubeViewModel>()
    private val recyclerView by lazy { binding.videoList }
    private lateinit var youtubeResponse: YouTubeResponse
    private var makeRequest = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        initObservers()

        viewModel.fetchVideos()
    }

    private fun initView() {
        binding.btnWatch.setOnClickListener {
            binding.etVideoId.text?.toString()?.let {
                startActivity(YouTubeWatch.newInstance(this, it))
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initObservers() {
        if (!makeRequest)
            return
        viewModel.videos.observe(this) { response ->
            if (response != null) {
                makeRequest = false
                youtubeResponse = response
                setupRecyclerView()
            } else {
                Log.v("ERRO VIDEO", "...")
            }
        }
    }

    private fun setupRecyclerView() {
        val adapter = VideoListAdapter(youtubeResponse.items, this@MainActivity)
        recyclerView.adapter = adapter
    }

    override fun onVideoClick(videoId: String) {
        startActivity(YouTubeWatch.newInstance(this, videoId))
    }
}