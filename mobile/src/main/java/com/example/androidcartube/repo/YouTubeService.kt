package com.example.androidcartube.repo

import com.example.androidcartube.base.AssetsHelper.getApiKey
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YouTubeService {

    suspend fun callYoutubeMostPopular(): YouTubeResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://youtube.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val youtubeApiService = retrofit.create(YouTubeApiService::class.java)

        val response = youtubeApiService.getMostPopularVideos(apiKey = getApiKey())

        return response
    }


}