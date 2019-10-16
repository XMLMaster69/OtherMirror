package com.example.othermirror.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import com.example.othermirror.Database.ConfigRepository;
import com.example.othermirror.MirrorSettings;
import com.example.othermirror.R;
import com.example.othermirror.YoutubeSearcher;
import com.example.othermirror.Configuration_model.ConfigFile;

public class HomeFragment extends Fragment {
    ImageView mirror_settings_img;
    ImageView youtube_img;
    ImageView BT_conf_img;

    private ConfigRepository configRepository;
    ConfigFile configFile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        youtube_img = view.findViewById(R.id.youtube_img_click);
        mirror_settings_img = view.findViewById(R.id.mirror_settings);
        BT_conf_img = view.findViewById(R.id.BT_send_configurations_img);

        configFile = new ConfigFile();
        configFile.getmJson_string();

        configRepository = new ConfigRepository(getActivity().getApplication());

        youtube_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_youtube_img();
            }
        });
        mirror_settings_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { openMirrorSettingsActivity();
            }
        });
        BT_conf_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_send_conf();
            }
        });

        return view;
    }

   /* public void openYoutubeActivity(){
        Intent intent = new Intent(getActivity(), YoutubeSearcher.class);
        intent.putExtra("youtube", "open_youtube");
        startActivity(intent);
    } */

    public void openMirrorSettingsActivity(){
        Toast.makeText(getActivity(), "Opening Mirror configurations", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MirrorSettings.class);
        intent.putExtra("mirrorsettings", "open_mirror");
        startActivity(intent);
    }

    public void click_youtube_img(){
        //https://medium.com/@ishanfx/android-shared-element-activity-transition-9c2501cd79ec
        Toast.makeText(getActivity(), "Opening youtube searcher", Toast.LENGTH_SHORT).show();
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(), youtube_img, "imageShare"); // they share the same ID in XML file
        Intent intent = new Intent(getActivity(), YoutubeSearcher.class);
        startActivity(intent, activityOptionsCompat.toBundle());
    }

    public void click_send_conf(){

        configFile.setmJson_string("hey");
        Toast.makeText(getActivity(), "Sending configurations", Toast.LENGTH_SHORT).show();
        configRepository.update(configFile);
    }
}
