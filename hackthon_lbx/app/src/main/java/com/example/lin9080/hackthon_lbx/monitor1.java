package com.example.lin9080.hackthon_lbx;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;

public class monitor1 extends AppCompatActivity {
    private ArrayList<ScoreOfStu> scoreOfStus=new ArrayList<>();
    private ArrayList<ChartBean> chartBeans=new ArrayList<>();
    static int COLOR= Color.RED;
    BarChart barChart1;
    BarChart barChart2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor1);
        barChart1=(BarChart)findViewById(R.id.chartOfClass);
        barChart2=(BarChart)findViewById(R.id.chartOfSubject) ;
        ckinit();
        initTerm(1);
    }
    private void ckinit(){
        final CheckBox ck1=(CheckBox)findViewById(R.id.sub1);
        final CheckBox ck2=(CheckBox)findViewById(R.id.sub2);
        final CheckBox ck3=(CheckBox)findViewById(R.id.sub3);
        final CheckBox ck4=(CheckBox)findViewById(R.id.sub4);
        ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ck2.setChecked(false);
                    ck3.setChecked(false);
                    ck4.setChecked(false);
                    initTerm(1);
                }else {
                    ck1.setChecked(true);
                }
            }
        });
        ck2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ck1.setChecked(false);
                    ck3.setChecked(false);
                    ck4.setChecked(false);
                    initTerm(2);
                }else {
                    ck2.setChecked(true);
                }
            }
        });
        ck3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ck2.setChecked(false);
                    ck1.setChecked(false);
                    ck4.setChecked(false);
                    initTerm(3);
                }else {
                    ck3.setChecked(true);
                }
            }
        });
        ck4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ck2.setChecked(false);
                    ck3.setChecked(false);
                    ck1.setChecked(false);
                    initTerm(4);
                }else {
                    ck4.setChecked(true);
                }
            }
        });
    }
    private void initTerm(int term){
        if(term==1)
            initTest();
        else{
            initTerm(term);
            Log.d("test", "换学期");
        }
        getDataNeed(0);
        BarChartUtil.initBarChart(barChart1,chartBeans);
        BarChartUtil.showBarChart(barChart1,chartBeans,"占比",COLOR);//班级概况
        getDataNeed(1);
        ArrayList<ChartBean> c1=new ArrayList<>();
        c1.addAll(chartBeans);
        getDataNeed(2);
        ArrayList<ChartBean> c2=new ArrayList<>();
        c2.addAll(chartBeans);
        getDataNeed(3);
        ArrayList<ChartBean> c3=new ArrayList<>();
        c3.addAll(chartBeans);
        getDataNeed(4);
        ArrayList<ChartBean> c4=new ArrayList<>();
        c4.addAll(chartBeans);
        getDataNeed(5);
        ArrayList<ChartBean> c5=new ArrayList<>();
        c5.addAll(chartBeans);
        BarChartUtil.initBarChart(barChart2,chartBeans);
        BarChartUtil.showBarChart(barChart2,c1,c2,c3,c4,c5);
    }

    private void getDataNeed(int i){
        chartBeans.clear();
        switch (i){
            //获取班级概况
            case 0:
                int result=0;
                int high=0;
                int low=100;
                int excellent=0;
                int fail=0;
                for(int j=0;j<scoreOfStus.size();j++){
                    ScoreOfStu stu=scoreOfStus.get(i);
                    for(int m=0;m<stu.getScores().size();m++){
                        result+=stu.getScores().get(m).getScore();
                        if(stu.getScores().get(m).getScore()>high) {
                            high = stu.getScores().get(m).getScore();
                        }
                        if(stu.getScores().get(m).getScore()<low){
                            low=stu.getScores().get(m).getScore();
                        }
                        if(stu.getScores().get(m).getScore()>85){
                            excellent++;
                        }
                        if(stu.getScores().get(m).getScore()<60){
                            fail++;
                        }
                    }
                }
                chartBeans.add(new ChartBean(excellent/6/(scoreOfStus.size())*100,"总优秀率"));
                chartBeans.add(new ChartBean(fail/6/(scoreOfStus.size())*100,"总不及格率"));
                chartBeans.add(new ChartBean(result/6/(scoreOfStus.size()),"总平均分"));
                chartBeans.add(new ChartBean(high,"总最高分"));
                chartBeans.add(new ChartBean(low,"总最低分"));
            //获取不同数据1-5
                break;
            case 1://优秀率
                int[] exc=new int[6];
                for(int j=0;j<scoreOfStus.size();j++){
                    ScoreOfStu stu=scoreOfStus.get(j);
                    for(int m=0;m<6;m++){
                        if(stu.getScores().get(m).getScore()>85){
                            exc[m]++;
                        }
                    }
                }
                for(int n=0;n<6;n++){
                    chartBeans.add(new ChartBean((exc[n]/(scoreOfStus.size())*100),scoreOfStus.get(0).getScores().get(n).getSubject()));
                }
                break;
            case 2://不及格率
                int[] fai=new int[6];
                for(int j=0;j<scoreOfStus.size();j++){
                ScoreOfStu stu=scoreOfStus.get(j);
                    for(int m=0;m<6;m++){
                        if(stu.getScores().get(m).getScore()<60){
                            fai[m]++;
                        }
                    }
                }
                for(int n=0;n<6;n++){
                    chartBeans.add(new ChartBean((fai[n]/(scoreOfStus.size())*100),scoreOfStus.get(0).getScores().get(n).getSubject()));
                }
                break;
            case 3://平均分
                int[] avr=new int[6];
                for(int j=0;j<scoreOfStus.size();j++){
                    ScoreOfStu stu=scoreOfStus.get(j);
                    for(int m=0;m<6;m++){
                        avr[m]+=stu.getScores().get(m).getScore();
                    }
                }
                for(int n=0;n<6;n++){
                    chartBeans.add(new ChartBean(avr[n]/(scoreOfStus.size()),scoreOfStus.get(0).getScores().get(n).getSubject()));
                }
                break;
            case 4://最高分
                int[] hig=new int[6];
                for(int j=0;j<scoreOfStus.size();j++){
                    ScoreOfStu stu=scoreOfStus.get(j);
                    for(int m=0;m<6;m++){
                        if(stu.getScores().get(m).getScore()>hig[m]){
                            hig[m]=stu.getScores().get(m).getScore();
                        }
                    }
                }
                for(int n=0;n<6;n++){
                    chartBeans.add(new ChartBean(hig[n],scoreOfStus.get(0).getScores().get(n).getSubject()));
                }
                break;
            case 5://最低分
                int[] lo={100,100,100,100,100,100};
                for(int j=0;j<scoreOfStus.size();j++){
                    ScoreOfStu stu=scoreOfStus.get(j);
                    for(int m=0;m<6;m++){
                        if(stu.getScores().get(m).getScore()<lo[m]){
                            lo[m]=stu.getScores().get(m).getScore();
                        }
                    }
                }
                for(int n=0;n<6;n++){
                    chartBeans.add(new ChartBean(lo[n],scoreOfStus.get(0).getScores().get(n).getSubject()));
                }
                break;
        }
    }
    private void initData(int term){
        //在此获取数据
    }
    void initTest(){
        ArrayList<Score> scores=new ArrayList<>();
        scores.add(new Score("共产主义",30));
        scores.add(new Score("共产主1",33));
        scores.add(new Score("共产主2",35));
        scores.add(new Score("共产主3",32));
        scores.add(new Score("共产主4",50));
        scores.add(new Score("共产主5",35));
        ScoreOfStu stu=new ScoreOfStu("031702416", "林斌祥",scores,1);
        scoreOfStus.add(stu);
    }
}
