package com.example.myeighthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button myButton;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String myPermission = android.Manifest.permission.ACCESS_FINE_LOCATION; //android 추가
    GPSTracker myGPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //퍼미션 관련 부분
            if (ActivityCompat.checkSelfPermission(this, myPermission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{myPermission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        myButton = (Button) findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myGPS = new GPSTracker(MainActivity.this);//context에 대한 것 gps에 대한 정보를 GPSTracker에게 넘겨줌(엑티비티와 gps가 상호간에 연결됨)
                if (myGPS.canGetLocation()) {
                    double latitude = myGPS.getLatitude();
                    double longitude = myGPS.getLongitude();
                    Toast.makeText(getApplicationContext(), "당신의 위치는 경도: " + latitude + " " + "위도: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    myGPS.showSettingAlert();
                }
            }
        });
    }
}
