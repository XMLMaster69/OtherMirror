package com.example.othermirror.Configuration_model;

// this class is going to have to implement the Json shizzle

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "config_file")
public class Config_file {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int ID;


    public String mJson_string;


    public Config_file(){
        mJson_string = "Json File";
    }

    public String getmJson_string() {
        return mJson_string;
    }

    public void setmJson_string(String mJson_string) {
        this.mJson_string = mJson_string;
    }
}
