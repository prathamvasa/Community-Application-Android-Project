package com.example.i851409.communityapp.data;

import org.json.JSONObject;

/**
 * Created by I851409 on 3/9/2016.
 */
public class Condition implements JSONPopulator{
    //private int code;
    private int temperature;
    private String description;

    //Implementing some getter methods
    //public int getCode(){
        //return code;
    //}

    public int getTemperature(){
        return temperature;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        //code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");
    }
}
