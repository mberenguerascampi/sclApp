package com.plusbits.social.models;

import android.media.Image;
import android.util.Pair;

/**
 * Created by Marc on 09/06/2015.
 */
public class Event {
    private String name;
    private String date;
    private String description;
    private String location;
    private Pair<Double,Double> coordinates;
    private String imageURL;

    public Event(String name, String date, String description, String location, String imageURL) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.location = location;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Pair<Double, Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImage(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
