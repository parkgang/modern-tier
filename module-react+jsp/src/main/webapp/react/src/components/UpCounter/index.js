import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

import './index.css';

const UpCounter = ({ handleUpCount, data }) => {
  return (
    <Fragment>
      <div>{data}</div>
      <button onClick={handleUpCount}>올리기</button>
    </Fragment>
  );
};

UpCounter.propTypes = {
  handleUpCount: PropTypes.func,
  data: PropTypes.number,
};
UpCounter.defaultProps = {
  data: 0,
};

export default UpCounter;
