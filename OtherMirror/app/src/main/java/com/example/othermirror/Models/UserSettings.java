package com.example.othermirror.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSettings {
    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Age")
    @Expose
    public int age;

    @SerializedName("Country Code")
    @Expose
    public String countrycode;

    @SerializedName("City")
    @Expose
    public String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public UserSettings(){
        name = "dennis";
        countrycode = "dk";
        age = 23;
        city = "aarhus";
    }
}
