import React from 'react';
import * as actions from '../../actions';
import { useDispatch } from 'react-redux';

import './index.css';

const Header = () => {
  const dispatch = useDispatch();

  return (
    <div id="Header">
      <button onClick={() => dispatch(actions.logoutUser())}>로그아웃</button>
      <button onClick={() => dispatch(actions.unlinkUser())}>탈퇴</button>
    </div>
  );
};

export default Header;
