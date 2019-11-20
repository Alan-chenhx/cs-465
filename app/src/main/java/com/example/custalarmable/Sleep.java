package com.example.custalarmable;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.race604.drawable.wave.WaveDrawable;

public class Sleep extends AppCompatActivity  implements TimeAnimator.TimeListener {
    AnimationDrawable dismissAnimation;
    public Button dismissButton;
    public Button snoozeButton;
    private ProgressBar progressBar;

    private TimeAnimator mAnimator;
    private int mCurrentLevel = 0;
    private ClipDrawable mClipDrawable;
    boolean pressed=false;
    private WaveDrawable mWaveDrawable;
    //    private long startTime;
    private Thread thread;
    private int height;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        long startTime = System.currentTimeMillis();
        long currTime = System.currentTimeMillis();
//        Button dismiss=(Button) findViewById(R.id.snooze);
//        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        dismissButton = (Button) findViewById(R.id.dismiss);
//        snoozeButton = (Button) findViewById(R.id.snooze);
//        dismissButton.setBackgroundResource(R.drawable.animation);
//        dismissAnimation = (AnimationDrawable)dismissButton.getBackground();
//        snoozeButton.setOnClickListener(this);
        mWaveDrawable = new WaveDrawable(this, R.drawable.waves);
//        mWaveDrawable.setLevel(50);
        height=mWaveDrawable.getIntrinsicHeight();
        mWaveDrawable.setIndeterminate(false);
//        mImageView.setImageDrawable(mWaveDrawable);
//        LayerDrawable layerDrawable = (LayerDrawable) findViewById(R.id.dismiss).getBackground();
        mAnimator = new TimeAnimator();
        mAnimator.setTimeListener(this);
        mAnimator.setDuration(5000);
//        mWaveDrawable.setIndeterminateAnimator(mAnimator);
//        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setDuration(5000);
////        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        mWaveDrawable.setIndeterminateAnimator(animator);


        mWaveDrawable.setLevel(50);
        dismissButton.setBackground(mWaveDrawable);
//        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setDuration(5000);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        mWaveDrawable.setIndeterminateAnimator(animator);
//        mWaveDrawable.setIndeterminate(true);
//        mWaveDrawable.setLevel(5000);
//        animator.setFloatValues(1f);
//        animator.setCurrentFraction(0.4f);
//        animator.pause();
//        mWaveDrawable.onAnimationUpdate(animator);


//        ValueAnimator animator = ValueAnimator.ofInt(0, 10);
//        mClipDrawable = (ClipDrawable) layerDrawable.findDrawableByLayerId(R.id.clip_drawable);
//        if(mClipDrawable==null){
//            Toast.makeText(this, "fuck", Toast.LENGTH_SHORT).show();
//        }


//        findViewById(R.id.dismiss).setOnTouchListener(new View.OnTouchListener() {
//            public boolean  onLongClick(View v){
//                animateButton(v);
//                return true;
//            }
//
//        });

//
        thread = new Thread(new Runnable() {
            @Override
            public void run(){
                long startTime = System.currentTimeMillis();
                while (!Thread.currentThread().isInterrupted() && mWaveDrawable.getLevel()<100) {
                    long currTime = System.currentTimeMillis();
                    int progress = (int) ((currTime - startTime) / 5000.0 * 100);
                    mWaveDrawable.setLevel(progress);
                }
                if (mWaveDrawable.getLevel()<100) {
                    mWaveDrawable.setLevel(0);
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissButton.setText("Confirm Dismiss");
                            dismissButton.setOnTouchListener(null);
                            dismissButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            });
                        }
                    });
                }
            }
        });
        findViewById(R.id.dismiss).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    mWaveDrawable.setIndeterminate(true);
//                    thread.start();
                    animateButton(v);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    thread.interrupt();
                    if (mWaveDrawable.isRunning()) {
                        mCurrentLevel = 0;
                        mAnimator.cancel();
//                        mAnimator.reverse();
//                        animateButton(v);
                        mWaveDrawable.setLevel(mCurrentLevel);
                    }
                    //dismissButton.setBackgroundColor(Color.parseColor("#D7D7D7"));
                }
                return true;
            }
        });

    }
//
//        dismissButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    thread.start();
//                    //dismissButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    thread.interrupt();
//                    //dismissButton.setBackgroundColor(Color.parseColor("#D7D7D7"));
//                }
//                return true;
//            }
//        });
//

//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.dismiss) {
//            dismissAnimation.start();
//        }
//    }
    @Override
    public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
        mWaveDrawable.setLevel(mCurrentLevel);
        if(mCurrentLevel <0){
            mAnimator.cancel();
            mCurrentLevel=0;
        }
        if (mCurrentLevel >= MAX_LEVEL) {
            mAnimator.cancel();
            dismissButton.setText("Confirm Dismiss");
            dismissButton.setOnTouchListener(null);
            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } else {
            mCurrentLevel =(int) Math.min(MAX_LEVEL, (int)2*totalTime);
        }
    }

    public void animateButton(View view) {
        if (!mAnimator.isRunning()) {
//            mCurrentLevel = 0;
            mAnimator.start();
        }
    }

    private static final int LEVEL_INCREMENT = 400;
    private static final int MAX_LEVEL = 10000;
}
