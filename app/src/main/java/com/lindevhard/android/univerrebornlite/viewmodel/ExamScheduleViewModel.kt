package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.ExamSchedule
import com.lindevhard.android.univerrebornlite.data.model.Session
import com.lindevhard.android.univerrebornlite.repository.ExamScheduleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExamScheduleViewModel @Inject constructor(private val repo: ExamScheduleRepository) : ViewModel() {
    private val examSchedule = MutableLiveData<ExamSchedule>()
    val exams: LiveData<ExamSchedule> = examSchedule
    private val _session = MutableLiveData<List<Session>>()
    val session: LiveData<List<Session>> = _session

    init {
        getActualSemesterInfo()
        loadExams()
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
    fun loadExams() {
        viewModelScope.launch {
            runCatching {
                repo.getExamSchedule()
            }.onSuccess {
                examSchedule.value = it.data
            }
        }
    }

    fun loadExamsBySemester(year: Int, semester: Int) {
        viewModelScope.launch {
            runCatching {
                repo.getExamSchedule(year, semester)
            }.onSuccess {
                examSchedule.value = it.data
            }
        }
    }
}

