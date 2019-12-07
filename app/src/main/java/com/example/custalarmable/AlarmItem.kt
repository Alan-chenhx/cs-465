package com.example.custalarmable

class AlarmItem :Comparable<AlarmItem>{
    var alarmName: String = "My Alarm"
    var alarmTime: String = "9:00"
    var alarmAmPm: String = "AM"
    var alarmType: String = "sleep"
    var alarmEnable: Boolean = true
    var snooze: Boolean = true
    var increasingVolume: Boolean = true
    var sun = false
    var sat = false
    var fri = false
    var thu = false
    var wed = false
    var tue = false
    var mon = false
    var ringtone = ""
    var finalRingtone = ""
    var repeats = true
    var autodelete = true
    var vibrateOnly = true
    var snoozePeriod = 9
    var numberOfSnooze = 10
    var maxVolume = 0.8
    override fun compareTo(other: AlarmItem): Int {
        if(alarmEnable==true && other.alarmEnable==false){
            return 1
        }else if(alarmEnable==false && other.alarmEnable==true){
            return -1
        }else{
            if(alarmAmPm=="AM" && other.alarmAmPm=="PM" ){
                return -1
            }else if(alarmAmPm=="PM" && other.alarmAmPm=="AM" ){
                return 1
            }else{
                return alarmTime.compareTo(other.alarmTime)
            }
        }
    }

}