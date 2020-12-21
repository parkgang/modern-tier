use modern_tier;

# 조회
select *
from friend;

# 삽입
insert into friend (kakao_id, friend_kakao_id)
values (1536694714, 1537406659);
insert into friend (kakao_id, friend_kakao_id)
values (1536694714, 1561304829);
insert into friend (kakao_id, friend_kakao_id)
values (1536694714, 1561343937);

insert into friend (kakao_id, friend_kakao_id)
values (1537406659, 1536897482);
insert into friend (kakao_id, friend_kakao_id)
values (1537406659, 1561304829);

# 친구 조회
select u.*
from friend
         inner join user u on friend.friend_kakao_id = u.kakao_id
where friend.kakao_id = 1536694714;

# 삭제
delete from friend;
delete from friend where kakao_id = 1536694714 and friend_kakao_id = 1561304829;