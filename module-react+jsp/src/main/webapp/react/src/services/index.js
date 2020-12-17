import axios from 'axios';

import { USE_DOMAIN } from '../constants';

export const getSession = () => {
  return axios.get(USE_DOMAIN + '/api/v1/user/login');
};

export const request = (method, url, data) => {
  return axios({
    method,
    url: USE_DOMAIN + url,
    data,
  })
    .then((res) => res.data)
    .catch((err) => console.log(err));
};
