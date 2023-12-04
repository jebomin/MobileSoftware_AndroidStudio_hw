package com.bomin.myfifthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 학생 데이터를 입력하며 입력한 모든 학생 데이터를 화면에 보여주는 메소드를 만들어야 함
    public void addStudent(View view) {
        ContentValues addValues = new ContentValues(); //데이터베이스에 넣기 위해 contentvalues라는 인스턴스를 만들어서 원하는 value를 여기에다가 put을 통해 다 넣음
        addValues.put(MyContentProvider.NAME, ((EditText) findViewById(R.id.editText1)).getText().toString());
        addValues.put(MyContentProvider.STUDENT_ID, ((EditText) findViewById(R.id.editText2)).getText().toString());
        addValues.put(MyContentProvider.PHONE, ((EditText) findViewById(R.id.editText3)).getText().toString());
        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);//database에 contentvalue 인스턴스를 삽입
        Toast.makeText(getBaseContext(), "Record Added", Toast.LENGTH_LONG).show(); //화면에 정상적으로 작동하는지를 확인
    }

    public void getStudent(View view) {
        String[] columns = new String[]{"_id", "student_id", "name", "phone_number"};
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null, null, null, null);
        if (c != null) {
            EditText editMultipleText = findViewById(R.id.editText4);
            editMultipleText.setText("");
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String number = c.getString(1);
                String name = c.getString(2);
                String phone = c.getString(3);
                editMultipleText.append("id: " + id + "\n number: " + number + "\n name: " + name + "\n phone: " + phone + "\n");
            }
            editMultipleText.append("\n Total : " + c.getCount());
            c.close();
        }
    }
}