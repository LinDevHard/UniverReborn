package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {
    private var newsData = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = newsData

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            runCatching {
                repo.getNews()
            }.onSuccess {
                newsData.value = it
            }.onFailure {
                Log.d("NewsViewModel", it.toString())
            }
        }
    }

}
