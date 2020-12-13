import axios from 'axios';

// eslint-disable-next-line no-unused-vars
const SERVER_DOMAIN = 'http://52.231.50.84/';
const LOCAL_DOMAIN = 'http://localhost:8080/';
export const USE_DOMAIN = LOCAL_DOMAIN;

export const getSession = () => {
  return axios.get(USE_DOMAIN + 'api/v1/user/login');
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
