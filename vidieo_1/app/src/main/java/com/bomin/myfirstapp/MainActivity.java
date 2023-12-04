package com.bomin.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.bomin.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate : 프로그램상에서 루틴이 도는게 아니라, 상황이 벌어졌을 때 시스템에서 호출하는 콜백함수[앱을 터치하게 되면 실행되는 화면]
        super.onCreate(savedInstanceState); //AppCompatActivity의 기본 내용을 구현하라는 내용[기본 화면 구성]
        setContentView(R.layout.activity_main); //추가 내용 구성, activity_main화면에 있는 내용(res에 있는)
        //추가로 앱 초기 화면에 넣고 싶은 부분은 아래에 넣음 으로써 추가할 수 있음, 내용 추가는 상속을 통해 구현
    }

    //버튼을 클릭하면 특정 메시지가 출력되도록 함
    public void sendMessage(View view) {
        //메인 화면에서 버튼을 누를 때, 입력한 텍스트를 다음화면에 전달
        Intent intent = new Intent(this,
                DisplayMessageActivity.class);
        EditText editText = (EditText)
                findViewById(R.id.editTextText);//editTextText안에 있는 문자열을 가져와서
        String message = editText.getText().toString(); //그걸 보여 줌
        intent.putExtra(EXTRA_MESSAGE, message); //intent에 넣어주고
        startActivity(intent); //다음화면에 전달(다음 화면이란?  DisplayMessageActivity임)
    }
}