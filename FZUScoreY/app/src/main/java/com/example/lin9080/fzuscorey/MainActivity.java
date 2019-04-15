package com.example.lin9080.fzuscorey;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Student> students=new ArrayList<>();
    private int flag=0;//0为学生，1为班长
    private static Button login,StuLog,MonLog;
    private static EditText Account,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInit();
        //以上为界面的初始化
        LitePal.getDatabase();//数据库初始化
        students.addAll(LitePal.findAll(Student.class));//获取数据库中所有学生对象
        TestInit();//测试方法
    }
    private void LayoutInit(){
        login=(Button)findViewById(R.id.login);
        StuLog=(Button)findViewById(R.id.stu_log);
        MonLog=(Button)findViewById(R.id.mon_log);
        Account=(EditText)findViewById(R.id.account);
        Password=(EditText)findViewById(R.id.password);
        ButtonChange(flag);
        ButtonInit();
    }
    private void ButtonInit(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String acc=Account.getText().toString();
                String pas=Password.getText().toString();
                if(flag==0){
                    int i;
                    for (i=0;i<students.size();i++){
                        Student student=students.get(i);
                        if((student.getStuid()).equals(acc)){
                            if(student.getPassword().equals(pas)){
                                //TODO 进入学生界面
                                Log.d("登录", "登录允许 ");
                                break;
                            }
                        }
                    }
                    if(i>=students.size()){
                        Toast.makeText(MainActivity.this,"账号不存在或密码错误，登录失败",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //TODO 进入班长界面
                }
            }
        });
        StuLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                ButtonChange(flag);
            }
        });
        MonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                ButtonChange(flag);
            }
        });
    }
    private void ButtonChange(int flag){//按键颜色的改变
        if(flag==0){
            StuLog.setTextColor(Color.BLUE);
            MonLog.setTextColor(Color.BLACK);
        }else{
            MonLog.setTextColor(Color.BLUE);
            StuLog.setTextColor(Color.BLACK);
        }
    }
    private void TestInit(){
        Student student=new Student();
        student.setTerm(1);
        student.setStuid("031702416");
        student.setPassword("mm36550332");
        student.setName_1("大物");
        student.setScore_1(50);
        student.setName_2("高数");
        student.setScore_2(50);
        student.setName_3("离散");
        student.setScore_3(50);
        student.setName_4("算法");
        student.setScore_4(50);
        student.setName_5("毛概");
        student.setScore_5(50);
        student.setName_6("英语");
        student.setScore_6(50);
        student.setRank(40);
        student.setPercentage("40%");
        student.setAverage(70);
        student.save();
    }
}
