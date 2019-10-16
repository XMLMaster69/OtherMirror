package com.example.othermirror.Database;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.othermirror.Configuration_model.ConfigFile;

import java.util.List;

public class ConfigRepository {
    private String json_file;
    private ConfigDao mConfigDao;
    private ConfigFile mConfig_file;
    private Context context;
    private List<ConfigFile> allConfigs;

    //Constructor that handles Database and initializes member variables
    public ConfigRepository(Application application){
        ConfigDatabase db = ConfigDatabase.getDatabase(application);
        mConfigDao = db.configDao();

        context = application.getApplicationContext();
    }

    public void insert(ConfigFile config_file){
        new insertAsyncTask().execute(config_file);
    }

    public ConfigFile update(ConfigFile configFile){
        new updateAsyncTask().execute(configFile);
        return configFile;
    }

    public ConfigFile remove(ConfigFile configFile){
        new removeAsyncTask().execute(configFile);
        return configFile;
    }


    private class insertAsyncTask extends AsyncTask<ConfigFile, Void, Void> {
        @Override
        protected Void doInBackground(final ConfigFile... config_files) {
            mConfigDao.insert(config_files[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("config"));
        }
    }


    private class updateAsyncTask extends AsyncTask<ConfigFile, Void, Void> {
        @Override
        protected Void doInBackground(final ConfigFile... config_files) {
            mConfigDao.update(config_files[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("config"));
        }
    }



    private class removeAsyncTask extends AsyncTask<ConfigFile, Void, Void> {
        @Override
        protected Void doInBackground(final ConfigFile... config_files) {
            mConfigDao.delete(config_files[0]);
            return null;
        }
    }

    private class getAllConfigfilesSync extends AsyncTask<Void, Void, List<ConfigFile>>{
        @Override
        protected List<ConfigFile> doInBackground(Void... voids) {
            allConfigs = mConfigDao.getAllConfigs();
            return null;
        }

        @Override
        protected void onPostExecute(List<ConfigFile> configFiles) {
            super.onPostExecute(configFiles);
            new getAllConfigfilesSync().execute();
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("config"));
        }
    }


}
