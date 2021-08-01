package com.example.withingstest.data

import com.example.withingstest.data.domain.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api?key=5511001-7691b591d9508e60ec89b63c4")
    //fun getImage(@ params : Map<String, String>) : Call<List<ImageResult>>
    fun getImage(@Query("q") q: String) : Call<SearchResult>
}