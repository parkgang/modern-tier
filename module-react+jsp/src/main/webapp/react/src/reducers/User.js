import * as types from '../actions/ActionTypes';
import { USE_DOMAIN } from '../constants';

const initialState = {
  profileImage: '',
  nickname: '',
};

export default (state = initialState, action) => {
  switch (action.type) {
    case types.LOGOUT_USER:
      location.href = USE_DOMAIN + '/api/v1/user/logout';
      return state;
    case types.UNLINK_USER:
      location.href = USE_DOMAIN + '/api/v1/user/unlink';
      return state;
    default:
      return state;
  }
};
