package com.bomin.myseventhapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView myPicture;
        TextView myText;
        MyViewHolder(View view){
            super(view);
            myPicture = view.findViewById(R.id.imageView);
            myText = view.findViewById(R.id.textView);
        }
    }
    private ArrayList<Schools> mySchoolList;
    MyAdapter(ArrayList<Schools> schools){
        this.mySchoolList = schools;
    }

    // 아래는 어댑터 클래스의 기본 기능이므로 오버라이드 해야 함. 리싸이클러의 각행에 사용되는 레이아웃 xml 을 가져옴.
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }
    // 리싸이클러에 보여지는 이미지와 문장들을 설정함.
    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.myPicture.setImageResource(mySchoolList.get(position).getImage_id());

        myViewHolder.myText.setText(mySchoolList.get(position).getSchool_name());

        myViewHolder.myPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mySchoolList.get(position).getURL()));
                context.startActivity(intent);
            }
        });
        myViewHolder.myText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mySchoolList.get(position).getURL()));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mySchoolList.size();
    }
}
