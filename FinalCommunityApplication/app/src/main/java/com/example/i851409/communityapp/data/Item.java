package com.example.i851409.communityapp.data;

import org.json.JSONObject;

/**
 * Created by I851409 on 3/9/2016.
 */
public class Item implements JSONPopulator{
    private Condition condition;

    //Implementing the getter method
    public Condition getCondition(){
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
