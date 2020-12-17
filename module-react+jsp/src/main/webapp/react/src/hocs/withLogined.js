import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';

import * as actions from '../actions';
import { Header, Profile, TabNavigator } from '../components';
import { getSession } from '../services';
import { USE_DOMAIN } from '../constants';

export default function (InputComponent) {
  function OAuthCheck(props) {
    const dispatch = useDispatch();
    useEffect(() => {
      getSession()
        .then((res) => {
          const kakaoId = res.data.kakao_id;
          console.log(`kakaoId: ${kakaoId}`);
          if (kakaoId === undefined) {
            window.location.href = USE_DOMAIN + '/views/kakaoOAuth/';
          } else {
            dispatch(actions.loginUser(kakaoId));
          }
        })
        .catch((err) => {
          console.error(
            `REST API Server가 비활성화되어 있을 확률이 높습니다: ${err}`
          );
          window.location.href = USE_DOMAIN + '/views/kakaoOAuth/';
        });
    }, []);

    return (
      <>
        <Header />
        <Profile />
        <div id="content">
          <TabNavigator />
          <InputComponent />
        </div>
      </>
    );
  }
  return OAuthCheck;
}
