package com.bomin.mythirdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view){
        startService(new Intent(getBaseContext(), DummyService.class)); //getBaseContext() : 다른 클래스에 대해서 무언가를 실행 하기 위해 전체적인 실행 내용을 같이 전달하자는 것
    }

    public void stopService(View view){
        stopService(new Intent(getBaseContext(), DummyService.class));
    }
}