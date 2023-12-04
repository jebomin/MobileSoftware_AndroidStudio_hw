package com.bomin.mythirdapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class DummyService extends Service {
    public DummyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flgs, int startId){ //인텐트가 왔을 때 얘를 실행
        Toast toast = Toast.makeText(this, "Service is started", Toast.LENGTH_LONG); // toast : 화면에 어떤 텍스트를 보여주는 라이브러리
        toast.setGravity(Gravity.TOP, 0,0); // 화면이 보여지는 문자 위치(제일 위에 위치하도록 함)
        toast.show(); //보여주는 명령어
        return START_STICKY; //여기에 붙어있어라 = 끝날때까지 실행해라
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast toast = Toast.makeText(this, "Service is destroyed", Toast.LENGTH_LONG); // toast : 화면에 어떤 텍스트를 보여주는 라이브러리
        toast.setGravity(Gravity.TOP, 0,0); // 화면이 보여지는 문자 위치(제일 위에 위치하도록 함)
        toast.show(); //보여주는 명령어
    }
}