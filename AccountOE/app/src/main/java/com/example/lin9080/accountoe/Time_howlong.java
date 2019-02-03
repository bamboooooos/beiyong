package com.example.lin9080.accountoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Time_howlong extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ArrayList<Account> accounts=new ArrayList<>();
    final AccountAdapter adapter=new AccountAdapter(accounts);
    int timeof;
    int newPurpose=0;
    int newIsGo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_howlong);
        Intent intent=getIntent();
        timeof=intent.getIntExtra("time",666);
        //拿到上个活动传来的数据
        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.time_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.toleft_dr);
        }
        //以上为界面初始化设置
        ArrayList<Account> nAccounts=(ArrayList<Account>) DataSupport.findAll(Account.class);
        switch (timeof){
            case 0:
                for(int i=nAccounts.size()-1;i>=0;i--){
                    if(getTime(nAccounts.get(i).getUseTime()).getTime()>getTimeNeed(0).getTime()){
                        accounts.add(nAccounts.get(i));
                    }
                }
                break;
            case 1:
                for(int i=nAccounts.size()-1;i>=0;i--){
                    if(getTime(nAccounts.get(i).getUseTime()).getTime()>getTimeNeed(1).getTime()){
                        accounts.add(nAccounts.get(i));
                    }
                }
                break;
            case 2:
                for(int i=nAccounts.size()-1;i>=0;i--){
                    if(getTime(nAccounts.get(i).getUseTime()).getTime()>getTimeNeed(2).getTime()){
                        accounts.add(nAccounts.get(i));
                    }
                }
                break;
            case 3:
                for(int i=nAccounts.size()-1;i>=0;i--){
                    accounts.add(nAccounts.get(i));
                }
                break;
                default:
        }
        //数据筛选
        isAcsNull();
        int result=total(accounts,1);
        ((TextView)findViewById(R.id.Get)).setText("收入:"+result);
        result=total(accounts,0);
        ((TextView)findViewById(R.id.Put)).setText("支出:"+result);
        result=total(accounts,2);
        if(result>=0)
            ((TextView)findViewById(R.id.total)).setText("总共支出:"+result);
        else
            ((TextView)findViewById(R.id.total)).setText("总共收入:"+(-result));
        //数据统计
        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.AccountNeed);
        final LinearLayoutManager manager=new LinearLayoutManager(Time_howlong.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //数据初始化
        NavigationView navView=findViewById(R.id.nav_view2);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent=new Intent(Time_howlong.this,Time_howlong.class);
                switch (menuItem.getItemId()){
                    case R.id.nav_day:
                        intent.putExtra("time",0);
                        Time_howlong.this.finish();
                        startActivity(intent);
                        break;
                    case R.id.nav_week:
                        intent.putExtra("time",1);
                        Time_howlong.this.finish();
                        startActivity(intent);
                        break;
                    case R.id.nav_month:
                        intent.putExtra("time",2);
                        Time_howlong.this.finish();
                        startActivity(intent);
                        break;
                    case R.id.nav_all:
                        intent.putExtra("time",3);
                        Time_howlong.this.finish();
                        startActivity(intent);
                        break;
                    default:
                }
                return true;
            }
        });
        //以上为滑动界面设置
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                AlertDialog.Builder dialog=new AlertDialog.Builder(Time_howlong.this);
                dialog.setTitle("清空记录").setMessage("是否清空所有用户账目?").setCancelable(true);
                dialog.setPositiveButton("清除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataSupport.deleteAll(Account.class);
                        RecyclerView recyclerView=findViewById(R.id.AccountNeed);
                        recyclerView.removeAllViews();
                        accounts.clear();
                        adapter.notifyDataSetChanged();
                        isAcsNull();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            case R.id.change:
                Toast.makeText(Time_howlong.this,"编辑功能可用",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
    boolean isAcsNull(){
        if(accounts.isEmpty()){
            ((TextView)findViewById(R.id.isNull2)).setText("账簿暂无账目");
            return false;
        }else{
            ((TextView)findViewById(R.id.isNull2)).setText("");
            return true;
        }
    }
    Date getTime(String time){
        Date result=new Date();
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        try {
            result = format.parse(time+ "-00-00-00");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    Date getTimeNeed(int cases){//cases012对应一天，一周，一月
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//可以方便地修改日期格式
        Date ndate=new Date();
        Date result;
        switch (cases){
            case 0:
                ndate.setTime(ndate.getTime()-1*24*3600*1000);
                break;
            case 1:
                ndate.setTime(ndate.getTime()-7*24*3600*1000);
                break;
            case 2:
                ndate.setTime(ndate.getTime()-15*24*3600*1000);
                ndate.setTime(ndate.getTime()-15*24*3600*1000);
                break;
            default:
        }
        result=ndate;
        return result;
    }
    int total(ArrayList<Account> data,int cases){//0是支出，1是收入，2是总计(支出为正)
        int result=0;
        switch (cases){
            case 0:
                for(int i=0;i<data.size();i++){
                    if(data.get(i).getIsGo()==0){
                        result+=Integer.parseInt(data.get(i).getNumber());
                    }
                }
                break;
            case 1:
                for(int i=0;i<data.size();i++){
                    if(data.get(i).getIsGo()==1){
                        result+=Integer.parseInt(data.get(i).getNumber());
                    }
                }
                break;
            case 2:
                for(int i=0;i<data.size();i++){
                    if(data.get(i).getIsGo()==0){
                        result+=Integer.parseInt(data.get(i).getNumber());
                    }else {
                        result-=Integer.parseInt(data.get(i).getNumber());
                    }
                }
                break;
        }
        return result;
    }
}
