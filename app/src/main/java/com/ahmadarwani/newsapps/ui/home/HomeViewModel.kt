package com.ahmadarwani.newsapps.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadarwani.newsapps.data.NewsRepository
import com.ahmadarwani.newsapps.data.network.response.everything.Everything
import com.ahmadarwani.newsapps.data.network.response.topheadlines.TopHeadlines
import com.ahmadarwani.newsapps.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState<out T : Any?> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _uiStateTopHeadline: MutableStateFlow<UiState<TopHeadlines>> =
        MutableStateFlow(UiState.Loading)
    val uiStateTopHeadline: StateFlow<UiState<TopHeadlines>> get() = _uiStateTopHeadline

    private val _uiStateEverything: MutableStateFlow<UiState<Everything>> =
        MutableStateFlow(UiState.Loading)
    val uiStateEverything: StateFlow<UiState<Everything>> get() = _uiStateEverything

    fun getEverything() {
        viewModelScope.launch {
            repository.getEverything(domains = Constant.DOMAINS)
                .catch {
                    _uiStateEverything.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiStateEverything.value = UiState.Success(it)
                }
        }
    }

    fun getTopHeadline() {
        viewModelScope.launch {
            repository.getTopHeadlines(country = Constant.COUNTRY)
                .catch {
                    _uiStateTopHeadline.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiStateTopHeadline.value = UiState.Success(it)
                }
        }
    }
}
