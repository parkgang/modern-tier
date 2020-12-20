import { combineReducers } from 'redux';

import User from './User';
import Friend from './Friend';

// 왼쪽으로 바인딩 되는 값이 "store.getState()"의 property으로 출력됩니다.
const reducers = combineReducers({
  user: User,
  friend: Friend,
});

export default reducers;
