/* action 생성함수 (파라미터를 받아와서 액션 객체 형태 만들어 줌) */

import * as types from './ActionTypes';

export const downCount = () => ({
  type: types.DOWN,
});

export const upCount = () => ({
  type: types.UP,
});
