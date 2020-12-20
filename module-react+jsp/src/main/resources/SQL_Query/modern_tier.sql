show databases;

create database modern_tier;

use modern_tier;

show tables;

CREATE TABLE user
(
    id                      INT(11) NOT NULL AUTO_INCREMENT,
    kakao_id                INT     NOT NULL UNIQUE,
    kakao_nickname          VARCHAR(20),
    kakao_email             VARCHAR(20),
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

select *
from user;

insert into user (kakao_id, kakao_nickname, kakao_email, kakao_profile_image_url, kakao_access_token,
                  kakao_refresh_token)
values (123, '닉네임', '이메일', '프로필 이미지', '엑세스 토큰', '리프레시 토큰');

delete
from user
where id = 1;

# 사용자 검색
select *
from user
where kakao_id = 007;
-- where kakao_nickname like '%경%';

# 사용자 수정
update user
set kakao_nickname='1',
    kakao_email='2',
    kakao_access_token='3',
    kakao_refresh_token='4'
where kakao_id = 1536694714;

# 사용자 롤 계정 등록 여부 체크
select riot_id
from user
where kakao_id = 1536694714;