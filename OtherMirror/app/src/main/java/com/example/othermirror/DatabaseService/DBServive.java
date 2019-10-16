package com.example.othermirror.DatabaseService;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.example.othermirror.Configuration_model.ConfigFile;
import com.example.othermirror.Database.ConfigRepository;

import java.util.List;

public class DBServive extends Service {
    //private final IBinder ibinder = new Servicebinder();
    ConfigFile configfile;
    ConfigRepository configRepository;

    @Override
    public void onCreate() {
        super.onCreate();


        new AsyncTask<Object, Void, List<ConfigFile>>(){
            @Override
            protected List<ConfigFile> doInBackground(Object... objects) {
                configfile = new ConfigFile();
                configRepository = new ConfigRepository(getApplication());
                return null;
            }

        }.execute();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class Servicebinder extends Binder {
        DBServive getService(){
            return DBServive.this;
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


}
