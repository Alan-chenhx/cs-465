package com.example.custalarmable;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

public class SnoozeSetting extends AppCompatActivity implements View.OnClickListener {
    //change snooze period
    Button snoozePeriodPicker;

    //two seek bars
    //private SeekbarWithIntervals snoozeLimits = null;
    //private SeekbarWithIntervals firstRingVolume = null;
    SeekBar snoozeLimits;
    int snoozeMax = 20, snoozeStep = 5;
    int snoozeProgress;

    SeekBar firstRingVolume;
    int ringMax = 100, ringStep = 20;

    //increase sound switch
    Switch increaseSound;

    //spinner of ringtone
    private Spinner finalRingtone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooze_setting);

        //change snooze period
        handleSnoozePeriod();

        //seekbar
        //snooze limit
       // List<String> snoozeLimitsIntervals = getSnoozeIntervals();
        //getSeekbarWithIntervals().setIntervals(snoozeLimitsIntervals);
        handleSnoozeLimit();

        //increasing volume
        handleVolumeOfFirstRing();
        //List<String> firstRingVolumeIntervals = getVolumeIntervals();
        //getSeekbarWithVolumeIntervals().setIntervals(firstRingVolumeIntervals);

        //increase sound switch
        handleIncreaseSoundSwitch();
        //spinner of selecting final ringtone
        addItemOnFinalRingtone();
    }

    private void handleSnoozePeriod() {
        snoozePeriodPicker = (Button) findViewById(R.id.snoozePeriod);
        snoozePeriodPicker.setOnClickListener(this);
    }

    private void handleSnoozeLimit() {
        snoozeLimits = (SeekBar) findViewById(R.id.snoozeLimit);
        snoozeLimits.setMax(snoozeMax / snoozeStep);
        snoozeLimits.setProgress(0);
        snoozeLimits.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                snoozeProgress = progress * snoozeStep;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void handleVolumeOfFirstRing() {
        firstRingVolume = (SeekBar) findViewById(R.id.volumeOfFirstRing);
        firstRingVolume.setMax(ringMax / ringStep);
        firstRingVolume.setProgress(0);
        firstRingVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
/*    private List<String> getSnoozeIntervals() {
        return new ArrayList<String>() {{
            add("1");
            add("5");
            add("10");
            add("15");
            add("unlimited");
//            add("ccc");
//            add("7");
//            add("ddd");
//            add("9");
        }};
    }
    private List<String> getVolumeIntervals() {
        return new ArrayList<String>() {{
            add("0%");
            add("20");
            add("40");
            add("65");
            add("80");
            add("100%");
//            add("7");
//            add("ddd");
//            add("9");
        }};
    }

    private SeekbarWithIntervals getSeekbarWithIntervals() {
        if (snoozeLimits == null) {
            snoozeLimits = (SeekbarWithIntervals) findViewById(R.id.snoozeLimit);
        }

        return snoozeLimits;
    }
    private SeekbarWithIntervals getSeekbarWithVolumeIntervals() {
        if (firstRingVolume == null) {
            firstRingVolume = (SeekbarWithIntervals) findViewById(R.id.volumeOfFirstRing);
        }

        return firstRingVolume;
    }*/

    private void handleIncreaseSoundSwitch() {
        increaseSound = (Switch) findViewById(R.id.increaseSoundSwitch);
        increaseSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    private void addItemOnFinalRingtone() {
        finalRingtone = (Spinner) findViewById(R.id.finalSnoozeRingtone);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ringtone_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalRingtone.setAdapter(adapter);
        finalRingtone.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    @Override
    public void onClick(View v) {
        snoozePeriodPickerDialog();
    }
    private void snoozePeriodPickerDialog() {
        NumberPicker myNumberPicker = new NumberPicker(this);
        myNumberPicker.setMaxValue(30);
        myNumberPicker.setMinValue(1);
        NumberPicker.OnValueChangeListener myValChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                snoozePeriodPicker.setText(""+newVal);
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
}
