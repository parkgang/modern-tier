import React from 'react';
import { Link } from 'react-router-dom';

import { PATH_ROOT, PATH_COMPETITION } from '../../constants';

import './index.css';

const TabNavigator = () => {
  return (
    <div id="tabNavigator">
      <Link to={PATH_ROOT}>
        <button>랭킹</button>
      </Link>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <Link to={PATH_COMPETITION}>
        <button>경쟁</button>
      </Link>
    </div>
  );
};

export default TabNavigator;
