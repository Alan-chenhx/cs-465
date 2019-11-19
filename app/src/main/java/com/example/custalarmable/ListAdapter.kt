package com.example.custalarmable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class ListAdapter(private val items: MutableList<AlarmItem>) : RecyclerView.Adapter<ListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItem(alarmItem: AlarmItem) {
        items.add(alarmItem)
        notifyItemInserted(items.size)
    }

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    class VH(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)) {

        fun bind(alarmItem: AlarmItem) = with(itemView) {
            alarmName.text = alarmItem.alarmName
            alarmTime.text = alarmItem.alarmTime
            alarmAmPm.text = alarmItem.alarmAmPm
        }
    }
}
