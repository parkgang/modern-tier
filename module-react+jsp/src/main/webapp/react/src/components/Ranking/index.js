import React from 'react';
import PropTypes from 'prop-types';

import './index.css';

const Ranking = ({ ranking, kakaoNickname, kakaoProfileImageUrl, riotName, riotProfileIconId, riotSummonerLevel, tier, rank }) => {
  const tierFileName = () => {
    switch (tier) {
      case 'CHALLENGER':
        return 'Emblem_Challenger.png';
      case 'GRANDMASTER':
        return 'Emblem_Grandmaster.png';
      case 'MASTER':
        return 'Emblem_Master.png';
      case 'DIAMOND':
        return 'Emblem_Diamond.png';
      case 'PLATINUM':
        return 'Emblem_Platinum.png';
      case 'GOLD':
        return 'Emblem_Gold.png';
      case 'SILVER':
        return 'Emblem_Silver.png';
      case 'BRONZE':
        return 'Emblem_Bronze.png';
      case 'IRON':
        return 'Emblem_Iron.png';
    }
  };

  console.log('반환 값' + tierFileName());

  return (
    <div id={'Ranking' + ranking} className="ranking">
      <span>{ranking + '.'}</span>
      <span>&nbsp;&nbsp;</span>
      <img src={kakaoProfileImageUrl} />
      <span>&nbsp;&nbsp;</span>
      <img src={'/react/src/resources/icon/ranked-emblems/' + tierFileName()} />
      <span>&nbsp;&nbsp;</span>
      <span>{tier + ' ' + rank}</span>
      <span>&nbsp;&nbsp;</span>
      <span>{kakaoNickname}</span>
      <span>&nbsp;&nbsp;</span>
      <span>{riotName}</span>
      <span>&nbsp;&nbsp;</span>
      <span>{riotSummonerLevel}</span>
    </div>
  );
};

Ranking.propTypes = {
  ranking: PropTypes.number,
  kakaoNickname: PropTypes.string,
  kakaoProfileImageUrl: PropTypes.string,
  riotName: PropTypes.string,
  riotProfileIconId: PropTypes.string,
  riotSummonerLevel: PropTypes.number,
  tier: PropTypes.string,
  rank: PropTypes.string,
};

export default Ranking;
