package com.lindevhard.android.univerrebornlite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.ExamSchedule
import com.lindevhard.android.univerrebornlite.repository.ExamScheduleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExamScheduleViewModel @Inject constructor(private val repo: ExamScheduleRepository) : ViewModel() {
    private val examSchedule = MutableLiveData<ExamSchedule>()
    val exams: LiveData<ExamSchedule> = examSchedule

    init {
        loadExams()
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
}
