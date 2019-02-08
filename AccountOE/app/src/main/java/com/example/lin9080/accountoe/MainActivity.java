package com.example.lin9080.accountoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.angmarch.views.NiceSpinner;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ArrayList<Account> accounts=new ArrayList<>();
    final AccountAdapter adapter=new AccountAdapter(accounts);
    final LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
    int newPurpose=0;
    int newIsGo=0;
    int ckVi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.toleft_dr);
        }
        //以上为界面初始化设置
        LitePal.getDatabase();//数据库初始化
        ArrayList<Account> nAccounts=(ArrayList<Account>)DataSupport.findAll(Account.class);
        for(int i=nAccounts.size()-1;i>=0;i--){
            accounts.add(nAccounts.get(i));
        }
        isAcsNull();
        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.newerAccount);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //以上为初始数据设置
        (findViewById(R.id.usethetime)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                final int year=now.get(Calendar.YEAR);
                final int month=now.get(Calendar.MONTH)+1;
                final int day=now.get(Calendar.DATE);
                ((TextView)findViewById(R.id.edi_year)).setText(""+year);
                ((TextView)findViewById(R.id.edi_month)).setText(""+month);
                ((TextView)findViewById(R.id.edi_day)).setText(""+day);
            }
        });
        //时间获取
        (findViewById(R.id.getAc)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String year=((TextView)findViewById(R.id.edi_year)).getText().toString();
                ((TextView)findViewById(R.id.edi_year)).setText("");
                String month=((TextView)findViewById(R.id.edi_month)).getText().toString();
                ((TextView)findViewById(R.id.edi_month)).setText("");
                String day=((TextView)findViewById(R.id.edi_day)).getText().toString();
                ((TextView)findViewById(R.id.edi_day)).setText("");
                String time=year+"-"+ month+"-"+day;
                String WhatDo=((TextView)findViewById(R.id.edi_WhatDo)).getText().toString();
                ((TextView)findViewById(R.id.edi_WhatDo)).setText("");
                String number=((TextView)findViewById(R.id.edi_number)).getText().toString();
                ((TextView)findViewById(R.id.edi_number)).setText("");
                if(!isNumber(year)||!isNumber(month)||!isNumber(day)){
                    Toast.makeText(MainActivity.this,"日期输入不规范！",Toast.LENGTH_SHORT).show();
                }else if(!isNumber(number)){
                    Toast.makeText(MainActivity.this,"金额输入不规范！",Toast.LENGTH_SHORT).show();
                }else {
                    Account account = new Account();
                    account.setNumber(number);
                    account.setUseTime(time);
                    account.setWhatDo(WhatDo);
                    account.setPurpose(newPurpose);
                    account.setIsGo(newIsGo);
                    account.save();
                    accounts.add(0, account);
                    isAcsNull();
                    adapter.notifyItemInserted(0);
                    recyclerView.getLayoutManager().scrollToPosition(0);
                }
            }
        });
        //以上为数据库相关设置

        NiceSpinner purpose = (NiceSpinner)findViewById(R.id.edi_purpose);
        List<String> ppsList = new ArrayList<>();
        ppsList.add("其他消费");
        ppsList.add("饮食");
        ppsList.add("服饰美容");
        ppsList.add("生活日用");
        ppsList.add("住房缴费");
        ppsList.add("交通出行");
        ppsList.add("通讯物流");
        ppsList.add("文教娱乐");
        ppsList.add("运动健康");
        ppsList.add("收入");
        purpose.attachDataSource(ppsList);
        purpose.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 :
                        newPurpose=0;
                        newIsGo=0;
                        break;
                    case 1 :
                        newPurpose=1;
                        newIsGo=0;
                        break;
                    case 2 :
                        newPurpose=2;
                        newIsGo=0;
                        break;
                    case 3 :
                        newPurpose=3;
                        newIsGo=0;
                        break;
                    case 4 :
                        newPurpose=4;
                        newIsGo=0;
                        break;
                    case 5 :
                        newPurpose=5;
                        newIsGo=0;
                        break;
                    case 6 :
                        newPurpose=6;
                        newIsGo=0;
                        break;
                    case 7 :
                        newPurpose=7;
                        newIsGo=0;
                        break;
                    case 8 :
                        newPurpose=8;
                        newIsGo=0;
                        break;
                    case 9:
                        newPurpose=9;
                        newIsGo=1;
                    default:
                }
            }
        });
        //以上为niceSpinner设置
        final NavigationView navView=findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent=new Intent(MainActivity.this,Time_howlong.class);
                switch (menuItem.getItemId()){
                    case R.id.nav_day:
                        intent.putExtra("time",0);
                        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawers();
                        startActivity(intent);
                        break;
                    case R.id.nav_week:
                        intent.putExtra("time",1);
                        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawers();
                        startActivity(intent);
                        break;
                    case R.id.nav_month:
                        intent.putExtra("time",2);
                        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawers();
                        startActivity(intent);
                        break;
                    case R.id.nav_all:
                        intent.putExtra("time",3);
                        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawers();
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
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("清空记录").setMessage("是否清空所有用户账目?").setCancelable(true);
                dialog.setPositiveButton("清除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataSupport.deleteAll(Account.class);
                        RecyclerView recyclerView=findViewById(R.id.newerAccount);
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
                if(ckVi==0) {
                    for (int i = 0; i < accounts.size(); i++) {
                        View view = manager.findViewByPosition(i);
                        view.setClickable(true);
                        RelativeLayout layout = (RelativeLayout) view;
                        TextView itemNb = layout.findViewById(R.id.newerNumber);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) (itemNb).getLayoutParams();
                        params.leftMargin = itemNb.getLeft() + 100;
                        params.rightMargin = itemNb.getRight() - 100;
                        CheckBox itemCk = layout.findViewById(R.id.itemCk);
                        itemCk.setVisibility(View.VISIBLE);
                        ckVi=1;
                    }
                }else {
                    for (int i = 0; i < accounts.size(); i++) {
                        View view = manager.findViewByPosition(i);
                        view.setClickable(false);
                        RelativeLayout layout = (RelativeLayout) view;
                        TextView itemNb = layout.findViewById(R.id.newerNumber);
                        CheckBox itemCk = layout.findViewById(R.id.itemCk);
                        itemCk.setVisibility(View.GONE);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) (itemNb).getLayoutParams();
                        params.leftMargin = itemNb.getLeft()-100;
                        params.rightMargin = itemNb.getRight()+100;
                        ckVi=0;
                }
            }
            break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
    static boolean isNumber(String string){
        if(string.length()<=0) {
            return false;
        }else{
            for (int i = 0; i < string.length(); i++) {
                if (!Character.isDigit(string.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
    boolean isAcsNull(){
        if(accounts.isEmpty()){
            ((TextView)findViewById(R.id.isNull)).setText("账簿暂无账目");
            return false;
        }else{
            ((TextView)findViewById(R.id.isNull)).setText("");
            return true;
        }
    }
}
