import * as types from '../actions/ActionTypes';

const initialState = {
  keyword: '',
  list: [
    {
      kakaoId: '',
      nickname: '',
      profileImage: '',
      isFriend: false,
    },
  ],
};

export default (state = initialState, action) => {
  switch (action.type) {
    case types.SEARCH_USER:
      return { ...state, keyword: action.payload };
    default:
      return state;
  }
};

/*
const temp = {
    kakaoId: '1',
    nickname: '2',
    profileImage: '3',
    isFriend: false,
  };
  return {
        ...state,
        friend: [...state.friend, temp],
      };
*/
