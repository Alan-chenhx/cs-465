package com.example.custalarmable

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.reflect.Type

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var listAdapter: ListAdapter
    private lateinit var alarmListPreferences: SharedPreferences
    private val gson = Gson()
    val alarmListType: Type? = object : TypeToken<ArrayList<AlarmItem>>() {}.type

    fun getAlarmList() : ArrayList<AlarmItem> {
        val json = alarmListPreferences.getString("AlarmList", "[]")
        val alarmList: ArrayList<AlarmItem> = gson.fromJson(json, alarmListType)
        return alarmList
    }

    fun saveAlarmList() {
        val json = gson.toJson(listAdapter.getList())
        alarmListPreferences.edit().putString("AlarmList", json).apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        alarmListPreferences = getSharedPreferences("AlarmSettings", Context.MODE_PRIVATE)

        listAdapter = ListAdapter(getAlarmList())

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ListAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                saveAlarmList()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            this.night_mode.setImageResource(R.drawable.ic_brightness_7_black_24dp)
        } else {
            this.night_mode.setImageResource(R.drawable.ic_brightness_2_black_24dp)
        }

        addItemBtn.setOnClickListener(this)
        jump.setOnClickListener(this)
        night_mode.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addItemBtn -> {
                var intent = Intent(this, AlarmSetting::class.java)
                startActivity(intent)
                var testAlarm = AlarmItem()
                testAlarm.alarmEnable = true
                testAlarm.alarmType = "work"
                testAlarm.alarmName = "CS 465"
                listAdapter.addItem(testAlarm)
                saveAlarmList()
            }
            R.id.jump -> {
                var intent = Intent(this, Sleep::class.java)
                startActivity(intent)
            }
            R.id.night_mode -> {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.statistics -> {
                val intent = Intent(this, Analysis::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
