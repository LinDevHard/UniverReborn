package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.adapter.AttendanceAdapter
import com.lindevhard.android.univerrebornlite.ui.adapter.SessionAdapter
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.AttendanceViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appbar_schedule.*
import kotlinx.android.synthetic.main.appbar_simple.toolbar_title
import kotlinx.android.synthetic.main.fragment_attendance.*
import javax.inject.Inject

class AttendanceFragment : DaggerFragment(), RecyclerView.RecyclerListener {
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        holder.itemId
    }

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
        toolbar_title.text = "Журнал"
        recycleView.layoutManager = LinearLayoutManager(this.context)
        viewModel.subjects.observe(this, Observer { item ->
            swipe_refresher.isRefreshing = false
            recycleView.adapter = AttendanceAdapter(item.subjectList)
        })

        viewModel.session.observe(this, Observer {item ->
            Log.d("AttendanceFragment", item.toString())
            session_indicator.adapter = SessionAdapter(item) {
                viewModel.loadSession(it.currentYear, it.currentSemester)
            }
        })

        swipe_refresher.setOnRefreshListener {
            viewModel.getActualAttendance()
        }

    }

}
