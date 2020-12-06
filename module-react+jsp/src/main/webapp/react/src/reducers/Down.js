import * as types from '../actions/ActionTypes';

const initialState = {
  num: 0,
};

const Down = (state = initialState, action) => {
  if (action.type === types.DOWN) {
    return {
      ...state,
      num: state.num - 1,
    };
  } else {
    return state;
  }
};

export default Down;
