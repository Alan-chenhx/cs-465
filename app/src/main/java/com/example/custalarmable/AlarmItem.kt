package com.example.custalarmable

class AlarmItem :Comparable<AlarmItem>{
    var alarmName: String = "My Alarm"
    var alarmTime: String = "9:00"
    var alarmAmPm: String = "AM"
    var alarmType: String = "sleep"
    var alarmEnable: Boolean = true
    var snooze: Boolean = false
    var increasingVolume: Boolean = false
    var sun = false
    var sat = false
    var fri = false
    var thu = false
    var wed = false
    var tue = false
    var mon = false
    var ringtone = ""
    var finalRingtone = ""
    var repeats = false
    var autodelete = false
    var vibrateOnly = false
    var snoozePeriod = 9
    var numberOfSnooze = 10
    var minVolume = 0.8
    override fun compareTo(other: AlarmItem): Int {
        return if (alarmEnable && !other.alarmEnable){
            -1
        }else if(alarmEnable==false && other.alarmEnable==true){
            1
        }else{
            if(alarmAmPm=="AM" && other.alarmAmPm=="PM" ){
                -1
            }else if(alarmAmPm=="PM" && other.alarmAmPm=="AM" ){
                1
            }else{
                alarmTime.compareTo(other.alarmTime)
            }
        }
    }

}