package com.bomin.practice2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PersonAdapter personAdapter;

    RecyclerView recyclerView;

    ArrayList<Person> personList = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDate();

        recyclerView = findViewById(R.id.recyclerView);

        personAdapter = new PersonAdapter(personList);

        recyclerView.setAdapter(personAdapter);
    }

    //샘플 데이터 생성
    public void createDate(){

        for(int i = 0; i < 10; i++){
            Person person = new Person("id"+i, "name"+i, "age"+i, "sex"+i);
            personList.add(person);
        }
    }
}