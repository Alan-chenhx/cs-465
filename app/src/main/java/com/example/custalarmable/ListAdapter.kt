package com.example.custalarmable

import android.R.color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*


class ListAdapter(private val items: ArrayList<AlarmItem>) : RecyclerView.Adapter<ListAdapter.VH>() {
    fun getList(): ArrayList<AlarmItem> {
        return items
    }

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
    fun enableAt(position: Int) {
        items[position].alarmEnable = !items[position].alarmEnable
        notifyItemChanged(position)
    }

    class VH(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)) {

        fun bind(alarmItem: AlarmItem) = with(itemView) {
            alarmName.text = alarmItem.alarmName
            alarmTime.text = alarmItem.alarmTime
            alarmAmPm.text = alarmItem.alarmAmPm
            alarmToggle.isChecked = alarmItem.alarmEnable


            if (alarmItem.alarmType == "sleep") {
                listTypeBar.background = resources.getDrawable(R.drawable.rect_tag_sleep)
            } else {
                listTypeBar.background = resources.getDrawable(R.drawable.rect_tag_work)
            }
            if(!alarmItem.snooze){
                listSnoozeIcon.setBackgroundColor(resources.getColor(color.transparent))
            }
            if(!alarmItem.snooze){
                listSnoozeIcon.setColorFilter(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.increasingVolume){
                listIncreasingSoundIcon.setColorFilter(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.fri){
                friday.setTextColor(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.sun){
                sunday.setTextColor(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.sat){
                saturday.setTextColor(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.mon){
                monday.setTextColor(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.tue){
                tuesday.setTextColor(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.wed){
                wednesday.setTextColor(resources.getColor(color.darker_gray))
            }
            if(!alarmItem.thu){
                thursday.setTextColor(resources.getColor(color.darker_gray))
            }

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
