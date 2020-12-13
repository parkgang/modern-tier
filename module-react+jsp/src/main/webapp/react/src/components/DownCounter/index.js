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
      <div>
        <a href="http://kyungeun.koreacentral.cloudapp.azure.com/api/v1/user/logout">
          로그아웃
        </a>
      </div>
      <div>
        <a href="http://kyungeun.koreacentral.cloudapp.azure.com/api/v1/user/unlink">
          탈퇴
        </a>
      </div>
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
