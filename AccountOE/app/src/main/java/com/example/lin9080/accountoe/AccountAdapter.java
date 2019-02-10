package com.example.lin9080.accountoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    private ArrayList<Account> maccounts;
    public AccountAdapter(ArrayList<Account> accounts) {
        maccounts=accounts;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        View AcView;
        TextView number;
        TextView Time;
        TextView WhatDo;
        TextView Purpose;

        public ViewHolder(View view){
            super(view);
            AcView=view;
            number=(TextView)view.findViewById(R.id.newerNumber);
            Time=(TextView)view.findViewById(R.id.newerTime);
            WhatDo=(TextView) view.findViewById(R.id.newerWhatDo);
            Purpose=(TextView) view.findViewById(R.id.newerPurpose);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.AcView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=viewHolder.getAdapterPosition();
                Account account=maccounts.get(position);
                final MyDialog myDialog;
                myDialog=new MyDialog(view.getContext(), R.style.Dialog,account);
                myDialog.show();
                myDialog.setOnDismissListener(new DialogInterface.OnDismissListener(){
                    @Override
                    public void onDismiss(DialogInterface dialog){
                        notifyDataSetChanged();
                    }
                });
            }
        });
        viewHolder.AcView.setClickable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Account account=maccounts.get(position);
        holder.number.setText("金额"+account.getNumber()+"");
        holder.Time.setText("时间"+account.getUseTime()+"");
        holder.WhatDo.setText(account.getWhatDo()+"");
        switch (account.getPurpose()){
            case 1:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("饮食");
                break;
            case 2:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("服饰美容");
                break;
            case 3:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("生活日用");
                break;
            case 4:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("住房缴费");
                break;
            case 5:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("交通出行");
                break;
            case 6:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("通讯物流");
                break;
            case 7:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("文教娱乐");
                break;
            case 8:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("运动健康");
                break;
            case 9:
                holder.itemView.setBackgroundColor(Color.GRAY);
                holder.Purpose.setText("收入");
                break;
            case 0:
                holder.itemView.setBackgroundColor(Color.LTGRAY);
                holder.Purpose.setText("其他");
                break;
                default:
        }
    }

    @Override
    public int getItemCount() {
        return maccounts.size();
    }
}
