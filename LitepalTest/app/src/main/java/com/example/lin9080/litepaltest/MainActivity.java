package com.example.lin9080.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private int count=1;
    ArrayList<Book> books=new ArrayList<>();
    ArrayList<Book> anotherBooks=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.createNewMap)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
            }
        });
        ((Button)findViewById(R.id.addNew)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book=new Book();
                book.setName("the"+count+"Book");
                book.setAuthor("God");
                book.setId(count);
                book.setPages(3*count);
                book.setPrice(Math.sqrt(count*5));
                anotherBooks.add(book);
                book.save();
                count++;
            }
        });
        ((Button)findViewById(R.id.load)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                books= (ArrayList<Book>) DataSupport.findAll(Book.class);
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.Data);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                BookAdapter adapter=new BookAdapter(anotherBooks);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
