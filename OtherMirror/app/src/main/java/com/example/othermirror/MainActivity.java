package com.example.othermirror;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.othermirror.Configuration_model.ConfigFile;
import com.example.othermirror.Fragments.HomeFragment;
import com.example.othermirror.Fragments.SettingsFragment;
import com.example.othermirror.Fragments.UserFragment;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.othermirror.Database.ConfigRepository;
import com.example.othermirror.DatabaseService.DBService;

public class MainActivity extends AppCompatActivity {
    ImageView mirror_settings;
    Button youtube_btn;
    ConfigFile configfile1;
    ConfigFile configfile2;
    ConfigRepository configRepository;
    public DBService dbService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        loadFragment(new HomeFragment());

        enableStethos();
        configfile1 = new ConfigFile();
        configfile2 = new ConfigFile();

        //Example on the repo
        //configRepository = new ConfigRepository(this.getApplication());
        //configRepository.remove(configfile1);
        //configRepository.remove(configfile2);

        if(dbService != null){
            Intent serviceIntent = new Intent(this, DBService.class);
            startService(serviceIntent);
        }

        if(isBound){
            Intent serviceIntent = new Intent(this, DBService.class);
            bindService(serviceIntent, dbConnection, Context.BIND_AUTO_CREATE);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
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
    protected void onStart() {
        super.onStart(); // Should start the service
        if(dbService == null){
            Intent serviceIntent = new Intent(this, DBService.class);
            startForegroundService(serviceIntent);
        }

        Log.d("register", "Brodcastrecevier starting");
        //LocalBroadcastManager.getInstance(this).registerReceiver(); // MAKE THIS LATER
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
           new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                   Fragment selectedFragmet = null;

                   switch (menuItem.getItemId()){
                       case R.id.nav_home:
                           selectedFragmet = new HomeFragment();
                           break;
                       case R.id.nav_settings:
                           selectedFragmet = new SettingsFragment();
                           break;
                       case R.id.nav_user:
                           selectedFragmet = new UserFragment();
                           break;
                   }

                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                           selectedFragmet).commit();
                   return true;
               }
           };


// Choose a fragment to start up with (and bottom navigation bar)
    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }

        return false;
    }


    private void enableStethos() {

           /* Stetho initialization - allows for debugging features in Chrome browser
           See http://facebook.github.io/stetho/ for details
           1) Open chrome://inspect/ in a Chrome browse
           2) select 'inspect' on your app under the specific device/emulator
           3) select resources tab
           4) browse database tables under Web SQL
         */
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                        Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(
                        Stetho.defaultInspectorModulesProvider(this))
                .build());

    }
}
