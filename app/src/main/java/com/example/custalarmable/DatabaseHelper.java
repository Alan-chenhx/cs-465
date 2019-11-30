package com.example.custalarmable;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "alarmSetting.db";
    public static final String TABLE_NAME = "alarmSetting_data";
    public static final String COL1 = "ID";

    //alarm setting page
    public static final String COL2 = "alarm_title";
    public static final String COL3 = "time";
    public static final String COL4 = "repeats";
    public static final String COL5 = "sun";
    public static final String COL6 = "mon";
    public static final String COL7 = "tue";
    public static final String COL8 = "wed";
    public static final String COL9 = "thur";
    public static final String COL10 = "fri";
    public static final String COL11 = "sat";

    public static final String COL12 = "work";
    public static final String COL13 = "sleep";

    public static final String COL14 = "auto_delete";
    public static final String COL15 = "vibrate_only";
    public static final String COL16 = "snooze";

    //snooze setting page
    public static final String COL17 = "snooze_period";
    public static final String COL18 = "snooze_limit";
    public static final String COL19 = "final_snooze_ringtone";
    public static final String COL20 = "increased_sound";
    public static final String COL21 = "volume_of_first_ring";



    public DatabaseHelper(Context context) {super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE" + TABLE_NAME + "(ID INTEGER KEY AUTOINCREMENT, " + " alarm_title TEXT, " + "time TEXT" +
                "repeats INTEGER DEFAULT 0" + "sun INTEGER DEFAULT 0" + "mon INTEGER DEFAULT 0" + "tue INTEGER DEFAULT 0" + "wed INTEGER DEFAULT 0" +
                "thur INTEGER DEFAULT 0" + "fri INTEGER DEFAULT 0" + "sat INTEGER DEFAULT 0" + "sun INTEGER DEFAULT 0" +
                "work INTEGER DEFAULT 1" + "sleep INTEGER DEFAULT 0" +
                "auto_delete INTEGER DEFAULT 0" + "vibrate_only INTEGER DEFAULT 0" + "snooze INTEGER DEFAULT 0" +
                "snooze_period TEXT" + "snooze_limit TEXT" + "final_snooze_ringtone TEXT" + "increased_sound INTEGER DEFAULT 0" +
                "volume_of_first_ring TEXT)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addData(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, alarm.alarm_title);
        cv.put(COL3, alarm.time);
        cv.put(COL4, alarm.repeats);
        cv.put(COL5, alarm.sun);
        cv.put(COL6, alarm.mon);
        cv.put(COL7, alarm.tue);
        cv.put(COL8, alarm.wed);
        cv.put(COL9, alarm.thur);
        cv.put(COL10, alarm.fri);
        cv.put(COL11, alarm.sat);
        cv.put(COL12, alarm.work);
        cv.put(COL13, alarm.sleep);
        cv.put(COL14, alarm.auto_delete);
        cv.put(COL15, alarm.vibrate_only);
        cv.put(COL16, alarm.snooze);
        cv.put(COL17, alarm.snooze_period);
        cv.put(COL18, alarm.snooze_limit);
        cv.put(COL19, alarm.final_snooze_ringtone);
        cv.put(COL20, alarm.increased_sound);
        cv.put(COL21, alarm.volume_of_first_ring);
    }
}
