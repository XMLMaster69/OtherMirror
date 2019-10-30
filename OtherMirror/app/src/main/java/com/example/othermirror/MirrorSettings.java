package com.example.othermirror;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.othermirror.Configuration_model.ConfigFile;
import com.example.othermirror.DatabaseService.DBService;

import androidx.appcompat.app.AppCompatActivity;

import com.example.othermirror.adapters.GridMirrorAdapter;
import com.example.othermirror.util.Globals;

public class MirrorSettings extends AppCompatActivity {
    Button cancel;
    Button done;
    private ImageView widget_img;

    ConfigFile configFile;
    DBService dbService;
    boolean isBound;
    int position;
    GridView gridView;
    GridMirrorAdapter gridMirrorAdapter;

    int preGridIndex[] = {Globals.TIME_DRAWABLE_INDEX, Globals.WEATHER_DRAWABLE_INDEX , Globals.CALENDAR_DRAWABLE_INDEX,
            Globals.NONE_DRAWABLE_INDEX, Globals.NONE_DRAWABLE_INDEX ,Globals.NONE_DRAWABLE_INDEX, Globals.NONE_DRAWABLE_INDEX,
            Globals.NONE_DRAWABLE_INDEX, Globals.NONE_DRAWABLE_INDEX, Globals.NEWSPAPER_DRAWABLE_INDEX,
            Globals.WEIGHT_DRAWABLE_INDEX, Globals.QUOTES_DRAWABLE_INDEX,}; // Pre index if user hasn't set it yet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // For the transistion
        setContentView(R.layout.activity_mirror_settings);
        setTitle("Mirror Settings");

        //Starting the service
        if(dbService != null){
            Intent serviceIntent = new Intent(this, DBService.class);
            startService(serviceIntent);
            //startForegroundService(serviceIntent);
        }
        //handling the connection
        if(isBound){
            Intent serviceIntent = new Intent(this, DBService.class);
            bindService(serviceIntent, dbConnection, Context.BIND_AUTO_CREATE);
        }


        gridView = (GridView) findViewById(R.id.GridView);
        gridView.setNumColumns(3);
        gridMirrorAdapter = new GridMirrorAdapter(this, preGridIndex);
        gridView.setAdapter(gridMirrorAdapter);
        //gridView.getItemAtPosition(position);
        //gridView.setItemChecked(position, true);

        cancel = (Button) findViewById(R.id.cancel_mirror_settings);
        done = (Button) findViewById(R.id.done_mirror_settings);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           }
       });


    }




    private ServiceConnection dbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            dbService = ((DBService.Servicebinder)service).getService();
            Log.d("connected", "The databaseService has connected");
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dbService = null;
            Log.d("disconnected", "The database service has disconnected");
            isBound = false;
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(isBound) {
            unbindService(dbConnection);
            isBound = false;
        }
    }

}
