import React from 'react';
import { useSelector } from 'react-redux';

import './index.css';

const userSelector = (state) => state.user;

const Profile = () => {
  const { nickName, profileImage } = useSelector(userSelector);
  return (
    <div id="profile">
      <div>
        <img src={profileImage} alt="kakao 프로필 사진" />
      </div>
      <div>
        <span>{nickName}</span>
      </div>
    </div>
  );
};

export default Profile;
