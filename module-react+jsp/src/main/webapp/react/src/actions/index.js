import * as types from './ActionTypes';

export const downCount = () => ({
  type: types.DOWN,
});

export const upCount = () => ({
  type: types.UP,
});

export const loginUser = (kakaoId) => {
  console.log('loginUser:' + kakaoId);
  // action에서 해당 id값에 해당하는 db 레코드 json으로 반환
  // 반환된 json으로 카카오 api 호출
  // 위의 모든 값은 리듀서로 전송
  // return이 없으면 redux가 트리거 되지 않아서 정상적으로 렌더링 되지 않습니다.
  return {
    type: types.LOGIN_USER,
  };
};

export const logoutUser = () => ({
  type: types.LOGOUT_USER,
});

export const unlinkUser = () => ({
  type: types.UNLINK_USER,
});
