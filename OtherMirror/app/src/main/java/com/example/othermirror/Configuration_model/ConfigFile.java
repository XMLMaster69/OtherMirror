package com.example.othermirror.Configuration_model;

// this class is going to have to implement the Json shizzle

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "config_file")
public class ConfigFile implements Serializable {
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

    @SerializedName("Weight")
    @Expose
    public String weight;


    @SerializedName("Email")
    @Expose
    public String email;


    @SerializedName("Quotes")
    @Expose
    public String quotes;

    public ConfigFile(){
        name = "Dennis ";
        countrycode = "DK";
        city = "Bøvling";
        weight = "75";
        email = "denniskobberholm3@gmail.com";
        quotes = "dinmor";
    }

    /*public ConfigFile(String name, String countrycode, String city,
                        double height, double weight, String email, String quotes){
        this.name = name;
        this.countrycode = countrycode;
        this.city = city;
        this.weight = weight;
        this.email = email;
        this.quotes = quotes;
    } */

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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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

}
