package com.example.othermirror.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.othermirror.Configuration_model.Config_file;
import com.example.othermirror.Database.ConfigDao;
import com.example.othermirror.Database.ConfigDatabase;
import java.lang.ref.WeakReference;


@Database(entities = {Config_file.class}, version = 1, exportSchema = false)
public abstract class ConfigDatabase extends RoomDatabase {

    public abstract  ConfigDao configDao(); // Dao object
    private static volatile ConfigDatabase INSTANCE; // Makeing my Databaes a singleton
    private static WeakReference<Context> weakcontext;

    static ConfigDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ConfigDatabase.class){
                if(INSTANCE == null){
                    //Create and get Database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ConfigDatabase.class, "config_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
