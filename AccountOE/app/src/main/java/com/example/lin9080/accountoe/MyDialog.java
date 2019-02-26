package com.example.lin9080.accountoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyDialog extends Dialog implements View.OnClickListener {
    int newPurpose=0;
    int newIsGo=0;
    private Context context;
    private Account account;
    private EditText changed_number,changed_WhatDo,changed_Year,changed_Month,changed_Day;
    private NiceSpinner changed_Purpose;
    private Button commit, cancel ,getNow ,deleteItem;
    private LeaveMyDialogListener listener;

    public interface LeaveMyDialogListener {
        public void onClick(View view);
    }

    public MyDialog(Context context) {
        super(context);
        this.context = context;
    }

    public MyDialog(Context context,int theme,Account account) {
        super(context,theme);
        this.context = context;
        this.account = account;
    }
    //onCreate中对dialog的组件进行资源的获取以及监听的添加。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.alertdialog_view);
        cancel = (Button) findViewById(R.id.cancel);    //关闭的按钮
        commit = (Button) findViewById(R.id.commit);     //提交按钮的资源
        changed_number = (EditText) findViewById(R.id.changeNumber);
        changed_WhatDo = (EditText) findViewById(R.id.changeWhatDo);
        changed_Year = (EditText) findViewById(R.id.changeYear);
        changed_Month = (EditText) findViewById(R.id.changeMonth);
        changed_Day = (EditText) findViewById(R.id.changeDay);
        changed_Purpose = (NiceSpinner) findViewById(R.id.changePurpose);
        getNow = (Button) findViewById(R.id.getChanged_now);
        deleteItem =(Button)findViewById(R.id.deleteItem) ;
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
        ppsList.add("");
        ppsList.add("");
        ppsList.add("");
        ppsList.add("");
        changed_Purpose.attachDataSource(ppsList);
        changed_Purpose.addOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        getNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                final int year=now.get(Calendar.YEAR);
                final int month=now.get(Calendar.MONTH)+1;
                final int day=now.get(Calendar.DATE);
                changed_Year.setText(""+year);
                changed_Month.setText(""+month);
                changed_Day.setText(""+day);
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String year=changed_Year.getText().toString();
                ((TextView)findViewById(R.id.changeYear)).setText("");
                String month=changed_Month.getText().toString();
                ((TextView)findViewById(R.id.changeMonth)).setText("");
                String day=changed_Day.getText().toString();
                ((TextView)findViewById(R.id.changeDay)).setText("");
                String time=year+"-"+ month+"-"+day;
                String WhatDo=changed_WhatDo.getText().toString();
                ((TextView)findViewById(R.id.changeWhatDo)).setText("");
                String number=changed_number.getText().toString();
                ((TextView)findViewById(R.id.changeNumber)).setText("");
                if(!MainActivity.isNumber(year)||!MainActivity.isNumber(month)||!MainActivity.isNumber(day)){
                    Toast.makeText(view.getContext(),"日期输入不规范！",Toast.LENGTH_SHORT).show();
                }else if(!MainActivity.isNumber(number)){
                    Toast.makeText(view.getContext(),"金额输入不规范！",Toast.LENGTH_SHORT).show();
                }else {
                    account.setNumber(number);
                    account.setUseTime(time);
                    account.setWhatDo(WhatDo);
                    account.setPurpose(newPurpose);
                    account.setIsGo(newIsGo);
                    account.save();
                    dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account.delete();
                cancel();
            }
        });
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

}
