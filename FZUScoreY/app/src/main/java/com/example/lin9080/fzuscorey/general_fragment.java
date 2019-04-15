package com.example.lin9080.fzuscorey;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin9080.fzuscorey.R;
import com.example.lin9080.fzuscorey.StuActivity;
import com.example.lin9080.fzuscorey.Student;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class general_fragment extends Fragment {
    private ArrayList<recycler_item> items = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_fragment,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.score_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        StuActivity stuActivity = (StuActivity) getActivity();
        String id = stuActivity.getAcco();
        ArrayList<Student> students= (ArrayList<Student>) LitePal.findAll(Student.class);
        for(int i=0;i<6;i++){

        }
        StudentAdapter adapter = new StudentAdapter(items);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return view;
    }
}