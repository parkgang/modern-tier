use modern_tier;

# 조회
select *
from user;

# 삽입
insert into user (kakao_id, kakao_nickname, kakao_email, kakao_profile_image_url, kakao_access_token,
                  kakao_refresh_token)
values (123, '닉네임', '이메일', '프로필 이미지', '엑세스 토큰', '리프레시 토큰');

# 삭제
delete
from user
where kakao_id = 1536694714;

# 검색
select *
from user
where kakao_id = 1536694714;
-- where kakao_nickname like '%경%';

# 수정
update user
set kakao_nickname='1',
    kakao_email='2',
    kakao_access_token='3',
    kakao_refresh_token='4'
where kakao_id = 1536694714;

# 롤 계정 등록 여부 체크
select riot_id
from user
where kakao_id = 1536694714;