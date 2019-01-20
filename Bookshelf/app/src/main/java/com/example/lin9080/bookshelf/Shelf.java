package com.example.lin9080.bookshelf;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Shelf extends AppCompatActivity {

    ArrayList<Book> books=new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                AlertDialog.Builder dialog=new AlertDialog.Builder(Shelf.this);
                dialog.setTitle("删除");
                dialog.setMessage("确认删除所有用户信息吗?(除了初始10本书)");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FileOutputStream out=null;
                        BufferedWriter writer=null;
                        try{
                            out=openFileOutput("BookName", Context.MODE_PRIVATE);
                            writer=new BufferedWriter(new OutputStreamWriter(out));
                            writer.write("");
                        }catch(Exception e){
                            e.printStackTrace();
                        }finally{
                            try{
                                if(writer!=null){
                                    writer.close();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(Shelf.this,"删除成功，请重新打开书架",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        ArrayList<Book> newBooks=load();
        for(int i=newBooks.size()-1;i>=0;i--){
            books.add(newBooks.get(i));
        }
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
                save(string);
                ((EditText)findViewById(R.id.editText)).setText("");
                books.add(0,new Book(string));
                adapter.notifyItemInserted(0);
                recyclerView.getLayoutManager().scrollToPosition(0);
            }
        });
    }
    void getInitBook(){
        books.add(new Book("以下是推荐书目"));
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
    void save(String bookname){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("BookName", Context.MODE_APPEND);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(bookname+"\n");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    ArrayList<Book> load(){//读取
        FileInputStream in=null;
        BufferedReader reader=null;
        ArrayList<Book> newbookList=new ArrayList<>();
        String line="";
        try {
            in=openFileInput("BookName");
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line=reader.readLine())!=null){
                newbookList.add(new Book(line));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(reader!=null){
                    reader.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return newbookList;
    }
}
