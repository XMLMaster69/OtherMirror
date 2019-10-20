package com.example.othermirror.Configuration_model;

// this class is going to have to implement the Json shizzle

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "config_file")
public class ConfigFile {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int ID;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Country Code")
    @Expose
    public String countrycode;

    @SerializedName("City")
    @Expose
    public String city;


    @SerializedName("Height")
    @Expose
    public double height;

    @SerializedName("Weight")
    @Expose
    public double weight;


    @SerializedName("Email")
    @Expose
    public String email;


    @SerializedName("Quotes")
    @Expose
    public String quotes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public ConfigFile(){
        name = "dennis";
        countrycode = "dk";
        city = "aarhus";
    }
}
