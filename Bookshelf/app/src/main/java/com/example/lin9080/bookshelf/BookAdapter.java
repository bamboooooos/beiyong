package com.example.lin9080.bookshelf;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by 90806 on 2018/12/21.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    static private int conut=0;
    private ArrayList<Book> books;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View view){
            super(view);
            textView=(TextView)view.findViewById(R.id.bookin);
        }
    }
    public BookAdapter(ArrayList<Book> books){
        this.books=books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book=books.get(position);
        holder.textView.setText(book.getName());
        if(BookAdapter.conut%2==1){
            holder.itemView.setBackgroundColor(Color.CYAN);
        }else{
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }
        BookAdapter.conut++;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
