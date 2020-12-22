create database modern_tier;

use modern_tier;

SHOW DATABASES;
SHOW TABLES;

CREATE TABLE user
(
    id                      INT(11) NOT NULL AUTO_INCREMENT,
    kakao_id                INT     NOT NULL UNIQUE,
    kakao_nickname          VARCHAR(20),
    kakao_email             VARCHAR(100),
    kakao_profile_image_url VARCHAR(100),
    kakao_access_token      VARCHAR(60),
    kakao_refresh_token     VARCHAR(60),
    riot_id                 VARCHAR(60) UNIQUE,
    riot_name               VARCHAR(20),
    riot_profileIconId      INT,
    riot_summonerLevel      INT,
    PRIMARY KEY (id, kakao_id)
);
drop table user;

CREATE TABLE friend
(
    id              INT(11) NOT NULL AUTO_INCREMENT,
    kakao_id        INT,
    friend_kakao_id INT     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (kakao_id) REFERENCES user (kakao_id) ON DELETE CASCADE
);
drop table friend;

