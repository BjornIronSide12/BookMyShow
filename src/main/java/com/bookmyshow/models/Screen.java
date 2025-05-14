package com.bookmyshow.models;


import java.util.List;

public class Screen {
    private int id;
    private String name;
    private List<Seat> seats;
    private List<Features> featuresList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Features> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<Features> featuresList) {
        this.featuresList = featuresList;
    }
}
