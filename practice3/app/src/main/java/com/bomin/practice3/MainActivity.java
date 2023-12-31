package com.bomin.practice3;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PhoneBookDB db;

    ArrayList<PhoneBook> phoneList = new ArrayList<>();

    RecyclerView recyclerView;

    PhoneBookAdapter adapter;

    TextView noDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //이미지 크기 오류 방지
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

        //데이터 유무 텍스트
        noDataText = findViewById(R.id.noData_text);

        //리스트 보여줄 화면
        recyclerView = findViewById(R.id.recyclerView);

        //어뎁터
        adapter = new PhoneBookAdapter(MainActivity.this);

        //어뎁터 등록
        recyclerView.setAdapter(adapter);

        //레이아웃 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //DB 생성성
        db = new PhoneBookDB(MainActivity.this);

        storeDataInArray();

        FloatingActionButton addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //등록화면으로 이동
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 데이터 가져오기
     */
    void storeDataInArray(){

        Cursor cursor = db.readAllData();

        if(cursor.getCount() == 0){

            noDataText.setVisibility(noDataText.VISIBLE);
        }else{
            noDataText.setVisibility(noDataText.GONE);

            while(cursor.moveToNext()){

                PhoneBook phone = new PhoneBook(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getBlob(3));

                //데이터 등록
                phoneList.add(phone);
                adapter.addItem(phone);

                //적용
                adapter.notifyDataSetChanged();
            }
        }
    }
}