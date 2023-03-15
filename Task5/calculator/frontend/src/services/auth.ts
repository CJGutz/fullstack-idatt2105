import axios from "axios"

export const registerUser = async (username: string, password: string) => {
    return await axios
        .post("http://localhost:8080/register", { username: "admin", password: "admin" })
        .then((response) => {
            console.log(response.data)
        })
        .catch((error) => {
            console.log(error.response.data)
        })
}

export const retrieveNewToken = async (username: string, password: string): Promise<string|null> => {
    return await axios
        .post("http://localhost:8080/token", { username: "admin", password: "admin" })
        .then((response) => {
            console.log(response.data)
            return response.data
        })
        .catch((error) => {
            console.log(error.response.data)
        })
}

