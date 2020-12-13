import React from 'react';
import PropTypes from 'prop-types';

import './index.css';

const Ranking = ({ id, name, tier }) => {
  return (
    <div id={'Ranking' + id} className="ranking">
      <span>{id}</span>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <span>{name}</span>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <span>{tier}</span>
    </div>
  );
};

Ranking.propTypes = {
  id: PropTypes.number,
  name: PropTypes.string,
  tier: PropTypes.string,
};

export default Ranking;
