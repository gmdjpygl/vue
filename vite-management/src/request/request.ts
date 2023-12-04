import axios from 'axios';
import Cookies from 'js-cookie';


const instance = axios.create({
    baseURL : 'http://localhost:9080/baseAdmin/',
    timeout: 10000
});

instance.interceptors.request.use(
    config=>{
        let token = Cookies.get("token");
        if(token){
            config.headers= config.headers||{};// config.headers为空时,赋值空对象
            config.headers.Authorization=token;
        }
        return  config;
    }, 
    err=>{
        Promise.reject(err);
    })

instance.interceptors.response.use(
    config=>{return config.data;},
    err=>{
        Promise.reject(err);
    })
    
export default instance;