import React, { useEffect } from 'react';

import { HeaderContainer } from '../containers';
import { Profile } from '../components';
import { getSession } from '../services';

export default function (InputComponent) {
  function OAuthCheck(props) {
    useEffect(() => {
      getSession().then((res) => {
        console.log(res.data.kakao_id);
        const kakaoId = res.data.kakao_id;
        if (kakaoId === undefined) {
          window.location.href = 'http://localhost:8080/views/kakaoOAuth/';
        }
      });
    }, []);

    return (
      <>
        <HeaderContainer />
        <Profile nickname={'박경은'} />
        <InputComponent />
      </>
    );
  }
  return OAuthCheck;
}
