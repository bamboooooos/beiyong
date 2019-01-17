package com.example.lin9080.bookshelf;

/*
* 颜色设置
*
*
*
* */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Shelf.class);
                Toast.makeText(MainActivity.this, "跳转中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
