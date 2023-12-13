package com.bomin.practice2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    ArrayList<Person> psersonList = new ArrayList<Person>();

    public PersonAdapter(ArrayList<Person> psersonList) {
        this.psersonList = psersonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.person_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Person person = psersonList.get(position);

        //화면에 데이터 담기
        holder.setItem(person);

        //아이템 클릭 이벤트
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int mPostion = holder.getAdapterPosition();

                Context context = view.getContext();

                Intent detailActivity = new Intent(context, DetailActivity.class);

                detailActivity.putExtra("id"    ,psersonList.get(mPostion).getId()); //아이디
                detailActivity.putExtra("name"  ,psersonList.get(mPostion).getName()); //이름
                detailActivity.putExtra("age"   ,psersonList.get(mPostion).getAge()); //나이
                detailActivity.putExtra("sex"   ,psersonList.get(mPostion).getSex()); //성별

                ((MainActivity)context).startActivity(detailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return psersonList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView id_text, name_text;
        CardView card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id_text = itemView.findViewById(R.id.id_text);
            name_text = itemView.findViewById(R.id.name_text);
            card_view = itemView.findViewById(R.id.layout_container);
        }

        public void setItem(Person person){

            id_text.setText(person.getId());
            name_text.setText(person.getName());
        }
    }
}