package com.example.i851409.communityapp.service;

import com.example.i851409.communityapp.data.Channel;

/**
 * Created by I851409 on 3/9/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
