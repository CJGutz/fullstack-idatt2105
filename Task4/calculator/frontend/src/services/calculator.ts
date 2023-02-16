import axios from "axios";

export const postCalculation = (expression: string): Promise<String> => {
    return axios
        .post("http://localhost:8080/calculate/", expression)
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            return error.data;
        });
};
