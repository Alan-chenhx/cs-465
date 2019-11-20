package com.example.custalarmable;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.TextView;



public class month extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private GridLayout grid;
    private int num_of_rows=5;
    private int days=31;
    private Context mContext;
    private int width;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_month, null);
//        GridLayout.LayoutParams params = GridLayout.getLayoutParams();
        this.mContext=getActivity();
        grid= view.findViewById(R.id.gv);
//        float width=grid.getWidth();
        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        width=dm2.widthPixels;
        ini();
//
        return view;
    }

    private void ini(){
        grid.setRowCount(num_of_rows);
//        grid.
        for(int i=0;i<days;i++){
            double d = Math.random();
//            d=d*10000;
            int num=trans((float)d*1000%12);
            if(num==0){
                num=1;
            }
            TextView tmp=new TextView(this.mContext);
//            grid.getWidth();

            tmp.setMinimumWidth((width-20)/7-50);
            tmp.setMinimumHeight((width-20)/7-50);
            tmp.setBackgroundColor(Color.rgb(0, 97, 0));
            tmp.setAlpha((float)d);
//            tmp.setText(Integer.toString(width));
            GridLayout.Spec rowSpec = GridLayout.spec(i/7);     //设置它的行和列
            GridLayout.Spec columnSpec=GridLayout.spec(i%7);
            GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec,columnSpec);
            grid.addView(tmp,params);


        }
    }
    private int trans(float n){
        return (int) Math.min(5,n/2);
    }

}
