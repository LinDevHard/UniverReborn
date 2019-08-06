package com.lindevhard.android.univerrebornlite.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.api.Exam
import com.lindevhard.android.univerrebornlite.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_exam.view.*

class ExamListAdapter(private val items: List<Exam>) :
        RecyclerView.Adapter<ExamListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = parent.inflate(R.layout.list_exam, false)
        return ViewHolder(inflateView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(exam: Exam) {
            containerView.date.text = exam.date
            containerView.subject.text = exam.subject
            containerView.time.text = exam.time
            containerView.place.text = exam.audience
        }
    }
}