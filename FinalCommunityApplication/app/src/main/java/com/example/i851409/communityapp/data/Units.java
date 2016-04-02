package com.example.i851409.communityapp.data;

import org.json.JSONObject;

/**
 * Created by I851409 on 3/9/2016.
 */
public class Units implements JSONPopulator{
    private String temperature;

    //Implementing the getter method
    public String getTemperature(){
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
