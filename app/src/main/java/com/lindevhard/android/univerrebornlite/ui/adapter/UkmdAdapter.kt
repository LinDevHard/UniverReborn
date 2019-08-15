package com.lindevhard.android.univerrebornlite.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.api.UkmdData
import com.lindevhard.android.univerrebornlite.ui.fragment.UmkdFragmentDirections
import com.lindevhard.android.univerrebornlite.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.view.mainContainer
import kotlinx.android.synthetic.main.item_news.view.title
import kotlinx.android.synthetic.main.item_umkd_subject.view.*

class UkmdAdapter(var items: List<UkmdData>) :
        RecyclerView.Adapter<UkmdAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = parent.inflate(R.layout.item_umkd_subject, false)
        return ViewHolder(inflateView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var ukmdId: Long = 0
        private var title: String = "UMKD"

        init {
            containerView.mainContainer.setOnClickListener {
                val action = UmkdFragmentDirections.actionUkmdFragmentToUmkdDetailFragment(ukmdId, title)
                it.findNavController().navigate(action)
            }
        }


        fun bind(ukmd: UkmdData) {
            containerView.type.text = ukmd.type
            containerView.title.text = ukmd.sName
            ukmdId = ukmd.ukmdId
            title = ukmd.sName
        }
    }
}