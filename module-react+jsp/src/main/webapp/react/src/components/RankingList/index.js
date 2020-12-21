import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import * as actions from '../../actions';
import { Ranking, RankingSkeleton } from '../';

import './index.css';

const rankingSelector = (state) => state.ranking;

const RankingList = () => {
  const dispatch = useDispatch();

  const { isLoading, list } = useSelector(rankingSelector);

  useEffect(() => {
    dispatch(actions.loadingRanking());
    actions.reqRanking().then((res) => {
      dispatch(res);
    });
  }, []);

  // 로딩 컴포넌트
  const loading = new Array(5).fill(1).map((x, index) => {
    return <RankingSkeleton key={index} />;
  });

  // 렌더링 변수
  const rankingList = list.map((x, index) => (
    <Ranking
      key={index}
      ranking={index + 1}
      kakaoNickname={x.kakao_nickname}
      kakaoProfileImageUrl={x.kakao_profile_image_url}
      riotName={x.riot_name}
      riotProfileIconId={x.riotProfileIconId}
      riotSummonerLevel={x.riot_summonerLevel}
      tier={x.tier}
      rank={x.rank}
    />
  ));
  return <div id="rankingList">{isLoading === true ? loading : rankingList}</div>;
};

export default RankingList;
