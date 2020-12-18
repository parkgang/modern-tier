import React from 'react';
import { useDispatch } from 'react-redux';

import * as actions from '../../actions';
import { PATH_ROOT } from '../../constants';
import SearchUser from '../SearchUser';

import './index.css';

const Header = () => {
  const dispatch = useDispatch();

  // 레이어 팝업 설계의 문제로 생성된 함수 입니다. 다른 팝업이 활성화 되어있으면 종료합니다.
  const popUpController = (mode) => {
    let oppositePopUpId;
    if (mode === 'burger-menu') {
      oppositePopUpId = 'add-friend-toggle';
    } else {
      oppositePopUpId = 'burger-menu-toggle';
    }
    const toggle = document.getElementById(oppositePopUpId);
    if (toggle.checked === true) {
      toggle.click();
    }
  };
  // 임시 변수
  const friendData = [
    {
      kakaoId: 123123123,
      nickname: '박경은',
      profileImage: '/src/resources/img/kakaoTalk-default-profile.jpg',
      isFriend: false,
    },
    {
      kakaoId: 20001123,
      nickname: '친구1',
      profileImage: '/src/resources/img/kakaoTalk-default-profile.jpg',
      isFriend: true,
    },
  ];
  // 렌더링 변수
  const friendList = friendData.map((x, index) => (
    <SearchUser
      key={index}
      kakaoId={x.kakaoId}
      nickname={x.nickname}
      profileImage={x.profileImage}
      isFriend={x.isFriend}
    />
  ));

  return (
    <div id="header">
      <a href={PATH_ROOT}>
        <img src="/react/src/resources/img/service-logo.jpg" />
      </a>
      <div className="burger-menu">
        <input id="burger-menu-toggle" type="checkbox" />
        <label
          htmlFor="burger-menu-toggle"
          onClick={() => {
            popUpController('burger-menu');
          }}
        >
          <img src="/react/src/resources/icon/burger-menu.svg" width="24px" />
        </label>
        <div id="burger-menu-list">
          <ul>
            <li>
              <button onClick={() => dispatch(actions.logoutUser())}>
                로그아웃
              </button>
            </li>
            <li>
              <button onClick={() => dispatch(actions.unlinkUser())}>
                탈퇴
              </button>
            </li>
          </ul>
        </div>
      </div>
      <div className="add-friend">
        <input id="add-friend-toggle" type="checkbox" />
        <label
          htmlFor="add-friend-toggle"
          onClick={() => {
            popUpController('add-friend');
          }}
        >
          <img src="/react/src/resources/icon/add-friend.svg" width="24px" />
        </label>
        <div id="friend-list">
          <input
            type="text"
            placeholder="카카오톡 이름으로 검색"
            onChange={(e) => {
              dispatch(actions.searchUser(e.target.value));
            }}
          />
          <div>{friendList}</div>
        </div>
      </div>
    </div>
  );
};

export default Header;
