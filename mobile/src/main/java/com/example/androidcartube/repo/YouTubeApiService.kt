package com.example.androidcartube.repo

import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {
    @GET("youtube/v3/videos")
    suspend fun getMostPopularVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "BR",
        @Query("maxResults") maxResults: String = "5",
        @Query("key") apiKey: String = "AIzaSyCVgE3Rc7HIJJC51LTm3wMMVJeus2gM5vM"
    ): YouTubeResponse
}