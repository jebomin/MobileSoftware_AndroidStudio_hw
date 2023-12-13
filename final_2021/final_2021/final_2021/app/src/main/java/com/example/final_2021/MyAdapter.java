package com.example.final_2021;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.Manifest;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 1; // 요청 코드에 대한 고유한 상수 사용
        TextView textViewName, textViewPhone, textViewEmail;

        MyViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.textViewName);
            textViewPhone = view.findViewById(R.id.textViewPhone);
            textViewEmail = view.findViewById(R.id.textViewEmail);

            textViewPhone.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = textViewPhone.getText().toString();
                    Intent dialIntent = new Intent(Intent.ACTION_VIEW);
                    dialIntent.setData(Uri.parse("tel: " + phone));

                    view.getContext().startActivity(dialIntent);
                }
            }));

            textViewEmail.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String email = textViewEmail.getText().toString();
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + email));
                    view.getContext().startActivity(emailIntent);
                }
            });
        }
    }

    private ArrayList<Information> myInfoList;

    MyAdapter(ArrayList<Information> infos) {
        this.myInfoList = infos;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.textViewName.setText(myInfoList.get(position).getName());
        myViewHolder.textViewPhone.setText(myInfoList.get(position).getPhone());
        myViewHolder.textViewEmail.setText(myInfoList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return myInfoList.size();
    }
}
