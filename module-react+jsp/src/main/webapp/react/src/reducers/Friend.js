import * as types from '../actions/ActionTypes';

const initialState = {
  isLoading: false,
  keyword: '',
  list: [
    {
      kakaoId: Number(null),
      nickname: '',
      profileImage: '',
      isFriend: false,
    },
  ],
};

export default (state = initialState, action) => {
  switch (action.type) {
    case types.SEARCHING_USER:
      return { ...state, isLoading: true };
    case types.SEARCH_USER:
      return {
        ...state,
        isLoading: false,
        keyword: action.nickname,
        list: action.payload.map((x) => ({
          kakaoId: x.kakaoId,
          nickname: x.nickname,
          profileImage: x.profileImage === undefined ? '/react/src/resources/img/kakaoTalk-default-profile.jpg' : x.profileImage,
          isFriend: x.isFriend,
        })),
      };
    // return { ...state, keyword: action.nickname, list: action.payload };
    default:
      return state;
  }
};
