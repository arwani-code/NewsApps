package com.ahmadarwani.newsapps.data.network.response.topheadlines

import com.google.gson.annotations.SerializedName

data class TopHeadlines(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)