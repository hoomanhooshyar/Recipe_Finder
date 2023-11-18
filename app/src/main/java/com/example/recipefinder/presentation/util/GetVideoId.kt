package com.example.recipefinder.presentation.util

fun getVideoId(videoAddress:String):String{
    val prefix = "https://www.youtube.com/watch?v="
    if(videoAddress.startsWith(prefix)){
        return videoAddress.removePrefix(prefix)
    }
    return ""
}