import React from 'react';

import './index.css';

const Profile = () => {
  return (
    <div id="profile">
      <div>
        <img
          width="100"
          src="http://k.kakaocdn.net/dn/VKqEW/btqPPDh1mAM/UrV6GV7xIdlJKGifqhgUbk/img_640x640.jpg"
          alt="kakao 프로필 사진"
        />
      </div>
      <div>
        <span>테스트 이름</span>
      </div>
    </div>
  );
};

export default Profile;
