package com.example.custalarmable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.Switch;




public class MainActivity extends AppCompatActivity   {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Switch mySwitch = (Switch)  findViewById(R.id.switch1);
        final ToggleButton myToggleButton1= (ToggleButton)  findViewById(R.id.buttonSun);
        final ToggleButton myToggleButton2= (ToggleButton)  findViewById(R.id.buttonMon);
        final ToggleButton myToggleButton3= (ToggleButton)  findViewById(R.id.buttonTue);
        final ToggleButton myToggleButton4= (ToggleButton)  findViewById(R.id.buttonWed);
        final ToggleButton myToggleButton5= (ToggleButton)  findViewById(R.id.buttonThur);
        final ToggleButton myToggleButton6= (ToggleButton)  findViewById(R.id.buttonFri);
        final ToggleButton myToggleButton7= (ToggleButton)  findViewById(R.id.buttonSat);
        myToggleButton1.setEnabled(false);
        myToggleButton2.setEnabled(false);
        myToggleButton3.setEnabled(false);
        myToggleButton4.setEnabled(false);
        myToggleButton5.setEnabled(false);
        myToggleButton6.setEnabled(false);
        myToggleButton7.setEnabled(false);
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
                }
                if (isChecked==true){

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

