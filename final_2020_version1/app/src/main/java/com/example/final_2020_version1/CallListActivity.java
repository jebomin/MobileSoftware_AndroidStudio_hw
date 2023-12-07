package com.example.final_2020_version1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CallListActivity extends AppCompatActivity {
    private static final String[] CALL_ITEMS = {"학사운영실", "정보통신공", "학장실"};
    private static final String[] PHONE_NUMBERS = {"02-2260-3833", "02-2260-3833", "02-2260-3833"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);

        ListView listView = findViewById(R.id.listViewCall);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CALL_ITEMS);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Call the selected institution
                String phoneNumber = PHONE_NUMBERS[position];
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            }
        });
    }
}