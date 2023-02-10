package com.ahmadarwani.newsapps.data.network.response.topheadlines


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
)