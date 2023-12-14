package com.bomin.practice4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    //선언
    ArrayList<ToDoModel> taskList;
    ToDoAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    EditText todoText;
    Button addBtn;

    //DB
    ToDoDB db;

    //입력 레이아웃
    LinearLayout bottomLayout;

    //할일 ID
    int gId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB 연결
        db = new ToDoDB(this);

        ///초기화
        taskList = new ArrayList<>();
        bottomLayout = findViewById(R.id.bottom_section);
        todoText = findViewById(R.id.todo_text);
        addBtn = findViewById(R.id.add_btn);
        fab = findViewById(R.id.fab);

        //recyclerView 설정
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adapter 설정
        adapter = new ToDoAdapter(db);
        adapter.setTasks(taskList);

        //adapter 적용
        recyclerView.setAdapter(adapter);

        //조회
        selectData();

        //등록모드
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewMode("ADD");
            }
        });

        //추가 버튼
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewMode("FAB");

                String text = todoText.getText().toString();

                //ADD면 등록 아니면 수정
                if(addBtn.getText().toString().equals("ADD")){

                    //데이터 담기
                    ToDoModel task = new ToDoModel();
                    task.setTask(text);
                    task.setStatus(0);

                    //할일 추가
                    db.addTask(task);

                    //조회 및 리셋
                    selectReset("ADD");

                }else{ //수정

                    //할일 수정
                    db.updateTask(gId, text);

                    //조회 및 리셋
                    selectReset("UPDATE");
                }

                //키보드 내리기
                hideKeyboard(todoText);
            }
        });

        //할일 입력 체크
        todoText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    addBtn.setEnabled(false);
                    addBtn.setTextColor(Color.GRAY);
                }else{
                    addBtn.setEnabled(true);
                    addBtn.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

    }//onCreate

    /**
     * 조회
     */
    public void selectData() {

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        adapter.setTasks(taskList);
        adapter.notifyDataSetChanged();
    }

    /**
     * 조회및 리셋
     * @param type
     */
    public void selectReset(String type){

        //조회
        selectData();

        //초기화
        todoText.setText("");

        //등록이 아니면 등록으로 변경
        if(!type.equals("ADD")){
            addBtn.setText("ADD");
        }
    }

    /**
     * 화면상태
     * @param type 상태타입
     */
    public void viewMode(String type){

        //입력하고 나면 입력창 사라지고 FAB 보여줌
        if(type.equals("FAB")){
            //입력창 숨김
            bottomLayout.setVisibility(View.GONE);

            //fab 보여줌
            fab.setVisibility(View.VISIBLE);

        }else{//FAB누르면 입력창 보여주고 FAB 사라짐

            //입력창 보여줌
            bottomLayout.setVisibility(View.VISIBLE);

            //fab 숨김
            fab.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 키보드 숨기는 이벤트
     * @param editText 입력뷰
     */
    private void hideKeyboard(EditText editText) {

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //키보드 숨김
        manager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
    }

}//MainActivity