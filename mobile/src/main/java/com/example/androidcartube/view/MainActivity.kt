package com.example.androidcartube.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcartube.databinding.ActivityMainBinding
import com.example.androidcartube.viewmodel.YouTubeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: YouTubeViewModel by viewModels<YouTubeViewModel>()
    private val recyclerView by lazy { binding.videoList }

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
    }

    private fun initObservers() {
        viewModel.videos.observe(this) { response ->
            if (response != null) {
                Log.v("VIDEO", response.toString())
                // Update UI with video data
            } else {
                Log.v("ERRO VIDEO", "...")
            }
        }
    }
}
//AIzaSyCVgE3Rc7HIJJC51LTm3wMMVJeus2gM5vM