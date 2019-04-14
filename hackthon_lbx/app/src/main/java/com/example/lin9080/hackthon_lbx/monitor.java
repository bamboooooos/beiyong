package com.example.lin9080.hackthon_lbx;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class monitor extends AppCompatActivity {
    private BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        barChart=(BarChart)findViewById(R.id.chart);
        ArrayList<ChartBean> chartBeans=new ArrayList<>();
        chartBeans.add(new ChartBean(50,"测试"));
        chartBeans.add(new ChartBean(60,"测试2"));
        chartBeans.add(new ChartBean(55,"测试"));
        chartBeans.add(new ChartBean(56,"测试2"));
        chartBeans.add(new ChartBean(70,"测试"));
        chartBeans.add(new ChartBean(87,"测试2"));
        BarChartUtil.initBarChart(barChart,chartBeans);
        BarChartUtil.showBarChart(barChart,chartBeans,"成绩",Color.RED);
    }

}
