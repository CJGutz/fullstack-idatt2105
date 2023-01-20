import axios from "axios"

export const postForm = (): Promise<boolean> =>{
    return axios.post('http://localhost:3000/data/', {})
        .then(() => {
            return true

        }).catch(() => {
            return false

        })
}