package com.example.custalarmable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;

public class DefaultSetting extends AppCompatActivity implements View.OnClickListener{

    Context mcontext;
    private String[] ringtone_arrays;
    Button snoozePeriodPicker;

    //two sliders
    Slider snoozeLimitSlider;
    Slider volumeSlider;

    //increase sound switch
    Switch increaseSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_setting);
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

        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(this, R.array.ringtone_arrays, R.layout.dropdown_menu_popup_item);
        AutoCompleteTextView editTextFilledExposedDropdown2 =
                findViewById(R.id.outlined_exposed_dropdown2);
        editTextFilledExposedDropdown2.setAdapter(adapter2);
        ringtone_arrays = getResources().getStringArray(R.array.ringtone_arrays);
        editTextFilledExposedDropdown2.setText(ringtone_arrays[0], false);

        snoozeLimitSlider = findViewById(R.id.snoozeLimitDefault);
        snoozeLimitSlider.setLabelFormatter(new SnoozeSetting.NumberSnoozeLabelFormatter());
        volumeSlider = findViewById(R.id.volumeOfFirstRingDefault);
        volumeSlider.setEnabled(false);
        TextView volumeOfFirstRingTitle = (TextView)findViewById(R.id.volumeOfFirstRingTitleDefault);
        volumeOfFirstRingTitle.setTextColor(getResources().getColor(android.R.color.darker_gray));

        //change snooze period
        handleSnoozePeriod();

        //increase sound switch
        handleIncreaseSoundSwitch();

        mcontext = getApplicationContext();
        complete();
    }
    private void handleSnoozePeriod() {
        snoozePeriodPicker = findViewById(R.id.snoozePeriodDefault);
        snoozePeriodPicker.setOnClickListener(this);
    }

    private void handleIncreaseSoundSwitch() {
        increaseSound = findViewById(R.id.increaseSoundSwitchDefault);
        increaseSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView volumeOfFirstRingTitle = (TextView)findViewById(R.id.volumeOfFirstRingTitleDefault);
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
        findViewById(R.id.addItemBtnDefault).setOnClickListener(new CompoundButton.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.addItemBtnDefault ) {
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
}
