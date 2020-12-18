import React from 'react';
import { useDispatch } from 'react-redux';

import * as actions from '../../actions';
import { PATH_ROOT } from '../../constants';

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

  return (
    <div id="header">
      <a href={PATH_ROOT}>
        <img src="/src/resources/img/service-logo.jpg" />
      </a>
      <div className="burger-menu">
        <input id="burger-menu-toggle" type="checkbox" />
        <label
          htmlFor="burger-menu-toggle"
          onClick={() => {
            popUpController('burger-menu');
          }}
        >
          <img src="/src/resources/icon/burger-menu.svg" width="24px" />
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
          <img src="/src/resources/icon/add-friend.svg" width="24px" />
        </label>
        <div id="friend-list">
          <input type="text" placeholder="이름으로 검색" />
          <ul>
            <li>
              <img
                src="/src/resources/img/kakaoTalk-default-profile.jpg"
                width="30px"
                alt=""
              />
              <span>박경은</span>
              <div>
                <button>친구 추가</button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Header;
