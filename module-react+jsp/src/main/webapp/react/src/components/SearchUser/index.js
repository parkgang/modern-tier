import React from 'react';
import PropTypes from 'prop-types';
import { useDispatch } from 'react-redux';

import * as actions from '../../actions';

import './index.css';

const SearchUser = ({ kakaoId, nickname, profileImage, isFriend }) => {
  const dispatch = useDispatch();

  return (
    <div id="search-user">
      <img src={profileImage} alt="친구 프로필 사진이 없습니다" />
      <span>{nickname}</span>
      <div>
        {isFriend === false ? (
          <button onClick={() => actions.addFriend(kakaoId).then((result) => dispatch(result))}>친구 추가</button>
        ) : (
          <button onClick={() => actions.delFriend(kakaoId).then((result) => dispatch(result))}>친구 삭제</button>
        )}
      </div>
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
