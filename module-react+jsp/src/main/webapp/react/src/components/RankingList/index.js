import React from 'react';

import Ranking from '../Ranking';

import './index.css';

const RankingList = () => {
  // 임시 변수
  const rankingData = [
    {
      id: 1,
      name: '박경은',
      tier: '챌린지',
    },
    {
      id: 2,
      name: '이인혁',
      tier: '골드1',
    },
    {
      id: 3,
      name: '전병수',
      tier: '골드2',
    },
  ];
  // 렌더링 변수
  const rankingList = rankingData.map((x, index) => (
    <Ranking key={index} id={x.id} name={x.name} tier={x.tier} />
  ));
  return <div id="RankingList">{rankingList}</div>;
};

export default RankingList;
