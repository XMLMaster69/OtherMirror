package com.example.othermirror;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class YoutubeSearcher extends AppCompatActivity {
    EditText youtube_searcher;
    ListView youtube_lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_youtube_searcher);

        youtube_searcher = (EditText) findViewById(R.id.youtube_searcher);
        youtube_lst = (ListView) findViewById(R.id.youtube_list);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getString("youtube") != null){
                Toast.makeText(getApplicationContext(), "youtube", Toast.LENGTH_SHORT);
            }
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("youtubevideo 1");
        arrayList.add("youtubevideo 2");
        arrayList.add("youtubevideo 3");
        arrayList.add("youtubevideo 4");
        arrayList.add("youtubevideo 5");
        arrayList.add("youtubevideo 6");
        arrayList.add("youtubevideo 7");
        arrayList.add("youtubevideo 8");
        arrayList.add("youtubevideo 9");
        arrayList.add("youtubevideo 10");
        arrayList.add("youtubevideo 11");
        arrayList.add("youtubevideo 12");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        youtube_lst.setAdapter(arrayAdapter);

    }
}
