import React from 'react';
import ContentLoader from 'react-content-loader';

const RankingSkeleton = () => {
  const tempStyle = {
    height: '100px',
  };

  return (
    <div style={tempStyle}>
      <ContentLoader viewBox="0 0 1331 100">
        <rect x="10" y="0" rx="10" ry="10" width="50" height="50" />
        <rect x="70" y="0" rx="10" ry="10" width="50" height="50" />
        <rect x="130" y="10" rx="10" ry="10" width="1200" height="30" />
      </ContentLoader>
    </div>
  );
};

export default RankingSkeleton;
