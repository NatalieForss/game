package com.example.Game;

public class Spel {
    private String name;
    private int Id;
    private boolean status;
    private String place;
    private String typeOfExchange;
    private String category;

    public Spel(String name, int id, boolean status, String place, String typeOfExchange, String category) {
        this.name = name;
        Id = id;
        this.status = status;
        this.place = place;
        this.typeOfExchange = typeOfExchange;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTypeOfExchange() {
        return typeOfExchange;
    }

    public void setTypeOfExchange(String typeOfExchange) {
        this.typeOfExchange = typeOfExchange;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

