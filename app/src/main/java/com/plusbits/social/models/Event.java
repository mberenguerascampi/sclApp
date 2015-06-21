package com.plusbits.social.models;

import android.media.Image;
import android.util.Pair;

import java.io.Serializable;

/**
 * Created by Marc on 09/06/2015.
 */
public class Event implements Serializable {
    private String id;
    private String name;
    private String date;
    private String description;
    private String location;
    private Pair<Double,Double> coordinates;
    private String imageURL;

    public Event(String id, String name, String date, String description, String location, String imageURL) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
