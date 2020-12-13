import * as types from './ActionTypes';

export const downCount = () => ({
  type: types.DOWN,
});

export const upCount = () => ({
  type: types.UP,
});

export const logoutUser = () => ({
  type: types.LOGOUT_USER,
});

export const unlinkUser = () => ({
  type: types.UNLINK_USER,
});
