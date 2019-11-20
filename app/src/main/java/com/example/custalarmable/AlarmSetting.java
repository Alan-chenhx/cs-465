package com.example.custalarmable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.button.MaterialButton;


public class AlarmSetting extends AppCompatActivity {

    MaterialButton sleep;
    MaterialButton work;
    Switch mySwitch;
    ToggleButton auto_delete;
    int minute;
    TextView time;
    Context mcontext;
    ToggleButton snooze;
    ImageButton snooze_setting;
    private String[] ringtone_arrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_sleep_data));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set up dropdown
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.ringtone_arrays, R.layout.dropdown_menu_popup_item);
        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.outlined_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
        ringtone_arrays = getResources().getStringArray(R.array.ringtone_arrays);
        editTextFilledExposedDropdown.setText(ringtone_arrays[0], false);

        sleep = (MaterialButton) findViewById(R.id.sleep_type);
        work = (MaterialButton) findViewById(R.id.work_type);
        mySwitch = (Switch) findViewById(R.id.switch1);
        time = (TextView) findViewById(R.id.time);
        mcontext = getApplicationContext();
        snooze = (ToggleButton) findViewById(R.id.snooze);
        snooze_setting = (ImageButton) findViewById(R.id.setting);

        snooze_setting_control();
        Toggle_control();
        type_control();
        time_control();

        snooze_jump();

    }

    private void snooze_jump() {
        snooze_setting.setOnClickListener(new CompoundButton.OnClickListener() {
            public void onClick(View v) {

                if (v.getId() == R.id.setting && snooze_setting.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(AlarmSetting.this, SnoozeSetting.class);
                    startActivity(intent);

                }
            }
        });
    }


    private void snooze_setting_control() {
        snooze_setting.setVisibility(View.GONE);
        snooze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    snooze_setting.setVisibility(View.VISIBLE);
                } else {
                    snooze_setting.setVisibility(View.GONE);
                }
            }


        });
    }

    private void time_control() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence Cur = time.getText();
                int Curhour = 0;
                int hour = 0;
                int minutes = 0;
                int[] array = convert(Cur);
                hour = array[0];
                minutes = array[1];


//                System.out.println(hour);
                new TimePickerDialog(AlarmSetting.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int houre = hourOfDay;
                        String ampm = "AM";


                        AlarmSetting.this.minute = minute;
                        if (houre > 12) {
                            houre -= 12;
                            ampm = "PM";
                        } else if (houre == 0) {
                            houre = 12;
                        } else if (houre == 12) {
                            ampm = "PM";
                        }
                        if (AlarmSetting.this.minute < 10) {
                            time.setText(houre + ":" + "0" + AlarmSetting.this.minute + " " + ampm);
                        } else {
                            time.setText(houre + ":" + AlarmSetting.this.minute + " " + ampm);
                        }
                    }
                }, hour, minutes, false).show();

            }
        });
    }

    private void type_control() {
        sleep.setChecked(true);
        sleep.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener() {
            public void onCheckedChanged(MaterialButton button, boolean isChecked) {
                if (isChecked) {
                    work.setChecked(false);
                }
            }
        });
        work.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener() {
            public void onCheckedChanged(MaterialButton button, boolean isChecked) {
                if (isChecked) {
                    sleep.setChecked(false);
                }
            }
        });


    }

    private int[] convert(CharSequence time) {
        int len = time.length();
        String tmp = time.toString();
        String cur = "";
        int hour = 0;
        int minute = 0;
        int i = 0;
        for (; i < len; i++) {
            if (tmp.charAt(i) == ':') {
                hour = Integer.parseInt(cur);
                cur = "";
                break;
            } else {
                cur += tmp.charAt(i);
            }
        }
        i++;
        for (; i < len; i++) {
            if (tmp.charAt(i) == ' ') {
                minute = Integer.parseInt(cur);
                cur = "";
                break;
            } else {
                cur += tmp.charAt(i);
            }
        }
        i++;
        for (; i < len; i++) {
            cur += tmp.charAt(i);
        }
        i++;
        if (cur.equals("PM")) {
            hour += 12;
            if (hour == 24) {
                hour -= 12;
            }
        }
        int[] array = new int[2];
        array[0] = hour;
        array[1] = minute;
        return array;
    }

    private void Toggle_control() {
        final ToggleButton myToggleButton1 = (ToggleButton) findViewById(R.id.buttonSun);
        final ToggleButton myToggleButton2 = (ToggleButton) findViewById(R.id.buttonMon);
        final ToggleButton myToggleButton3 = (ToggleButton) findViewById(R.id.buttonTue);
        final ToggleButton myToggleButton4 = (ToggleButton) findViewById(R.id.buttonWed);
        final ToggleButton myToggleButton5 = (ToggleButton) findViewById(R.id.buttonThur);
        final ToggleButton myToggleButton6 = (ToggleButton) findViewById(R.id.buttonFri);
        final ToggleButton myToggleButton7 = (ToggleButton) findViewById(R.id.buttonSat);
        auto_delete = (ToggleButton) findViewById(R.id.auto_delete);
        myToggleButton1.setEnabled(false);
        myToggleButton2.setEnabled(false);
        myToggleButton3.setEnabled(false);
        myToggleButton4.setEnabled(false);
        myToggleButton5.setEnabled(false);
        myToggleButton6.setEnabled(false);
        myToggleButton7.setEnabled(false);
        auto_delete.setChecked(false);
        auto_delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    mySwitch.setChecked(false);
                }
            }
        });
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked == false) {

                    myToggleButton1.setChecked(false);
                    myToggleButton1.setEnabled(false);
                    myToggleButton2.setChecked(false);
                    myToggleButton2.setEnabled(false);
                    myToggleButton3.setChecked(false);
                    myToggleButton3.setEnabled(false);
                    myToggleButton4.setChecked(false);
                    myToggleButton4.setEnabled(false);
                    myToggleButton5.setChecked(false);
                    myToggleButton5.setEnabled(false);
                    myToggleButton6.setChecked(false);
                    myToggleButton6.setEnabled(false);
                    myToggleButton7.setChecked(false);
                    myToggleButton7.setEnabled(false);
                } else {
                    auto_delete.setChecked(false);
                    myToggleButton1.setEnabled(true);
                    myToggleButton1.setChecked(false);
                    myToggleButton2.setEnabled(true);
                    myToggleButton2.setChecked(false);
                    myToggleButton3.setEnabled(true);
                    myToggleButton3.setChecked(false);
                    myToggleButton4.setEnabled(true);
                    myToggleButton4.setChecked(false);
                    myToggleButton5.setEnabled(true);
                    myToggleButton5.setChecked(false);
                    myToggleButton6.setEnabled(true);
                    myToggleButton6.setChecked(false);
                    myToggleButton7.setEnabled(true);
                    myToggleButton7.setChecked(false);
                }
            }
        });
    }

}

