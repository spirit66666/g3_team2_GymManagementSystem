
import axios from 'axios'
const login = axios.create({
    baseURL: 'http://localhost:9990/api',

})


export default login