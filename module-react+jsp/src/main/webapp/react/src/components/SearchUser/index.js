import React from 'react';
import PropTypes from 'prop-types';

import './index.css';

const SearchUser = ({ kakaoId, nickname, profileImage, isFriend }) => {
  return (
    <div id="search-user">
      <img src={profileImage} width="30px" alt="" />
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

export default SearchUser;
