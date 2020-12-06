import * as types from '../actions/ActionTypes';

const initialState = {
  tempNum: 0,
};

const Up = (state = initialState, action) => {
  if (action.type === types.UP) {
    return {
      ...state,
      tempNum: state.tempNum + 1,
    };
  } else {
    return state;
  }
};

export default Up;
