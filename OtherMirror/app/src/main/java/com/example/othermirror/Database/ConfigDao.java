package com.example.othermirror.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.othermirror.Configuration_model.ConfigFile;
import java.util.List;
@Dao
public interface ConfigDao {
    @Insert
    void insert(ConfigFile json_file);

    @Update
    void update(ConfigFile json_file);

    @Delete
    void delete(ConfigFile json_file);

    @Query("SELECT * FROM config_file")
    List<ConfigFile> getAllConfigs();


}
