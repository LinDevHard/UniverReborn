package com.lindevhard.android.univerrebornlite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {
    private val newsData = MutableLiveData<News>()
    val news: LiveData<News> = newsData

    fun loadNewsById(id: Int) {
        viewModelScope.launch {
            runCatching {
                repo.getNewsById(id)
            }.onSuccess {
                if (it != null)
                    newsData.value = it
            }
        }
    }
}