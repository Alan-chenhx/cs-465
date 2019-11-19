package com.example.custalarmable;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class week extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private CombinedChart dataChart;//图表
    private CombinedData data;
    private Integer[] datas = {3,4,1,5,8,0,7};
    private Integer[] z = {8, 9, 4, 5, 7, 4, 9};
    private float avg(Integer[] nums){
        float sum=0;
        for (int value:nums){
            sum+=value;
        }
        return sum/nums.length;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_week, null);
        dataChart = view.findViewById(R.id.chart1);
        showDataOnChart();
        Legend legend = dataChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        return view;
    }
    private void showDataOnChart() {
        //绘制图表数据
        data = new CombinedData();
        //设置折线图数据
       data.setData(getLineData());
        //设置柱状图数据
        data.setData(getBarData());
        dataChart.setData(data);

        setAxisXBottom();

        setAxisYRight();

        setAxisYLeft();
        dataChart.setTouchEnabled(false);
        dataChart.getDescription().setEnabled(false);
        dataChart.setDrawGridBackground(false);
        dataChart.setDrawBarShadow(false);
        dataChart.setHighlightFullBarEnabled(true);
        dataChart.animateX(2000);
//        dataChart.getAxisRight().setEnabled(false);
    }
    private void setAxisXBottom() {
        List<String> valuesX = new ArrayList<>();
        String[] date = {"S", "M", "T", "W", "Th", "F", "S"};
        for (int i = 0;i < date.length;i++){
            valuesX.add(date[i]);
        }
        XAxis bottomAxis = dataChart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setCenterAxisLabels(true);
        bottomAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        bottomAxis.setAxisMinimum(data.getXMin()-0.5f);
        bottomAxis.setAxisMaximum(data.getXMax()+0.5f);
        bottomAxis.setLabelCount(date.length);
        bottomAxis.setAvoidFirstLastClipping(true);

    }
    private void setAxisYRight() {
        YAxis right = dataChart.getAxisRight();
        right.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return Integer.toString((int) value);
            }
        });
        right.setDrawGridLines(false);
    }
    private void setAxisYLeft() {
        YAxis left = dataChart.getAxisLeft();
        left.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return Integer.toString((int) value);
            }
        });
        left.setDrawGridLines(false);
        float avg_num=avg(z);
        LimitLine yLimitLine = new LimitLine(avg_num,"AVG");
        yLimitLine.setLineColor(Color.BLUE);
        yLimitLine.setTextColor(Color.BLUE);
        yLimitLine.setLineWidth(3);
//        YAxis leftAxis = chart.getAxisLeft();
        left.addLimitLine(yLimitLine);
    }

    public LineData getLineData() {
        LineData lineData = new LineData();
        List<Entry> customCounts = new ArrayList<>();

        //人数
        for (int i = 0; i < datas.length; i++) {
            customCounts.add(new Entry(i + 0.5f,datas[i]));
        }
        LineDataSet lineDataSet = new LineDataSet(customCounts,"snooze times");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        lineDataSet.setColor(Color.parseColor("#66A3AB"));
//        lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleColor(Color.parseColor("#66A3AB"));
        lineDataSet.setCircleRadius(5);
        lineDataSet.setLineWidth(3);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextColor(Color.parseColor("#66A3AB"));
        lineData.addDataSet(lineDataSet);
        return lineData;
    }
    public BarData getBarData() {
        BarData barData = new BarData();

        List<BarEntry> amounts = new ArrayList<>();


        for (int i = 0; i < z.length; i++) {
            amounts.add(new BarEntry(i + 0.5f,z[i]));
        }

        BarDataSet amountBar = new BarDataSet(amounts,"Hours of Sleep");
        amountBar.setAxisDependency(YAxis.AxisDependency.LEFT);
        amountBar.setColor(Color.parseColor("#C23531"));

        amountBar.setValueTextSize(0);

        barData.addDataSet(amountBar);


        float barWidth = 0.6f;
        barData.setBarWidth(barWidth);
//        barData.groupBars(0, groupSpace, barSpace);
        return barData;
    }

}
