import axios from 'axios';

// const DOMAIN = 'http://localhost:8080/';
const DOMAIN = 'http://52.231.50.84/';

export const getSession = () => {
  return axios.get(DOMAIN + 'api/v1/user/login');
};
