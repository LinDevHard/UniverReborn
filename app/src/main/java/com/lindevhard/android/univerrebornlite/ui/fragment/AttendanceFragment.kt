package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.adapter.AttendanceAdapter
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.AttendanceViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_exam_schedule.*
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class AttendanceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AttendanceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        viewModel.subjects.observe(this, Observer { item ->
            swipe_refresher.isRefreshing = false
            recycleView.adapter = AttendanceAdapter(item.subjectList)
        })

        swipe_refresher.setOnRefreshListener {
            viewModel.getActualAttendance()
        }
    }
}