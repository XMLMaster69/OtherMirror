package com.example.othermirror.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.othermirror.Configuration_model.Config_file;

@Dao
public interface ConfigDao {
    @Insert
    void insert(Config_file json_file);

    @Update
    void update(Config_file json_file);

    @Delete
    void delete(Config_file json_file);

}
