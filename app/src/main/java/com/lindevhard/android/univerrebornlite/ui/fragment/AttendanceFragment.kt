package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.adapter.AttendanceAdapter
import com.lindevhard.android.univerrebornlite.ui.adapter.SessionAdapter
import com.lindevhard.android.univerrebornlite.ui.adapter.SubjectRowDiffCallback
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.AttendanceViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appbar_with_session.*
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
        recycleView.layoutManager = LinearLayoutManager(this.context)

        swipe_refresher.isRefreshing = true
        val mainAdapter = AttendanceAdapter(listOf())
        recycleView.adapter = mainAdapter
        toolbar_title.setText(R.string.title_attendance)

        session_indicator.addItemDecoration(DividerItemDecoration(session_indicator.context,
                DividerItemDecoration.HORIZONTAL))

        viewModel.subjects.observe(this, Observer { item ->
            val diff = DiffUtil.calculateDiff(SubjectRowDiffCallback(
                    mainAdapter.items, item), false)
            mainAdapter.items = item
            recycleView.recycledViewPool.clear()
            mainAdapter.notifyDataSetChanged()
            diff.dispatchUpdatesTo(mainAdapter)

            swipe_refresher.isRefreshing = false
        })


        viewModel.session.observe(this, Observer {item ->
            session_indicator.adapter = SessionAdapter(item) {
                swipe_refresher.isRefreshing = true
                viewModel.loadSession(it.currentYear, it.currentSemester)
            }
        })

        swipe_refresher.setOnRefreshListener {
            viewModel.getActualAttendance()
        }

    }
}
