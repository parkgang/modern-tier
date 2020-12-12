show databases;

create database modern_tier;

use modern_tier;

show tables;

CREATE TABLE user
(
    id                  INT(11) NOT NULL AUTO_INCREMENT,
    kakao_id            INT     NOT NULL UNIQUE,
    kakao_nickname      VARCHAR(20),
    kakao_email         VARCHAR(20),
    kakao_access_token  VARCHAR(60),
    kakao_refresh_token VARCHAR(60),
    riot_id             VARCHAR(60) UNIQUE,
    riot_name           VARCHAR(20),
    riot_summonerLevel  INT,
    PRIMARY KEY (id, kakao_id)
);

drop table user;

select *
from user;

insert into user
values (null, 2, null, null, null, null, null, null, 1);

delete
from user
where id = 1;