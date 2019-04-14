package com.example.lin9080.hackthon_lbx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class monitor2 extends AppCompatActivity {
    ArrayList<ScoreOfStu> list=new ArrayList<>();
    final ScoreAdapter adapter=new ScoreAdapter(list);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor2);
        initTest();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.scoreSort);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
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
        list.add(stu);
    }
}
