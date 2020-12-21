import * as types from '../actions/ActionTypes';

const initialState = {
  isLoading: false,
  list: [{}],
};

export default (state = initialState, action) => {
  switch (action.type) {
    case types.LOADING_RANKING:
      return { ...state, isLoading: true };
    case types.REQUEST_RANKING:
      return {
        ...state,
        isLoading: false,
        list: action.payload.map((x) => ({
          kakao_id: x.kakao_id,
          kakao_nickname: x.kakao_nickname,
          kakao_profile_image_url:
            x.kakao_profile_image_url === undefined ? '/react/src/resources/img/kakaoTalk-default-profile.jpg' : x.kakao_profile_image_url,
          riot_id: x.riot_id,
          riot_name: x.riot_name,
          riot_summonerLevel: x.riot_summonerLevel,
          tier: x.tier,
          rank: x.rank,
        })),
      };
    default:
      return state;
  }
};
