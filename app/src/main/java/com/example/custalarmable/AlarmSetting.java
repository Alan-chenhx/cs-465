package com.example.custalarmable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.button.MaterialButton;


public class AlarmSetting extends AppCompatActivity   {

    MaterialButton sleep;
    MaterialButton work;
    Switch mySwitch;
    ToggleButton auto_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar_sleep_data));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.ringtone_arrays, R.layout.dropdown_menu_popup_item);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.outlined_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
        sleep=(MaterialButton) findViewById(R.id.sleep_type);
        work    =(MaterialButton) findViewById(R.id.work_type);
        mySwitch = (Switch)  findViewById(R.id.switch1);
        Toggle_control();
        type_control();

    }

    private void type_control(){
        sleep.setChecked(true);
        sleep.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener(){
            public void onCheckedChanged (MaterialButton button,boolean isChecked){
                if(isChecked==true){
                    work.setChecked(false);
                }
            }
        });
        work.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener(){
            public void onCheckedChanged (MaterialButton button,boolean isChecked){
                if(isChecked==true){
                    sleep.setChecked(false);
                }
            }
        });


    }

    private void Toggle_control(){
        final ToggleButton myToggleButton1= (ToggleButton)  findViewById(R.id.buttonSun);
        final ToggleButton myToggleButton2= (ToggleButton)  findViewById(R.id.buttonMon);
        final ToggleButton myToggleButton3= (ToggleButton)  findViewById(R.id.buttonTue);
        final ToggleButton myToggleButton4= (ToggleButton)  findViewById(R.id.buttonWed);
        final ToggleButton myToggleButton5= (ToggleButton)  findViewById(R.id.buttonThur);
        final ToggleButton myToggleButton6= (ToggleButton)  findViewById(R.id.buttonFri);
        final ToggleButton myToggleButton7= (ToggleButton)  findViewById(R.id.buttonSat);
        auto_delete = (ToggleButton) findViewById(R.id.auto_delete);
        myToggleButton1.setEnabled(false);
        myToggleButton2.setEnabled(false);
        myToggleButton3.setEnabled(false);
        myToggleButton4.setEnabled(false);
        myToggleButton5.setEnabled(false);
        myToggleButton6.setEnabled(false);
        myToggleButton7.setEnabled(false);
        auto_delete.setChecked(false);
        auto_delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    mySwitch.setChecked(false);
                }
            }
        });
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked==false){

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
                }else{
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

