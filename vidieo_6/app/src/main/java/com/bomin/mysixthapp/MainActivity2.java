package com.bomin.mysixthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView myText = (TextView)findViewById(R.id.textView);
        Uri uri = getIntent().getData();
        myText.setText(uri.toString() + " is accepted.");
    }
}