package com.ahmadarwani.newsapps.data.network.retrofit

import com.ahmadarwani.newsapps.data.network.response.everything.Everything
import com.ahmadarwani.newsapps.data.network.response.topheadlines.TopHeadlines
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getEverything(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String
    ): Everything

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): TopHeadlines
}