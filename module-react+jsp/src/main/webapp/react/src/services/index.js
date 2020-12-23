import axios from 'axios';

import { USE_DOMAIN, KAKAO_JAVASCRIPT_KEY } from '../constants';

// kakao SDK 초기화
// eslint-disable-next-line no-undef
Kakao.init(KAKAO_JAVASCRIPT_KEY);
// eslint-disable-next-line no-undef
if (Kakao.isInitialized()) {
  console.log('정상적으로 kakao SDK가 초기화 되었습니다.');
} else {
  console.error('kakao SDK가 초기화되지 않았습니다.');
}

export const getSession = () => {
  return axios.get(USE_DOMAIN + '/api/v1/user/login');
};

export const request = (method, url, data) => {
  return axios({
    method,
    url: USE_DOMAIN + url,
    data,
  }).catch((err) => console.log(`services/request 에러: ${err}`));
};
