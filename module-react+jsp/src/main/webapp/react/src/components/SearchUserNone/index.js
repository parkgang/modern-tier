import React from 'react';
import PropTypes from 'prop-types';

import './index.css';

const kakaoLinkSend = () => {
  // eslint-disable-next-line no-undef
  Kakao.Link.sendDefault({
    objectType: 'text',
    text: '회원님이 Modern tier를 함께 사용하고 싶어 합니다!',
    link: {
      mobileWebUrl: '',
      webUrl: '',
    },
  });
};

const SearchUserNone = ({ title, content1, content2 }) => {
  return (
    <div id="search-user-none">
      <div>
        <span>{title}</span>
      </div>
      <div>
        <div>{content1}</div>
        <div>{content2}</div>
      </div>
      <div>
        <button onClick={kakaoLinkSend}>
          <img src="/react/src/resources/img/kakaolink_btn_small.png" />
        </button>
      </div>
      <div>
        <span>버튼을 눌러 공유하기</span>
      </div>
    </div>
  );
};

SearchUserNone.propTypes = {
  title: PropTypes.string,
  content1: PropTypes.string,
  content2: PropTypes.string,
};

export default SearchUserNone;
