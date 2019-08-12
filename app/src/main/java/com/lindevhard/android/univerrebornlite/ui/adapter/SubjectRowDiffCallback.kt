package com.lindevhard.android.univerrebornlite.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lindevhard.android.univerrebornlite.api.Attendance

class SubjectRowDiffCallback(private val newRows : List<Attendance>,
                             private val oldRows : List<Attendance>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldRows[oldItemPosition]
        val newItem = newRows[newItemPosition]
        return oldItem.subjectName == newItem.subjectName &&
                oldItem.period == newItem.period
    }

    override fun getOldListSize(): Int =  oldRows.size

    override fun getNewListSize(): Int = newRows.size
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRow = oldRows[oldItemPosition]
        val newRow = newRows[newItemPosition]
        return oldRow == newRow
    }
}