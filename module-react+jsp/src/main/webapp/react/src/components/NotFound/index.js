import React from 'react';
import { Link } from 'react-router-dom';

import { PATH_ROOT } from '../../constants';

import './index.css';

const NotFound = () => {
  return (
    <div id="notFound">
      <div>
        <span>Not Found</span>
      </div>
      <div>
        <span>잘못된 요청입니다.</span>
      </div>
      <div>
        <Link to={PATH_ROOT}>
          <button>홈 화면으로 돌아가기</button>
        </Link>
      </div>
    </div>
  );
};

export default NotFound;
