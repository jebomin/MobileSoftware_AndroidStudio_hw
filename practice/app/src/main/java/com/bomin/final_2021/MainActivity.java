package com.bomin.final_2021;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PlaylistItem> playlistItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 샘플 플레이리스트 아이템 생성
        playlistItems = new ArrayList<>();
        playlistItems.add(new PlaylistItem("Ditto", "https://youtu.be/Km71Rr9K-Bw?si=o62iPuT9wsJ5Pjvg"));
        playlistItems.add(new PlaylistItem("Selfmade orange", "https://youtu.be/BlpGB8yvIL0?si=5MauYuURGVCGNcoU"));
        playlistItems.add(new PlaylistItem("띵", "https://youtu.be/GBaKmkppD5A?si=K1N3WVgjKGyuEYeM"));

        // 어댑터 설정
        PlaylistAdapter adapter = new PlaylistAdapter(playlistItems, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 아이템 클릭 시 동작
                String youtubeLink = playlistItems.get(position).getYoutubeLink();
                openYoutubeVideo(youtubeLink);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void openYoutubeVideo(String youtubeLink) {
        // 유튜브 링크를 열기 위한 인텐트 생성
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));

        // 패키지 매니저를 통해 유튜브 앱 설치 여부 확인
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "유튜브 앱이 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
