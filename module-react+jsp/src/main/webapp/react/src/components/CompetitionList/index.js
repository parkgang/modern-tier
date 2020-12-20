import React from 'react';

import { Competition } from '../';

import './index.css';

const CompetitionList = () => {
  // 임시 변수
  const competitionData = [
    {
      id: 1,
      name: 'bot1 vs bot2',
    },
    {
      id: 2,
      name: '학과 내전',
    },
    {
      id: 3,
      name: 'young blood vs old blood',
    },
  ];
  // 렌더링 변수
  const competitionList = competitionData.map((x, index) => (
    <Competition key={index} id={x.id} name={x.name} />
  ));
  return <div id="competitionList">{competitionList}</div>;
};

export default CompetitionList;
