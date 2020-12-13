import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import './index.css';

const DownCounter = ({ handleDownCount, data }) => {
  return (
    <>
      <span>{data}</span>
      <br />
      <button onClick={handleDownCount}>내리기</button>
      <br />
      <Link to={`/test`}>
        <button>업 카운터 이동</button>
      </Link>
    </>
  );
};

DownCounter.propTypes = {
  handleDownCount: PropTypes.func,
  data: PropTypes.number,
};
DownCounter.defaultProps = {
  data: 0,
};

export default DownCounter;
