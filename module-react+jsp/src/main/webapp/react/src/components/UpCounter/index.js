import React, { Fragment } from 'react';

import * as actions from '../../actions';

import { useDispatch, useSelector } from 'react-redux';

import './index.css';

const counterSelector = (state) => state.upPage;

const UpCounter = () => {
  const dispatch = useDispatch();
  const { tempNum } = useSelector(counterSelector);

  return (
    <Fragment>
      <div>{tempNum}</div>
      <button onClick={() => dispatch(actions.upCount())}>올리기</button>
    </Fragment>
  );
};

export default UpCounter;
