package com.example.lin9080.litepaltest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90806 on 2019/1/25.
 * BookAdapter is test,should be changed to AccountAdapter
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    ArrayList<Book> mbooks;
    BookAdapter(ArrayList<Book> books){
        mbooks=books;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        TextView time;
        TextView WhatDo;
        public ViewHolder(View v){
            super(v);
            number=(TextView)v.findViewById(R.id.number);
            time=(TextView)v.findViewById(R.id.time);
            WhatDo=(TextView)v.findViewById(R.id.WhatDo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book oneBook=mbooks.get(position);
        holder.number.setText(oneBook.getId());
        holder.WhatDo.setText(oneBook.getName()+oneBook.getPages());
        holder.time.setText(""+oneBook.getPrice());
    }


    @Override
    public int getItemCount() {
        return mbooks.size();
    }
}
