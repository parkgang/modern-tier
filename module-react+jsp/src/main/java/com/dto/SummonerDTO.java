package com.dto;

public class SummonerDTO {
    private String id;
    private String name;
    private int profileIconId;
    private int summonerLevel;

    public SummonerDTO() {
        this.id = null;
        this.name = null;
        this.profileIconId = 0;
        this.summonerLevel = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}
