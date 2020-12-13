import React from 'react';
import PropTypes from 'prop-types';

import './index.css';

const Competition = ({ id, name }) => {
  return (
    <div id={'Competition' + id} className="Competition">
      <span>{id}</span>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <span>{name}</span>
    </div>
  );
};

Competition.propTypes = {
  id: PropTypes.number,
  name: PropTypes.string,
};

export default Competition;
