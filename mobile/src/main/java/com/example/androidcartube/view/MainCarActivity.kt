package com.example.androidcartube.view

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.lifecycle.lifecycleScope
import com.example.androidcartube.repo.YouTubeResponse
import com.example.androidcartube.repo.YouTubeService
import kotlinx.coroutines.launch

class MainCarActivity(carContext: CarContext) : Screen(carContext) {

    private val youtubeService: YouTubeService = YouTubeService()
    private lateinit var youtubeResponse: YouTubeResponse


    override fun onGetTemplate(): Template {

        lifecycleScope.launch {
            try {
                val response = youtubeService.callYoutubeMostPopular()
                youtubeResponse = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val itemListBuilder = ItemList.Builder()

        for (video in youtubeResponse.items) {
            itemListBuilder.addItem(
                Row.Builder()
                    .setTitle(video.snippet.title)
                    .addAction(Action.Builder().setTitle("Play").setOnClickListener {
                        val videoId = video.id
                    }.build())
                    .build()
            )
        }

        return ListTemplate.Builder().apply {
            setTitle("Video List")
            setHeaderAction(Action.BACK)
            setSingleList(itemListBuilder.build())
        }.build()
    }

}