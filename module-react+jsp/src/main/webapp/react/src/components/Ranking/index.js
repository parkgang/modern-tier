import React from 'react';
import PropTypes from 'prop-types';

import './index.css';

const Ranking = ({ key, ranking, kakaoNickname, kakaoProfileImageUrl, riotName, riotProfileIconId, riotSummonerLevel, tier, rank }) => {
  return (
    <div id={'Ranking' + key} className="ranking">
      <span>{ranking + '.'}</span>
      <span>&nbsp;&nbsp;</span>
      <img src={kakaoProfileImageUrl} />
      <span>&nbsp;&nbsp;</span>
      <img src="/react/src/resources/icon/ranked-emblems/Emblem_Platinum.png" />
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
  key: PropTypes.number,
  ranking: PropTypes.string,
  kakaoNickname: PropTypes.string,
  kakaoProfileImageUrl: PropTypes.string,
  riotName: PropTypes.string,
  riotProfileIconId: PropTypes.string,
  riotSummonerLevel: PropTypes.string,
  tier: PropTypes.string,
  rank: PropTypes.string,
};

export default Ranking;
