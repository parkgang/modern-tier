import axios from 'axios';

const DOMAIN = 'http://localhost:8080/';

export const getSession = () => {
  return axios.get(DOMAIN + 'api/v1/user/login');
};
