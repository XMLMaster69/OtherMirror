package com.example.othermirror.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.othermirror.Models.UserSettings;
//import com.example.othermirror.config_parse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    ImageView mirror_settings_img;
    ImageView youtube_img;
    ImageView BT_conf_img;
    JSONObject obj;
    private ConfigRepository configRepository;
    ConfigFile configFile;
    private Context context;
    private TextView json_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        youtube_img = view.findViewById(R.id.youtube_img_click);
        mirror_settings_img = view.findViewById(R.id.mirror_settings);
        BT_conf_img = view.findViewById(R.id.BT_send_configurations_img);
        json_txt = view.findViewById(R.id.Gson);
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

        //-------Testing JSON ----------------------------------------------
        try{
            JSONObject obj = new JSONObject(loadJSONFromAssets());
            JSONArray obj_array1 = obj.getJSONArray("UserSettings");
            JSONArray obj_array2 = obj.getJSONArray("MirrorSettings");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();

            Log.d("jsonTAG", "THE OBJECT: " + obj);
            Log.d("jsonTAG","THE usersettings: " + obj_array1);
            Log.d("jsonTAG", "THE mirrosettings" + obj_array2);

            HashMap<String, String> m_li;
            for(int i = 0; i < obj_array1.length(); i++){

                // This is where the values are showed...
                JSONObject jo_inside = obj_array1.getJSONObject(i);
                String name_value = jo_inside.getString("Name");
                String age_value = jo_inside.getString("Age");
                String city_val = jo_inside.getString("City");


                Log.d("jsonTAG" , "Name: " + name_value);
                Log.d("jsonTAG" , "Age: " + age_value);
                Log.d("jsonTAG" , "City: " + city_val);

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("Name", name_value);
                m_li.put("Age", age_value);
                m_li.put("Country", city_val);

                formList.add(m_li); // Adding to arraylist

                Log.d("jsonTAG", "Arraylist: " + m_li);
                jo_inside.put("City", "Lunderskov");
                ((JSONObject)obj_array1.get(i)).put("City", "Lunderskov");

                if(jo_inside.getString("Name").equals("Dennis")){
                    ((JSONObject) obj_array1.get(i)).put(city_val, "Lunderskov");
                }
                Log.d("jsonTAG", "change name:" + city_val);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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


    public String loadJSONFromAssets(){
        String jsonString = null;
        try{
            InputStream iStream = getActivity().getAssets().open("Json_Config.txt");
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();
            jsonString = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}
