package com.example.lin9080.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Shelf extends AppCompatActivity {

    ArrayList<Book> books=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        getInitBook();
        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.shelf);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        final BookAdapter adapter=new BookAdapter(books);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=((EditText)findViewById(R.id.editText)).getText().toString();
                ((EditText)findViewById(R.id.editText)).setText("");
                books.add(0,new Book(string));
                adapter.notifyItemInserted(0);
                recyclerView.getLayoutManager().scrollToPosition(0);
            }
        });
    }
    void getInitBook(){
        books.add(new Book("第一行代码"));
        books.add(new Book("大物下"));
        books.add(new Book("算法与数据结构"));
        books.add(new Book("毛泽东思想"));
        books.add(new Book("离散数学"));
        books.add(new Book("数字逻辑电路"));
        books.add(new Book("大学英语(四)"));
        books.add(new Book("家"));
        books.add(new Book("春"));
        books.add(new Book("秋"));
    }
}
