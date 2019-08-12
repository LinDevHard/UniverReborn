package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.Attendance
import com.lindevhard.android.univerrebornlite.data.model.Session
import com.lindevhard.android.univerrebornlite.repository.AttendanceRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AttendanceViewModel @Inject constructor(private val repo: AttendanceRepository) : ViewModel() {

    private val attendanceData = MutableLiveData<List<Attendance>>()
    val subjects: LiveData<List<Attendance>> = attendanceData
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
                attendanceData.value = it
            }.onFailure {
                Log.d("AttendanceViewModel", it.message)
            }
        }
    }

    fun loadSession(currentYear: Int, currentSemester: Int) {
        viewModelScope.launch {
            runCatching {
                Log.d("ViewModel", currentYear.toString() + currentSemester.toString())
                repo.getAttendanceBySemester(currentYear, currentSemester)
            }.onSuccess {
                Log.d("ViewModel", it.toString())
                attendanceData.value = it
            }.onFailure {
                Log.d("AttendanceViewModel", it.message)
            }
        }
    }
}

