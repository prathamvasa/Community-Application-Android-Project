package com.example.i851409.communityapp.data;

import org.json.JSONObject;

/**
 * Created by I851409 on 3/9/2016.
 */
public class Channel implements JSONPopulator{
    private Units units;
    private Item item;

    //Implementing the getter() methods
    public Units getUnits(){
        return units;
    }

    public Item getItem(){
        return item;
    }

    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));
    }
}
