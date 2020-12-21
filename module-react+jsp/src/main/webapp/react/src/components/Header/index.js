import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import * as actions from '../../actions';
import { PATH_ROOT, USE_DOMAIN } from '../../constants';
import { SearchUser, SearchUserNone, SearchUserSkeleton } from '../';

import './index.css';

const friendSelector = (state) => state.friend;

const Header = () => {
  useEffect(() => {
    dispatch(actions.searchingUser());
    actions.searchUser('').then((res) => {
      dispatch(res);
    });
  }, []);

  const dispatch = useDispatch();

  const { isLoading, list } = useSelector(friendSelector);

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

  // 로딩 컴포넌트
  const loading = new Array(5).fill(1).map((x, index) => {
    return <SearchUserSkeleton key={index} />;
  });

  // 렌더링 변수
  const friendList = list.map((x, index) => (
    <SearchUser key={index} kakaoId={x.kakaoId} nickname={x.nickname} profileImage={x.profileImage} isFriend={x.isFriend} />
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
              <button onClick={() => (window.location.href = USE_DOMAIN + '/views/searchRiotAccount/')}>LOL 계정 변경</button>
            </li>
            <li>
              <button onClick={() => dispatch(actions.logoutUser())}>로그아웃</button>
            </li>
            <li>
              <button onClick={() => dispatch(actions.unlinkUser())}>탈퇴</button>
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
              dispatch(actions.searchingUser());
              actions.searchUser(e.target.value).then((res) => {
                dispatch(res);
              });
            }}
          />
          <div>{isLoading === true ? loading : !list.length ? <SearchUserNone /> : friendList}</div>
        </div>
      </div>
    </div>
  );
};

export default Header;
