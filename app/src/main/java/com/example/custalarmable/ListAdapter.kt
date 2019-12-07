package com.example.custalarmable

import android.R.color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*
import kotlin.reflect.KFunction0


class ListAdapter(private val items: ArrayList<AlarmItem>, private val kFunction0: KFunction0<Unit>) : RecyclerView.Adapter<ListAdapter.VH>() {
    fun getList(): ArrayList<AlarmItem> {
        return items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
        holder.toggle.setOnClickListener {
            items[position].alarmEnable = !items[position].alarmEnable
            sort()
        }
        holder.card.setOnClickListener {
            removeAt(position)
            kFunction0()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(alarmItem: AlarmItem) {
        var pos = 0
        for (i in 0 until items.size){
            if (items[i] > alarmItem) {
                pos = i
                break
            }
        }
        if (pos == items.size) {
            items.add(alarmItem)
            notifyItemInserted(pos)
        } else {
            items.add(pos, alarmItem)
            notifyItemMoved(items.size, pos)
        }

    }

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun sort() {
        items.sort()
        notifyDataSetChanged()
    }

    class VH(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)) {

        lateinit var toggle : Switch
        lateinit var card : CardView

        fun bind(alarmItem: AlarmItem) = with(itemView) {
            alarmName.text = alarmItem.alarmName
            alarmTime.text = alarmItem.alarmTime
            alarmAmPm.text = alarmItem.alarmAmPm
            alarmToggle.isChecked = alarmItem.alarmEnable
            toggle = alarmToggle
            card = rowCard

            if(!alarmItem.snooze){
                listSnoozeIcon.setColorFilter(resources.getColor(color.darker_gray))
            }else{
                listSnoozeIcon.setColorFilter(resources.getColor(R.color.colorPrimary))
            }

            if(!alarmItem.increasingVolume){
                listIncreasingSoundIcon.setColorFilter(resources.getColor(color.darker_gray))
            }else{
                listIncreasingSoundIcon.setColorFilter(resources.getColor(R.color.colorPrimary))
            }

            if(!alarmItem.fri){
                friday.setTextColor(resources.getColor(color.darker_gray))
            }else{
                friday.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            if(!alarmItem.sun){
                sunday.setTextColor(resources.getColor(color.darker_gray))
            }else{
                sunday.setTextColor(resources.getColor(R.color.colorPrimary))
            }

            if(!alarmItem.sat){
                saturday.setTextColor(resources.getColor(color.darker_gray))
            }else{
                saturday.setTextColor(resources.getColor(R.color.colorPrimary))
            }

            if(!alarmItem.mon){
                monday.setTextColor(resources.getColor(color.darker_gray))
            }else{
                monday.setTextColor(resources.getColor(R.color.colorPrimary))
            }

            if(!alarmItem.tue){
                tuesday.setTextColor(resources.getColor(color.darker_gray))
            }else{
                tuesday.setTextColor(resources.getColor(R.color.colorPrimary))
            }

            if(!alarmItem.wed){
                wednesday.setTextColor(resources.getColor(color.darker_gray))
            }else{
                wednesday.setTextColor(resources.getColor(R.color.colorPrimary))
            }


            if(!alarmItem.thu){
                thursday.setTextColor(resources.getColor(color.darker_gray))
            }else{
                thursday.setTextColor(resources.getColor(R.color.colorPrimary))
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
            } else{
                if(!alarmItem.fri){
                    friday.setTextColor(resources.getColor(color.darker_gray))
                }else{
                    friday.setTextColor(resources.getColor(R.color.colorPrimary))
                }
                if(!alarmItem.sun){
                    sunday.setTextColor(resources.getColor(color.darker_gray))
                }else{
                    sunday.setTextColor(resources.getColor(R.color.colorPrimary))
                }

                if(!alarmItem.sat){
                    saturday.setTextColor(resources.getColor(color.darker_gray))
                }else{
                    saturday.setTextColor(resources.getColor(R.color.colorPrimary))
                }

                if(!alarmItem.mon){
                    monday.setTextColor(resources.getColor(color.darker_gray))
                }else{
                    monday.setTextColor(resources.getColor(R.color.colorPrimary))
                }

                if(!alarmItem.tue){
                    tuesday.setTextColor(resources.getColor(color.darker_gray))
                }else{
                    tuesday.setTextColor(resources.getColor(R.color.colorPrimary))
                }

                if(!alarmItem.wed){
                    wednesday.setTextColor(resources.getColor(color.darker_gray))
                }else{
                    wednesday.setTextColor(resources.getColor(R.color.colorPrimary))
                }


                if(!alarmItem.thu){
                    thursday.setTextColor(resources.getColor(color.darker_gray))
                }else{
                    thursday.setTextColor(resources.getColor(R.color.colorPrimary))
                }
            }
        }
    }
}
