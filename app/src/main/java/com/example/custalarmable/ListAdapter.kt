package com.example.custalarmable

import android.R.*
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
            alarmToggle.isChecked = alarmItem.alarmEnable
            if (!alarmItem.alarmEnable) {
                rowCard.elevation = 0f
                rowCard.setCardBackgroundColor(resources.getColor(color.transparent))
                alarmName.setTextColor(resources.getColor(color.darker_gray))
                alarmTime.setTextColor(resources.getColor(color.darker_gray))
                alarmAmPm.setTextColor(resources.getColor(color.darker_gray))

                sunday.setTextColor(resources.getColor(color.darker_gray))
                monday.setTextColor(resources.getColor(color.darker_gray))
                tuesday.setTextColor(resources.getColor(color.darker_gray))
                wednesday.setTextColor(resources.getColor(color.darker_gray))
                thursday.setTextColor(resources.getColor(color.darker_gray))
                friday.setTextColor(resources.getColor(color.darker_gray))
                saturday.setTextColor(resources.getColor(color.darker_gray))

                listSnoozeIcon.setColorFilter(resources.getColor(color.darker_gray))
                listIncreasingSoundIcon.setColorFilter(resources.getColor(color.darker_gray))
            }
        }
    }
}
