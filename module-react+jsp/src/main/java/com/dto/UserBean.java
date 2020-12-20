package com.dto;

public class UserBean {
    private int id;
    private int kakao_id;
    private String kakao_nickname;
    private String kakao_email;
    private String kakao_profile_image_url;
    private String kakao_access_token;
    private String kakao_refresh_token;
    private String riot_id;
    private String riot_name;
    private int riot_profileIconId;
    private int riot_summonerLevel;

    public UserBean() {
        this.id = 0;
        this.kakao_id = 0;
        this.kakao_nickname = null;
        this.kakao_email = null;
        this.kakao_profile_image_url = null;
        this.kakao_access_token = null;
        this.kakao_refresh_token = null;
        this.riot_id = null;
        this.riot_name = null;
        this.riot_profileIconId = 0;
        this.riot_summonerLevel = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKakao_id() {
        return kakao_id;
    }

    public void setKakao_id(int kakao_id) {
        this.kakao_id = kakao_id;
    }

    public String getKakao_nickname() {
        return kakao_nickname;
    }

    public void setKakao_nickname(String kakao_nickname) {
        this.kakao_nickname = kakao_nickname;
    }

    public String getKakao_email() {
        return kakao_email;
    }

    public void setKakao_email(String kakao_email) {
        this.kakao_email = kakao_email;
    }

    public String getKakao_profile_image_url() {
        return kakao_profile_image_url;
    }

    public void setKakao_profile_image_url(String kakao_profile_image_url) {
        this.kakao_profile_image_url = kakao_profile_image_url;
    }

    public String getKakao_access_token() {
        return kakao_access_token;
    }

    public void setKakao_access_token(String kakao_access_token) {
        this.kakao_access_token = kakao_access_token;
    }

    public String getKakao_refresh_token() {
        return kakao_refresh_token;
    }

    public void setKakao_refresh_token(String kakao_refresh_token) {
        this.kakao_refresh_token = kakao_refresh_token;
    }

    public String getRiot_id() {
        return riot_id;
    }

    public void setRiot_id(String riot_id) {
        this.riot_id = riot_id;
    }

    public String getRiot_name() {
        return riot_name;
    }

    public void setRiot_name(String riot_name) {
        this.riot_name = riot_name;
    }

    public int getRiot_profileIconId() {
        return riot_profileIconId;
    }

    public void setRiot_profileIconId(int riot_profileIconId) {
        this.riot_profileIconId = riot_profileIconId;
    }

    public int getRiot_summonerLevel() {
        return riot_summonerLevel;
    }

    public void setRiot_summonerLevel(int riot_summonerLevel) {
        this.riot_summonerLevel = riot_summonerLevel;
    }
}
