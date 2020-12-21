import * as types from './ActionTypes';
import { request } from '../services';

export const loginUser = async (kakaoId) => {
  const userProfile = await request('get', `/api/v1/user/profile?kakaoId=${kakaoId}`);
  return {
    type: types.LOGIN_USER,
    payload: userProfile.data,
  };
};

export const logoutUser = () => ({
  type: types.LOGOUT_USER,
});

export const unlinkUser = () => ({
  type: types.UNLINK_USER,
});

export const loadingUserProfile = () => ({
  type: types.LOADING_USER_PROFILE,
});

export const searchingUser = () => ({
  type: types.SEARCHING_USER,
});

export const searchUser = async (nickname) => {
  const userList = await request('get', `/api/v1/user/search?kakaoNickname=${nickname}`);
  return {
    type: types.SEARCH_USER,
    nickname,
    payload: userList.data,
  };
};

export const addFriend = async (kakaoId) => {
  const res = await request('get', `/api/v1/friend/add?friendKakaoId=${kakaoId}`);
  if (res.status === 200) {
    return {
      type: types.ADD_FRIEND,
      kakaoId,
    };
  }
};

export const delFriend = async (kakaoId) => {
  const res = await request('get', `/api/v1/friend/del?friendKakaoId=${kakaoId}`);
  if (res.status === 200) {
    return {
      type: types.DEL_FRIEND,
      kakaoId,
    };
  }
};

export const loadingRanking = () => ({
  type: types.LOADING_RANKING,
});

export const reqRanking = async () => {
  const ranking = await request('get', `/api/v1/riot/ranking`);
  return {
    type: types.REQUEST_RANKING,
    payload: ranking.data,
  };
};
