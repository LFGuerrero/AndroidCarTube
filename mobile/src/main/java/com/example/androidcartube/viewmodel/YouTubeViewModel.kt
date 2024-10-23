package com.example.androidcartube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcartube.repo.YouTubeResponse
import com.example.androidcartube.repo.YouTubeService
import kotlinx.coroutines.launch

class YouTubeViewModel(private val youtubeService: YouTubeService = YouTubeService()) : ViewModel() {

    private val _videos = MutableLiveData<YouTubeResponse>()
    val videos: LiveData<YouTubeResponse> = _videos

    fun fetchVideos() {
        viewModelScope.launch {
            try {
                val response = youtubeService.callYoutubeMostPopular()
                _videos.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}