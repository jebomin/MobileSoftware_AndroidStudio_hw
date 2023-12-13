package com.bomin.final_2020_version2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String selectedResult = intent.getStringExtra("selectedResult");
        String imageName = getImageName(selectedResult);
        int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(imageResourceId);
    }

    private String getImageName(String result) {
        switch (result) {
            case "손흥민 골 직후":
                return "after_goal";
            case "손흥민 경기 후":
                return "after_game";
            case "손흥민 입대":
                return "enlisting";
            default:
                return "default_image";
        }
    }
}
