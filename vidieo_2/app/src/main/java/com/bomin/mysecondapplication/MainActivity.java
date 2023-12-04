package com.bomin.mysecondapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    String message = "Android App Message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.d(message, "onCreate() operated!"); //디버깅 창에서 메시지를 보임
        
        //앱 화면에서 textView를 통해 메시지를 보임
        text = (TextView)findViewById(R.id.textView);
        text.setText("My onStart!!");
    }

    @Override
    protected void onResume(){ //onResume이라는 콜백함수는 디폴트로 있는데 추가 기능을 넣기위해 override함
        super.onResume();
        Log.d(message, "onResume() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("My onResume!!!");
    }

    // Called when the activity is about to become visible.
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(message, "onStart() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("My onStart !!!");
    }
    // Called when another activity is taking focus.
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(message, "onPause() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("My onPause !!!");
    }
    // Called when the activity is no longer visible.
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(message, "onStop() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("My onPause !!!");
    }
    // Called just before the activity is destroyed.
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(message, "onDestroy() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("My onDestroy !!!");
    }
}
