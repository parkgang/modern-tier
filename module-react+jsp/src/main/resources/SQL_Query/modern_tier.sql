show databases;

create database modern_tier;

use modern_tier;

CREATE TABLE user
(
    id             INT(11)      NOT NULL AUTO_INCREMENT,
    kakao_id       VARCHAR(100) NOT NULL,
    kakao_nickname VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);