package com.example.final_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Information> saveInfo = new ArrayList<>();
    EditText editTextName, editTextPhone, editTextEmail;
    Button buttonSave;
    static String name;
    static String phone;
    static String email;

    public static final String NAME = "com.example.final_2021.NAME";
    public static final String PHONE = "com.example.final_2021.PHONE";
    public static final String EMAIL = "com.example.final_2021.EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextName.getText().toString();
                phone = editTextPhone.getText().toString();
                email = editTextEmail.getText().toString();
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }

    public static String getName() {
        return name;
    }

    public static String getPhone() {
        return phone;
    }

    public static String getEmail() {
        return email;
    }

}