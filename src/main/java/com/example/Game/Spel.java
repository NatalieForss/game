package com.example.Game;

public class Spel {
    private int id;
    private String name;
    private boolean status;
    private String location;
    private String typeOfExchange;
    private String category;
    private String userID;


    public Spel(int id, String name, boolean status, String location, String typeOfExchange, String category) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.location = location;
        this.typeOfExchange = typeOfExchange;
        this.category = category;
    }

    public Spel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Spel(String s) {
    }

    public Spel() {

    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLocation() {   
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}

