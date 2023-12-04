package com.bomin.myfourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyReceiver br;
    String message = "Android App Message : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(message, "On Create");

        br = new MyReceiver(); // 인스턴스를 만듦을로써 MyReceiver.java를 사용할 수 있게 됨

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("com.bomin.CUSTOM_INTENT");
        this.registerReceiver(br, filter); //시스템에 filter와 리시버 등록
        //onCreate하면 이제 초기화 되는 것임
    }

    public void broadcastIntent(View view){//broadcast 메시지를 시스템에 보내는 메소드
        //intent를 만드는 부분 함수
        Intent intent = new Intent(); //여기서 intent란 상황이 발생했을 때 브로드케스팅 하겠다라는 의미
        intent.setAction("com.bomin.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy(); //오버라이드를 하게되면 이 super는 필수적으로 해서 초기화 해줘야함
        this.unregisterReceiver(br);
    }
}