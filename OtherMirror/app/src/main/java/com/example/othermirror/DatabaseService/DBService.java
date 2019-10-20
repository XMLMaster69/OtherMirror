package com.example.othermirror.DatabaseService;

import android.app.Service;
import android.content.Intent;
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

public class DBService extends JobIntentService {
    ConfigFile configfile;
    ConfigRepository configRepository;
    private final IBinder ibinder = new DBService.Servicebinder();

    @Override
    public void onCreate() {
        super.onCreate();

        loadJSONFromAssets();

        new AsyncTask<Object, Void, List<ConfigFile>>(){
            @Override
            protected List<ConfigFile> doInBackground(Object... objects) {
                configfile = new ConfigFile();
                configRepository = new ConfigRepository(getApplication());
                return null;
            }

        }.execute();

        Gson gson = new Gson();
        String jsonString = loadJSONFromAssets();
        Moviejsonparse(jsonString);
        Log.d("JSON_PARSING", jsonString);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ibinder;
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

    }


    public class Servicebinder extends Binder {
        public DBService getService(){
            return DBService.this;
        }
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


    public ConfigFile Moviejsonparse(String jsonString) {
        Gson gson = new Gson();
        ConfigFile configFile = gson.fromJson(jsonString, ConfigFile.class);
        return configFile;
    }
}
