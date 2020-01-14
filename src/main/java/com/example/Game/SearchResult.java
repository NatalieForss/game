package com.example.Game;

public class SearchResult {
    private String gameName;
    private String typeOfExchange;
    private String location;
    private UserInfo userInfo;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTypeOfExchange() {
        return typeOfExchange;
    }

    public void setTypeOfExchange(String typeOfExchange) {
        this.typeOfExchange = typeOfExchange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
