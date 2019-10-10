package com.example.othermirror;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.othermirror.Fragments.HomeFragment;
import com.example.othermirror.Fragments.SettingsFragment;
import com.example.othermirror.Fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView mirror_settings;
    Button youtube_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        loadFragment(new HomeFragment());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
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
}
