import axios from 'axios';

// const DOMAIN = 'http://localhost:8080/';
const DOMAIN = 'http://kyungeun.koreacentral.cloudapp.azure.com/';

export const getSession = () => {
  return axios.get(DOMAIN + 'api/v1/user/login');
};
