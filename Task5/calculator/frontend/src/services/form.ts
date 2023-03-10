import axios from "axios";

export const postForm = (): Promise<boolean> => {
  return axios
    .post("http://localhost:8080/feedback/", {})
    .then(() => {
      return true;
    })
    .catch(() => {
      return false;
    });
};
