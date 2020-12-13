import React from 'react';

import './index.css';

const Profile = ({ profile_image_url, nickname }) => {
  return (
    <div>
      <img
        width="100"
        src="http://k.kakaocdn.net/dn/VKqEW/btqPPDh1mAM/UrV6GV7xIdlJKGifqhgUbk/img_640x640.jpg"
        alt="kakao 프로필 사진"
      />
      <div>{nickname}</div>
    </div>
  );
};

export default Profile;
