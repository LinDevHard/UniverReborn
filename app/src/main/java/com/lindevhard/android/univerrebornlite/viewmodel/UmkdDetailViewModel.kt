package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.UmkdFiles
import com.lindevhard.android.univerrebornlite.repository.UkmdRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UmkdDetailViewModel @Inject constructor(private val repo: UkmdRepository) : ViewModel() {
    private val _umkd = MutableLiveData<List<UmkdFiles>>()
    val umkd: LiveData<List<UmkdFiles>> = _umkd

    fun findFilesById(fileId: Long) {
        viewModelScope.launch {
            runCatching {
                repo.getActualUmkdFiles(fileId)
            }.onSuccess {
                _umkd.value = it.data.umkdList
            }.onFailure {
                Log.d("UMKD DETAIL ", it.message)
            }
        }
    }

    fun getUmkdFile(fileName: String, itemId: Long, subjectId: Long) {
        viewModelScope.launch {
            runCatching {
                repo.getUmkdFile(fileName, itemId, subjectId)
            }.onSuccess {
                Log.d("Download", "OK")
            }.onFailure {
                Log.d("UMKD DETAIL ", it.message)
            }
        }
    }
}
