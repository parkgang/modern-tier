package com.dto;

public class RankingDTO {
    private int kakao_id;
    private String kakao_nickname;
    private String kakao_profile_image_url;
    private String riot_id;
    private String riot_name;
    private int riot_summonerLevel;
    private String tier;
    private String rank;

    public RankingDTO() {
        this.kakao_id = 0;
        this.kakao_nickname = null;
        this.kakao_profile_image_url = null;
        this.riot_id = null;
        this.riot_name = null;
        this.riot_summonerLevel = 0;
        this.tier = null;
        this.rank = null;
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

    public String getKakao_profile_image_url() {
        return kakao_profile_image_url;
    }

    public void setKakao_profile_image_url(String kakao_profile_image_url) {
        this.kakao_profile_image_url = kakao_profile_image_url;
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

    public int getRiot_summonerLevel() {
        return riot_summonerLevel;
    }

    public void setRiot_summonerLevel(int riot_summonerLevel) {
        this.riot_summonerLevel = riot_summonerLevel;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
