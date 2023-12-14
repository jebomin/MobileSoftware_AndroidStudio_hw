package com.bomin.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    EditText updateNameEdit, updatePhoneEdit;

    ImageView updateImageView;

    String id, name, phoneNumber;

    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        updateNameEdit = findViewById(R.id.update_name_edit); //이름
        updatePhoneEdit = findViewById(R.id.update_phone_edit); //번호
        updateImageView = findViewById(R.id.update_image_view); //사진

        getAndSetIntentData();
    }


    /**
     * 데이터 가져와서 화면에 보여주기
     */
    public void getAndSetIntentData() {

        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("phone_number") && getIntent().hasExtra("image")) {

            //데이터 가져오기
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            phoneNumber = getIntent().getStringExtra("phone_number");
            image = getIntent().getByteArrayExtra("image");

            //데이터 넣기
            updateNameEdit.setText(name);
            updatePhoneEdit.setText(phoneNumber);

            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

            updateImageView.setImageBitmap(bitmap);
        }
    }
}