package com.example.othermirror.Database;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.othermirror.Configuration_model.Config_file;

public class ConfigRepository {
    private String json_file;
    private ConfigDao mConfigDao;
    private Config_file mConfig_file;
    private Context context;

    //Constructor that handles Database and initializes member variables
    public ConfigRepository(Application application){

        ConfigDatabase db = ConfigDatabase.getDatabase(application);
        mConfigDao = db.configDao();

        context = application.getApplicationContext();
    }

    public void insert(Config_file config_file){
        new insertAsyncTask().execute(config_file);
    }



    private class insertAsyncTask extends AsyncTask<Config_file, Void, Void> {
        @Override
        protected Void doInBackground(final Config_file... config_files) {
            mConfigDao.insert(config_files[0]);
            return null;
        }
    }


    private class updateAsyncTask extends AsyncTask<Config_file, Void, Void> {
        @Override
        protected Void doInBackground(final Config_file... config_files) {
            mConfigDao.update(config_files[0]);
            return null;
        }
    }



    private class remove extends AsyncTask<Config_file, Void, Void> {
        @Override
        protected Void doInBackground(final Config_file... config_files) {
            mConfigDao.delete(config_files[0]);
            return null;
        }
    }


}
