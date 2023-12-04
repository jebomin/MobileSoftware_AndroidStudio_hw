package com.bomin.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();//그 인텐트를 받아서
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);//메시지를 추출
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);//추출한 메시지를 display
    }
}