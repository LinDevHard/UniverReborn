package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.AttendanceList
import com.lindevhard.android.univerrebornlite.data.model.Session
import com.lindevhard.android.univerrebornlite.repository.AttendanceRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AttendanceViewModel @Inject constructor(private val repo: AttendanceRepository) : ViewModel() {
    private val attendanceData = MutableLiveData<AttendanceList>()
    val subjects: LiveData<AttendanceList> = attendanceData
    private val _session = MutableLiveData<List<Session>>()
    val session: LiveData<List<Session>> = _session

    init {
        getActualAttendance()
        getActualSemesterInfo()
    }

    private fun getActualSemesterInfo() {
        viewModelScope.launch {
            runCatching {
                repo.getActualSessionList()
            }.onSuccess {
                _session.value = it
            }.onFailure {
                Log.d("AttendanceViewModel", it.message)
            }
        }
    }

    fun getActualAttendance() {
        viewModelScope.launch {
            runCatching {
                repo.getAttendance()
            }.onSuccess {
                attendanceData.value = it.data
            }.onFailure {
                Log.d("AttendanceViewModel", it.message)
            }
        }
    }
}
