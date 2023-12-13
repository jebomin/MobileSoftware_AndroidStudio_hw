package com.example.final_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    EditText editTextPhone, editTextEmail;

    String phone = "";
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        String name = MainActivity.getName();
        String phone = MainActivity.getPhone();
        String email = MainActivity.getEmail();

        MainActivity.saveInfo.add(new Information(name, phone, email));

        MyAdapter myAdapter = new MyAdapter(MainActivity.saveInfo);

        myRecyclerView.setAdapter(myAdapter);
    }
}