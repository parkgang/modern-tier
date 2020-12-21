import React from 'react';

import { Ranking } from '../';

import './index.css';

const RankingList = () => {
  // 임시 변수
  const rankingData = [
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '롤 이름',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
    {
      ranking: 1,
      kakaoNickname: '박경은',
      kakaoProfileImageUrl: '/src/resources/img/kakaoTalk-default-profile.jpg',
      riotName: '집중하는 경은',
      riotProfileIconId: 4205,
      riotSummonerLevel: 139,
      tier: 'GOLD',
      rank: 'IV',
    },
  ];
  // 렌더링 변수
  const rankingList = rankingData.map((x, index) => (
    <Ranking
      key={index}
      ranking={x.ranking}
      kakaoNickname={x.kakaoNickname}
      kakaoProfileImageUrl={x.kakaoProfileImageUrl}
      riotName={x.riotName}
      riotProfileIconId={x.riotProfileIconId}
      riotSummonerLevel={x.riotSummonerLevel}
      tier={x.tier}
      rank={x.rank}
    />
  ));
  return <div id="rankingList">{rankingList}</div>;
};

export default RankingList;
