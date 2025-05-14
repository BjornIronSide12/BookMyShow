package com.bookmyshow.models;

public class Region {
    private int id;
    private String name;
    private List<Theatre> theatres;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }
}
