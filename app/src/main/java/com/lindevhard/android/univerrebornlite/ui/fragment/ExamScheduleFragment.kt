package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.adapter.ExamListAdapter
import com.lindevhard.android.univerrebornlite.ui.adapter.SessionAdapter
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.ExamScheduleViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appbar_simple.toolbar_title
import kotlinx.android.synthetic.main.appbar_with_session.*
import kotlinx.android.synthetic.main.fragment_exam_schedule.*
import javax.inject.Inject

class ExamScheduleFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ExamScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exam_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

        toolbar_title.text = "Расписание экзаменов"
        session_indicator.addItemDecoration(DividerItemDecoration(session_indicator.context,
                DividerItemDecoration.HORIZONTAL))

        recycleView.layoutManager = LinearLayoutManager(this.context)
        viewModel.exams.observe(this, Observer { item ->
            recycleView.adapter = ExamListAdapter(item.examList)
            swipe_refresher.isRefreshing = false
        })

        swipe_refresher.setOnRefreshListener {
            viewModel.loadExams()
        }

        viewModel.session.observe(this, Observer { item ->
            session_indicator.adapter = SessionAdapter(item) {
                swipe_refresher.isRefreshing = true
                viewModel.loadExamsBySemester(it.currentYear, it.currentSemester)
            }
        })
    }

}