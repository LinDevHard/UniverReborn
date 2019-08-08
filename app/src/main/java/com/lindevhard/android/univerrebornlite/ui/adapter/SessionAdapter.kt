package com.lindevhard.android.univerrebornlite.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.data.model.Session
import com.lindevhard.android.univerrebornlite.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_session_indicator.view.*


class SessionAdapter(private val items: List<Session>) : RecyclerView.Adapter<SessionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = parent.inflate(R.layout.item_session_indicator, false)
        return ViewHolder(inflateView)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var checkedTextView: CheckedTextView = containerView.title

        init {
            checkedTextView.setOnClickListener{ checkedTextView.isChecked = true}
        }
        fun bind(session: Session){
            if (session.isCurrent) checkedTextView.isChecked = true
            checkedTextView.text = "${session.currentNumber} сессия"
        }


    }
}