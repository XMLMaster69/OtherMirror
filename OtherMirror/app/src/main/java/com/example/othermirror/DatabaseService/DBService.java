package com.example.othermirror.DatabaseService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.othermirror.Configuration_model.ConfigFile;
import com.example.othermirror.Database.ConfigRepository;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DBService extends Service {
    ConfigFile configfile;
    ConfigRepository configRepository;

    private final IBinder ibinder = new DBService.Servicebinder();
    public boolean firstStart = false;

    @Override
    public IBinder onBind(Intent intent) {
        return ibinder;
    }

    public class Servicebinder extends Binder {
        public DBService getService(){
            return DBService.this;
        }
    }

    public DBService(){}

    @Override
    public void onCreate() {
        super.onCreate();

        configfile = new ConfigFile();
        configRepository = new ConfigRepository(this.getApplication());

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);

        firstStart = preferences.getBoolean("firstStart", true);
        if(firstStart){
            addOnce();
        }

        loadJSONFromAssets();
        String jsonString = loadJSONFromAssets();
        ConfigFileParsing(jsonString); //This is the GSON formatting with the JSON file loaded from assets.
        Log.d("JSON_PARSING", jsonString);

        Gson gson = new Gson();
        ConfigFile configFile = gson.fromJson(jsonString, ConfigFile.class);
        Log.d("config_json", String.valueOf(configFile));

    }

    // Makes the database add only 1 object in the entire lifetime of the app
    public void addOnce(){
        addConfigFile(configfile);
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
    public List<ConfigFile> getAllConfigs(){
        return configRepository.updateAllconfigs();
    }

    //Adding a configFile to your database
    public void addConfigFile(ConfigFile addconfigFile){
        configRepository.insert(addconfigFile);
    }


    //Updating an already existing configFile
    public ConfigFile updateConfigFile(ConfigFile updateConfigfile){
        configRepository.update(updateConfigfile);
        return updateConfigfile;
    }

    //Removing the configFile. This needs to be made
    // so the configfile is gonna be in the base form.
    public ConfigFile removeConfigFile(ConfigFile removeConfigfile){
        configRepository.remove(removeConfigfile);
        return removeConfigfile;
    }


    public String loadJSONFromAssets(){
        String jsonString = null;
        try{
            InputStream configInputStream = getResources().getAssets().open("Json_Config.txt");
            int size = configInputStream.available();
            byte[] buffer = new byte[size];
            configInputStream.read(buffer);
            configInputStream.close();
            jsonString = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }


    public ConfigFile ConfigFileParsing(String jsonString) {
        Gson gson = new Gson();
        ConfigFile configFile = gson.fromJson(jsonString, ConfigFile.class);
        return configFile;
    }
}
