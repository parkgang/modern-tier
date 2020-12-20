import React from 'react';

import './index.css';

const kakaoLinkSend = () => {
  Kakao.Link.sendDefault({
    objectType: 'text',
    text: '회원님이 Modern tier를 함께 사용하고 싶어 합니다!',
    link: {
      mobileWebUrl: '',
      webUrl: '',
    },
  });
};

const SearchUserNone = () => {
  return (
    <div id="search-user-none">
      <div>
        <span>검색된 사용자가 없습니다!</span>
      </div>
      <div>
        <div>서비스에 가입된 사용자만 친구 추가가 가능합니다.</div>
        <div>친구에게 초대를 권유해보는 건 어떨까요?</div>
      </div>
      <div>
        <button onClick={kakaoLinkSend}>
          <img src="/react/src/resources/img/kakaolink_btn_small.png" />
        </button>
      </div>
    </div>
  );
};

export default SearchUserNone;
