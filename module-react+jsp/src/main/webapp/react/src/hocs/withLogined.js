import React, { useEffect } from 'react';

import { Header, Profile, TabNavigator } from '../components';

import { USE_DOMAIN, getSession } from '../services';

export default function (InputComponent) {
  function OAuthCheck(props) {
    useEffect(() => {
      getSession().then((res) => {
        console.log(res.data.kakao_id);
        const kakaoId = res.data.kakao_id;
        if (kakaoId === undefined) {
          window.location.href = USE_DOMAIN + 'views/kakaoOAuth/';
        }
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
