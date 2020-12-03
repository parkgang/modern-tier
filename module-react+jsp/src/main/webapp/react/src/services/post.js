import axios from "axios";

const getPost = (postId) => {
  return axios.get("https://jsonplaceholder.typicode.com/posts/" + postId);
};

const getComments = (postId) => {
  return axios.get(
    `https://jsonplaceholder.typicode.com/posts/${postId}/comments`
  );
};

export { getPost, getComments };
