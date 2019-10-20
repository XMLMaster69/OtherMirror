package com.example.othermirror;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.othermirror.Fragments.HomeFragment;

public class MirrorSettings extends AppCompatActivity {

    Button cancel;
    Button done;
    WebView mirrorSettings;

    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context
         * @param c*/
        WebAppInterface(MirrorSettings c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // For the transistion

        setContentView(R.layout.activity_mirror_settings);
        cancel = (Button) findViewById(R.id.cancel_mirror_settings);
        done = (Button) findViewById(R.id.done_mirror_settings);
        mirrorSettings = (WebView) findViewById(R.id.miwswebview);
        mirrorSettings.getSettings().setJavaScriptEnabled(true);
        mirrorSettings.loadUrl("file:///android_asset/MobDesigner.html"); //so design me maybe, and all the other girls, this is crazy

        mirrorSettings.addJavascriptInterface(new WebAppInterface(this), "Android");



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
