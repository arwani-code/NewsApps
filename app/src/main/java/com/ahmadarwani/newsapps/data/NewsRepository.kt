package com.ahmadarwani.newsapps.data

import com.ahmadarwani.newsapps.data.network.response.everything.Everything
import com.ahmadarwani.newsapps.data.network.response.topheadlines.TopHeadlines
import com.ahmadarwani.newsapps.data.network.retrofit.NewsApiService
import com.ahmadarwani.newsapps.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: NewsApiService
) {

    fun getEverything(domains: String): Flow<Everything> = flow {
        val result = apiService.getEverything(domains = domains, apiKey = Constant.API_KEY)
        emit(result)
    }

    fun getTopHeadlines(country: String): Flow<TopHeadlines> = flow {
        val result = apiService.getTopHeadlines(country = country, apiKey = Constant.API_KEY)
        emit(result)
    }
}