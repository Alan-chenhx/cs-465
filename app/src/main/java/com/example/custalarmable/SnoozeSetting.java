package com.example.custalarmable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.gson.Gson;

public class SnoozeSetting extends AppCompatActivity implements View.OnClickListener {
    //change snooze period
    Button snoozePeriodPicker;

    //two sliders
    Slider snoozeLimitSlider;
    Slider volumeSlider;

    //increase sound switch
    Switch increaseSound;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooze_setting);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_snooze_setting));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set up dropdown
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.ringtone_arrays, R.layout.dropdown_menu_popup_item);
        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.outlined_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
        String[] ringtone_arrays = getResources().getStringArray(R.array.ringtone_arrays);
        editTextFilledExposedDropdown.setText(ringtone_arrays[0], false);

        //set up sliders
        snoozeLimitSlider = findViewById(R.id.snoozeLimit);
        snoozeLimitSlider.setLabelFormatter(new NumberSnoozeLabelFormatter());
        volumeSlider = findViewById(R.id.volumeOfFirstRing);
        volumeSlider.setEnabled(false);
        TextView volumeOfFirstRingTitle = (TextView)findViewById(R.id.volumeOfFirstRingTitle);
        volumeOfFirstRingTitle.setTextColor(getResources().getColor(android.R.color.darker_gray));

        //change snooze period
        handleSnoozePeriod();

        //increase sound switch
        handleIncreaseSoundSwitch();

        complete();
    }

    private void handleSnoozePeriod() {
        snoozePeriodPicker = findViewById(R.id.snoozePeriod);
        snoozePeriodPicker.setOnClickListener(this);
    }

    private void handleIncreaseSoundSwitch() {
        increaseSound = findViewById(R.id.increaseSoundSwitch);
        increaseSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView volumeOfFirstRingTitle = (TextView)findViewById(R.id.volumeOfFirstRingTitle);
                if (!isChecked) {
                    volumeSlider.setEnabled(false);
                    volumeOfFirstRingTitle.setTextColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    volumeSlider.setEnabled(true);
                    volumeOfFirstRingTitle.setTextColor(getResources().getColor(R.color.text_color));
                }
            }
        });
    }

    private void complete(){
        findViewById(R.id.addItemBtn).setOnClickListener(new CompoundButton.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.addItemBtn ) {
                    Intent data = new Intent();
                    setResult(Activity.RESULT_OK, data);
                    AlarmItem alarm = new AlarmItem();
                    alarm.setSnoozePeriod(Integer.parseInt(snoozePeriodPicker.getText().toString()));
                    alarm.setNumberOfSnooze( Math.round(snoozeLimitSlider.getValue()) );
                    alarm.setMinVolume( Math.round(volumeSlider.getValue()) );
                    alarm.setIncreasingVolume( increaseSound.isChecked() );
                    String json = gson.toJson(alarm);
                    data.putExtra("alarm", json);
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        snoozePeriodPickerDialog();
    }

    private void snoozePeriodPickerDialog() {
        NumberPicker myNumberPicker = new NumberPicker(this);
        myNumberPicker.setMaxValue(30);
        myNumberPicker.setMinValue(1);
        myNumberPicker.setValue(Integer.parseInt(snoozePeriodPicker.getText().toString()));
        NumberPicker.OnValueChangeListener myValChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                snoozePeriodPicker.setText("" + newVal);
            }
        };
        myNumberPicker.setOnValueChangedListener(myValChangedListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(myNumberPicker);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    public static final class NumberSnoozeLabelFormatter implements Slider.LabelFormatter {
        @Override
        public String getFormattedValue(float value) {
            if (value >= 20) {
                return "∞";
            } else if (value >= 15) {
                return "15";
            } else if (value >= 10) {
                return "10";
            } else if (value >= 5) {
                return "5";
            } else {
                return "1";
            }
        }
    }
}
