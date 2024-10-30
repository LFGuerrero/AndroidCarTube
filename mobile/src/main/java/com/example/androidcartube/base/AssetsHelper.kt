package com.example.androidcartube.base

import java.io.IOException

object AssetsHelper {
    fun getApiKey(): String {
        return try {
            MyApplication.appContext.assets.open("apikey.txt").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            ""
        }
    }
}