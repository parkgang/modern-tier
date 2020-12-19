import * as types from './ActionTypes';
import { request } from '../services';

export const loginUser = async (kakaoId) => {
  const userProfile = await request(
    'get',
    `/api/v1/user/profile?kakaoId=${kakaoId}`
  );
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

export const searchingUser = () => ({
  type: types.SEARCHING_USER,
});

export const searchUser = async (nickname) => {
  const userList = await request(
    'get',
    `/api/v1/user/search?kakaoNickname=${nickname}`
  );
  return {
    type: types.SEARCH_USER,
    nickname,
    payload: userList.data,
  };
};
