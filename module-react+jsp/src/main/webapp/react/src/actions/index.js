import * as types from './ActionTypes';
import { request } from '../services';

export const downCount = () => ({
  type: types.DOWN,
});

export const upCount = () => ({
  type: types.UP,
});

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

export const searchUser = (nickname) => ({
  type: types.SEARCH_USER,
  payload: nickname,
});
