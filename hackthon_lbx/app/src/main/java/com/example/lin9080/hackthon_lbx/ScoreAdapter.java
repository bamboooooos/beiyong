package com.example.lin9080.hackthon_lbx;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    ArrayList<ScoreOfStu> scoreOfStus=new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView StuID;
        TextView StuName;
        TextView StuSort;
        TextView StuScore;
        public ViewHolder(View view){
            super(view);
            StuID=(TextView)view.findViewById(R.id.StuID);
            StuName=(TextView)view.findViewById(R.id.StuName);
            StuSort=(TextView)view.findViewById(R.id.StuSort);
            StuScore=(TextView)view.findViewById(R.id.StuScore);
        }
    }
    public ScoreAdapter(ArrayList<ScoreOfStu> scoreOfStus){
        this.scoreOfStus=scoreOfStus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScoreOfStu stu=scoreOfStus.get(position);
        holder.StuID.setText(stu.getStuID());
        holder.StuName.setText(stu.getStuName());
        int all=0;
        for(int i=0;i<stu.getScores().size();i++){
            all+=stu.getScores().get(i).getScore();
        }
        holder.StuScore.setText(all+"分");
        holder.StuSort.setText("排名"+stu.getSort());
    }

    @Override
    public int getItemCount() {
        return scoreOfStus.size();
    }
}
