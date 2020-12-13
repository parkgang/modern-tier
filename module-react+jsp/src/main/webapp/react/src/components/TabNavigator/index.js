import React from 'react';
import { Link } from 'react-router-dom';

import './index.css';

const TabNavigator = () => {
  return (
    <div id="tabNavigator">
      <Link to={`/react/dist`}>
        <button>랭킹</button>
      </Link>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <Link to={`/competition`}>
        <button>경쟁</button>
      </Link>
    </div>
  );
};

export default TabNavigator;
