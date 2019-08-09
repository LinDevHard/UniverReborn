package com.lindevhard.android.univerrebornlite.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.lindevhard.android.univerrebornlite.api.Attendance
import com.lindevhard.android.univerrebornlite.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.view.title
import kotlinx.android.synthetic.main.item_subject.view.*


class AttendanceAdapter(private val items: List<Attendance>) : RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = parent.inflate(com.lindevhard.android.univerrebornlite.R.layout.item_subject, false)
        return ViewHolder(inflateView)
    }

    fun getData() = items
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var groupId: String = ""

        init {
            containerView.expand.setOnClickListener { expandOrCollapse() }
            containerView.detail_button.setOnClickListener { increment() }
        }

        private fun increment() {
            containerView.progress_rk1.progress += 1
            containerView.rk1.text = containerView.progress_rk1.progress.toString()
        }

        fun bind(subject: Attendance) {
            containerView.title.text = subject.subjectName
            containerView.period.text = subject.period
            containerView.type.text = subject.type
            groupId = subject.groupId
            containerView.rk1.text = subject.firstRk.toString()
            containerView.rk2.text = subject.secondRk.toString()
            containerView.progress_rk1.progress = subject.firstRk
            containerView.progress_rk2.progress = subject.secondRk
        }

        private fun expandOrCollapse() {
            val detailContainer = containerView.detailContainer
            TransitionManager.beginDelayedTransition(containerView.mainContainer, AutoTransition())
            if (detailContainer.visibility == View.GONE)
                detailContainer.visibility = View.VISIBLE
            else {
                TransitionManager.beginDelayedTransition(containerView.mainContainer, AutoTransition())
                detailContainer.visibility = View.GONE
            }
        }

    }
}
