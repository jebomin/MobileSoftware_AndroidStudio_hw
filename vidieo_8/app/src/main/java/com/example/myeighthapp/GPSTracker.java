package com.example.myeighthapp;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

public class GPSTracker extends Service implements LocationListener {
    private final Context myContext;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetGPSInfo = false;

    Location myLocation;
    double latitude;
    double longitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; //10 meters 단위로 업데이트
    private static final long MIN_TIME_UPDATES = 30000; // 30초 단위로 업데이트
    protected LocationManager myLocationManager;//location 객체 핸들링

    public GPSTracker(Context context) {
        myContext = context;
        getLocation();
    }

    //초기화 하는 부분
    public Location getLocation() {
        try {
            myLocationManager = (LocationManager) myContext.getSystemService(LOCATION_SERVICE);

            isGPSEnabled = myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); // 퍼미션 얻는법 : 기기로부터
            isNetworkEnabled = myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER); //퍼미션 얻는법 : 네트워크 프로바이더
            if (!isGPSEnabled && !isNetworkEnabled) {
                // do nothing
            } else {
                this.canGetGPSInfo = true;
                if (isNetworkEnabled) {
                    //퍼미션 체크도 해줘야함 이제
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        myLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if(myLocation != null) {
                            latitude = myLocation.getLatitude();
                            longitude = myLocation.getLongitude();
                        }
                    }
                }
                if(isGPSEnabled) {
                    if(myLocation == null) {
                        myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if(myLocationManager != null) {
                            myLocation = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(myLocation != null) {
                                latitude = myLocation.getLatitude();
                                longitude = myLocation.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myLocation;
    }

    //인터페이스에서 버튼 누를 때 동작하는 부분
    public void stopUsingGPS() {
        if(myLocation != null) {
            myLocationManager.removeUpdates(GPSTracker.this);
        }
    }
    public double getLatitude() {
        if(myLocation != null) {
            latitude = myLocation.getLatitude();
        }
        return latitude;
    }
    public double getLongitude() {
        if(myLocation != null) {
            longitude = myLocation.getLongitude();
        }
        return longitude;
    }
    public boolean canGetLocation() {
        return this.canGetGPSInfo;
    }
    public void showSettingAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);
        alertDialog.setTitle("GPS는 설정중입니다.");
        alertDialog.setMessage("GPS는 활성화 되지 않았습니다. 설정 메뉴로 이동할까요?");
                alertDialog.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                myContext.startActivity(intent);
                            }
                        });
        alertDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        alertDialog.show();
    }

    //아래와 같은 리스너 안 만들면 오류남
    @Override
    public void onLocationChanged(Location location) {
    }
    @Override
    public void onProviderEnabled(String s) {
    }
    @Override
    public void onProviderDisabled(String s) {
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) { //bundle : 클래스간에 내용을 주고 받을 때 패키지? 인텐트를 통해서 클래스간에 내용을 전달하는데 이 역시 같은 기능
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}