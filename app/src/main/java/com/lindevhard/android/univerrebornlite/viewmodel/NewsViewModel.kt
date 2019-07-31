package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            val data = repo.getNews()
            Log.d("NEWS VIEW MODEL", data.toString())
        }
    }

}
