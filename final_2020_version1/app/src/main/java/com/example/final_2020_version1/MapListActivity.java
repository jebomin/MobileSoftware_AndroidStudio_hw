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

public class MapListActivity extends AppCompatActivity {
    private static final String[] MAP_ITEMS = {"신공학관", "공대학사운영실", "기숙사"};
    private static final double[] LATITUDES = {37.558058787929134, 37.5589262868377, 37.558402166228966};
    private static final double[] LONGITUDES = {126.99840306484138, 126.99889548582202, 126.99814771755447};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list);

        ListView listView = findViewById(R.id.listViewMap);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MAP_ITEMS);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Show map for the selected location
                showMap(position);
            }
        });
    }

    private void showMap(int position) {
        double latitude = LATITUDES[position];
        double longitude = LONGITUDES[position];

        Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(Location)");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
