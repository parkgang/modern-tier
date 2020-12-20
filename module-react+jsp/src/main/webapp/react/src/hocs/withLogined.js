import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import * as actions from '../actions';
import { Header, Profile, ProfileSkeleton, TabNavigator } from '../components';
import { getSession } from '../services';
import { USE_DOMAIN } from '../constants';

const userSelector = (state) => state.user;

export default function (InputComponent) {
  function OAuthCheck(props) {
    const dispatch = useDispatch();
    const { isLoading } = useSelector(userSelector);

    useEffect(() => {
      getSession()
        .then((res) => {
          const kakaoId = res.data.kakao_id;
          console.log(`kakaoId: ${kakaoId}`);
          if (kakaoId === undefined) {
            window.location.href = USE_DOMAIN + '/views/kakaoOAuth/';
          } else {
            dispatch(actions.loadingUserProfile());
            actions.loginUser(kakaoId).then((result) => {
              dispatch(result);
            });
            // dispatch(actions.loginUser(kakaoId)); 와 동일합니다
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
        {isLoading === true ? <ProfileSkeleton /> : <Profile />}
        <div id="content">
          <TabNavigator />
          <InputComponent />
        </div>
      </>
    );
  }
  return OAuthCheck;
}
