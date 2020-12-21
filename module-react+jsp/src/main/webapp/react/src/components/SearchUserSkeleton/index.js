import React from 'react';
import ContentLoader from 'react-content-loader';

const SearchUserSkeleton = () => {
  return (
    <ContentLoader speed={0.5} width={240} height={50} backgroundColor="#f3f3f3" foregroundColor="#ecebeb">
      <circle cx="25" cy="25" r="15" />
      <rect x="50" y="15" width="125" height="20" />
      <rect x="185" y="15" width="50" height="20" />
    </ContentLoader>
  );
};

export default SearchUserSkeleton;
