package com.lindevhard.android.univerrebornlite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.NewsList
import com.lindevhard.android.univerrebornlite.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {
    private var newsData = MutableLiveData<NewsList>()
    val news: LiveData<NewsList> = newsData

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            runCatching {
                repo.getNews()
            }.onSuccess {
                newsData.value = it.data
            }
        }
    }

}
