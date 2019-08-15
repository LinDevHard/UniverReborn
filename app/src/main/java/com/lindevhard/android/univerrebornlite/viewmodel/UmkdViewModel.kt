package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.UkmdData
import com.lindevhard.android.univerrebornlite.repository.UkmdRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UmkdViewModel @Inject constructor(private val repo: UkmdRepository) : ViewModel() {
    private val _umkd = MutableLiveData<List<UkmdData>>()
    val umkd: LiveData<List<UkmdData>> = _umkd

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            runCatching {
                repo.getActualUkmdList()
            }.onSuccess {
                Log.d("UKMD", it.toString())
                _umkd.value = it.data.ukmdList
            }.onFailure {
                Log.d("UKMD", it.message)
            }
        }
    }
}
