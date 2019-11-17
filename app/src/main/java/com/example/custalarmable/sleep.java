package com.example.custalarmable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.ProgressBar;


public class sleep extends AppCompatActivity implements View.OnClickListener {
    AnimationDrawable dismissAnimation;
    public Button dismissButton;
    public Button snoozeButton;
    private ProgressBar progressBar;
//    private long startTime;
    private Thread thread;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        dismissButton = (Button) findViewById(R.id.dismiss);
        snoozeButton = (Button) findViewById(R.id.snooze);
        dismissButton.setBackgroundResource(R.drawable.animation);
        dismissAnimation = (AnimationDrawable)dismissButton.getBackground();
        snoozeButton.setOnClickListener(this);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                while (!Thread.currentThread().isInterrupted() && progressBar.getProgress() < 100) {
                    long currTime = System.currentTimeMillis();
                    int progress = (int) ((currTime - startTime) / 5000.0 * 100);
                    progressBar.setProgress(progress);
                }
                if (progressBar.getProgress() < 100) {
                    progressBar.setProgress(0);
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissButton.setText("Confirm Dismiss");
                        }
                    });
                }
            }
        });

        dismissButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    thread.start();
                    //dismissButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    thread.interrupt();
                    //dismissButton.setBackgroundColor(Color.parseColor("#D7D7D7"));
                }
                return true;
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dismiss) {
            dismissAnimation.start();
    }

}
}
