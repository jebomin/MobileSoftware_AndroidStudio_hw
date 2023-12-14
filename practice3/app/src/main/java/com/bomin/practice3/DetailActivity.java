package com.bomin.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
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

        updateNameEdit = findViewById(R.id.update_name_edit);
        updatePhoneEdit = findViewById(R.id.update_phone_edit);
        updateImageView = findViewById(R.id.update_image_view);

        //전화번호 하이픈(-) 자동입력
        updatePhoneEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        //데이터 가져와서 화면에 보여주기
        getAndSetIntentData();

        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = updateNameEdit.getText().toString();
                String phoneNumber = updatePhoneEdit.getText().toString();

                PhoneBookDB db = new PhoneBookDB(DetailActivity.this);
                db.updateData(id, name, phoneNumber);
            }
        });
    }

    public void getAndSetIntentData() {

        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("phone_number") && getIntent().hasExtra("image")){

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