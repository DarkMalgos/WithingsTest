package com.example.withingstest.data.domain


data class ImageResult(
    val previewURL: String,
    val previewWidth : Int,
    val previewHeight : Int,
    val largeImageURL : String,
    val fullHDURL : String,
    val imageURL : String,
)