package com.bkstudios.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bkstudios.myapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding = null;

    ArrayList<Feeds> data = new ArrayList<>();
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Second.class);
                startActivity(intent);
            }
        });
       RecyclerView view=(RecyclerView)findViewById(R.id.list);
       RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        view.setLayoutManager(manager);
        adapter=new ListAdapter(data,this);
        view.setAdapter(adapter);

    }

    public void delete(int pos, Feeds feedObj) {
        data.remove(pos);

        adapter.notifyDataSetChanged();

    }
    public void update(Feeds feeds){

        Intent intent=new Intent(MainActivity.this,Second.class);
        intent.putExtra("name",feeds.getPost());
        intent.putExtra("name1",feeds.getAutoId());

        //intent.putExtra("feed", feeds);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();

        DataThread dataThread=new DataThread(MainActivity.this,data, adapter);
        dataThread.execute();
    }
}
