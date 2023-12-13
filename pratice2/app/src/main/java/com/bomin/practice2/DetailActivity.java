package com.bomin.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView detail_id_text, detail_name_text, detail_age_text, detail_sex_text;

    String id, name, age, sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_id_text = findViewById(R.id.detail_id_text);
        detail_name_text = findViewById(R.id.detail_name_text);
        detail_age_text = findViewById(R.id.detail_age_text);
        detail_sex_text = findViewById(R.id.detail_sex_text);

        //메인엑티비티에서 받아온 데이터
        Intent intent = getIntent();

        id = intent.getExtras().getString("id");
        name = intent.getExtras().getString("name");
        age = intent.getExtras().getString("age");
        sex = intent.getExtras().getString("sex");

        detail_id_text.setText(id);
        detail_name_text.setText(name);
        detail_age_text.setText(age);
        detail_sex_text.setText(sex);
    }
}