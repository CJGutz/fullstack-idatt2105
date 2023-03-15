import axios from "axios";

export const postCalculation = async (expression: string, token: string): Promise<Calculation> => {
  const headers = {
    Authorization: "Bearer " + token,
  };
  return await axios
    .post("http://localhost:8080/calculate", { expression: expression }, {headers: headers})
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      return error.data

    });
};

export interface Calculation {
  expression: string;
  result: string;
}

export const getHistory = async (token: string): Promise<Calculation[]> => {
  const headers = {
    Authorization: "Bearer " + token,
  };
  return await axios
    .get("http://localhost:8080/calculations", {headers: headers})
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      return error.data

    });
};