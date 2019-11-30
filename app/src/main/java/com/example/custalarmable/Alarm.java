package com.example.custalarmable;

public class Alarm {
    String alarm_title;
    String time;
    int repeats;
    int sun, mon, tue, wed, thur, fri, sat;
    int work, sleep;
    int auto_delete, vibrate_only, snooze;

    String snooze_period, snooze_limit, final_snooze_ringtone, volume_of_first_ring;
    int increased_sound;

    public void set_title(String title) {
        this.alarm_title = title;
    }

    public void set_time(String time) {
        this.time = time;
    }

    public void set_repeats(int repeats) {
        this.repeats = repeats;
    }

    public void set_repeat_day(int sun, int mon, int tue, int wed, int thur, int fri, int sat) {
        this.sun = sun;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thur = thur;
        this.fri = fri;
        this.sat = sat;

    }

    public void set_type(int work, int sleep) {
        this.work = work;
        this.sleep = sleep;
    }


    public void set_auto_delete(int n) {
        this.auto_delete = n;
    }

    public void set_vibrate_only(int n) {
        this.vibrate_only = n;
    }

    public void set_snooze(int n) {
        this.snooze = n;
    }

    public void set_snooze_period(String snooze_period) {
        this.snooze_period = snooze_period;
    }

    public void set_snooze_limit(String snooze_limit) {
        this.snooze_limit = snooze_limit;
    }

    public void set_final_snooze_ringtone(String final_snooze_ringtone) {
        this.final_snooze_ringtone = final_snooze_ringtone;
    }

    public void set_increased_sound(int n) {
        this.increased_sound = n;
    }

    public void set_volume_of_first_ring(String volume_of_first_ring) {
        this.volume_of_first_ring = volume_of_first_ring;
    }
}
