import React from 'react';
import ContentLoader from 'react-content-loader';

const ProfileSkeleton = () => {
  return (
    <ContentLoader viewBox="0 0 1331 225">
      <rect x="590.5" y="10" rx="40" ry="40" width="150" height="150" />
      <rect x="610.5" y="170" width="110" height="25" />
    </ContentLoader>
  );
};

export default ProfileSkeleton;
