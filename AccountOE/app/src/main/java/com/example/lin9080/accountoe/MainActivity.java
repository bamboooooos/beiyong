package com.example.lin9080.accountoe;

import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ArrayList<Account> accounts=new ArrayList<>();
    final AccountAdapter adapter=new AccountAdapter(accounts);
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
        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //以上为初始数据设置
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
                    account.setNumber("金额:"+number);
                    account.setUseTime("时间:"+time);
                    account.setWhatDo(WhatDo);
                    account.save();
                    accounts.add(0, account);
                    isAcsNull();
                    adapter.notifyItemInserted(0);
                    recyclerView.getLayoutManager().scrollToPosition(0);
                }
            }
        });
        //以上为数据库相关设置
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
                Toast.makeText(MainActivity.this,"编辑功能可用",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
    boolean isNumber(String string){
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
