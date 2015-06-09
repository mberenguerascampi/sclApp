package com.plusbits.social.interfaces;

import com.plusbits.social.models.Event;

import java.util.ArrayList;

/**
 * Created by Marc on 09/06/2015.
 */
public interface ApiListener {
    public void onGetEventsSuccess(ArrayList<Event> events);
    public void onRequestFail(String error);
}
