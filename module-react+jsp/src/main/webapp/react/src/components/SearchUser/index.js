import React from 'react';
import PropTypes from 'prop-types';

import './index.css';

const SearchUser = ({ kakaoId, nickname, profileImage, isFriend }) => {
  return (
    <div id="search-user">
      <img src={profileImage} alt="친구 프로필 사진이 없습니다" />
      <span>{nickname}</span>
      <div>{isFriend === false ? <button>친구 추가</button> : null}</div>
    </div>
  );
};

SearchUser.propTypes = {
  kakaoId: PropTypes.number,
  nickname: PropTypes.string,
  profileImage: PropTypes.string,
  isFriend: PropTypes.bool,
};
SearchUser.defaultProps = {
  kakaoId: 0,
  nickname: '',
  profileImage: '',
  isFriend: false,
};

export default SearchUser;
