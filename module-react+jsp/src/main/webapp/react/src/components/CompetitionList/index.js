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
  const competitionList = competitionData.map((x, index) => <Competition key={index} id={x.id} name={x.name} />);

  return (
    <div id="competitionList">
      <div>
        <span>론칭 예정 입니다</span>
      </div>
      <div>
        <div>
          <span>실제 LOL 전적 데이터를 기반으로</span>
        </div>
        <div>
          <span>친구와 함께 경쟁을 할 수 있어요!</span>
        </div>
        <div>
          <span>경쟁에서 이긴 친구는 보상을 받는 시스템 입니다.</span>
        </div>
        <div>
          <span>추후, 업데이트 예정이니 기대해 주세요!</span>
        </div>
      </div>
    </div>
  );
};

export default CompetitionList;
