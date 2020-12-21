import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import { PATH_ROOT, PATH_COMPETITION } from '../../constants';

import './index.css';

const TabNavigator = ({ page }) => {
  return (
    <div id="tabNavigator">
      <ul>
        <Link to={PATH_ROOT}>
          <li className={page === 'RankingList' ? 'tab-select' : ''}>
            <span>랭킹</span>
          </li>
        </Link>
        <Link to={PATH_COMPETITION}>
          <li className={page === 'CompetitionList' ? 'tab-select' : ''}>
            <span>경쟁</span>
          </li>
        </Link>
      </ul>
    </div>
  );
};

TabNavigator.propTypes = {
  page: PropTypes.string,
};

export default TabNavigator;
