package com.lindevhard.android.univerrebornlite.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.api.UmkdFiles
import com.lindevhard.android.univerrebornlite.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_umkd_files.view.*


class UmkdFilesAdapter(var items: List<UmkdFiles>, val listener: (UmkdFiles) -> Unit) :
        RecyclerView.Adapter<UmkdFilesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = parent.inflate(R.layout.item_umkd_files, false)
        return ViewHolder(inflateView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun bind(ukmd: UmkdFiles, listener: (UmkdFiles) -> Unit) {
            containerView.type.text = ukmd.type
            containerView.title.text = ukmd.fname
        }
    }
}