package com.example.othermirror;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.othermirror.Fragments.HomeFragment;

public class MirrorSettings extends AppCompatActivity {

    Button cancel;
    Button done;
    ImageView mirrorSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // For the transistion

        setContentView(R.layout.activity_mirror_settings);
        cancel = (Button) findViewById(R.id.cancel_mirror_settings);
        done = (Button) findViewById(R.id.done_mirror_settings);
        mirrorSettings = (ImageView) findViewById(R.id.change_mirror_settings);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MirrorSettings.this, HomeFragment.class);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MirrorSettings.this, HomeFragment.class);
                startActivity(intent);
            }
        });

    }



}
