package com.example.lin9080.accountoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private int conut=1;
    private ArrayList<Account> accounts=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((Button)findViewById(R.id.createALitePal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
            }
        });
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ((Button)findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account=new Account(conut*5,df.format(new Date()),"测试",conut%9);
                account.save();
                conut++;
            }
        });
        ((Button)findViewById(R.id.data)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accounts= (ArrayList<Account>) DataSupport.findAll(Account.class);
                for(int i=0;i<accounts.size();i++) {
                    Log.d("test", accounts.get(i).getNumber()+","+accounts.get(i).getUseTime()+","+accounts.get(i).getWhatDo());
                }
                RecyclerView recyclerView=findViewById(R.id.newerAccount);
                LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                AccountAdapter adapter=new AccountAdapter(accounts);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                DataSupport.deleteAll(Account.class);
                break;
            case R.id.change:
                Toast.makeText(MainActivity.this,"编辑功能可用",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
