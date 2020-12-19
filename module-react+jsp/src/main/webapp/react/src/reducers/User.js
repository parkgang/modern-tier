import * as types from '../actions/ActionTypes';
import { USE_DOMAIN } from '../constants';

const initialState = {
  nickName: '로그인 되지 않은 사용자',
  profileImage: '',
};

export default (state = initialState, action) => {
  switch (action.type) {
    case types.LOGOUT_USER:
      location.href = USE_DOMAIN + '/api/v1/user/logout';
      return state;
    case types.UNLINK_USER:
      location.href = USE_DOMAIN + '/api/v1/user/unlink';
      return state;
    case types.LOGIN_USER:
      return {
        ...state,
        nickName: action.payload.nickName,
        profileImage:
          action.payload.profileImage === undefined
            ? '/react/src/resources/img/kakaoTalk-default-profile.jpg'
            : action.payload.profileImage,
      };
    default:
      return state;
  }
};
