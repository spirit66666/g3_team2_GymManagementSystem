
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import BaiduMap from 'vue-baidu-map'//百度地图

import Store from './components/store/store.js'
import App from './App.vue'


import axios from 'axios'
import router from "./components/tools/Router.js";
axios.defaults.baseURL = 'http://localhost:9990'

const app = createApp(App)



app.config.globalProperties.$http = axios
axios.interceptors.request.use(
    config => {
        //先从浏览器的回话存储中提取token值
        const tokenStr = localStorage.getItem('token')
        if (tokenStr) {
            config.headers.Authorization=tokenStr
        }
        return config
    })
//响应拦截器
axios.interceptors.response.use(
    response => {
        //如果reponse里面的status是200，说明访问到接口了，否则错误
        console.log(response);
        // if (response.data.state==false){
        //   // alert("账号信息已过期，请重新登录");
        //   location.href = '/'
        //   localStorage.removeItem("token");
        // }
        if(response.status == 200){
            return Promise.resolve(response);
        }else{
            return Promise.reject(response);
        }

    },
    error => {
        if(error.response.status){
            console.log(error);
            switch(error.response.status){
                case 401:       //未登录
                    router.replace({
                        path:'/',
                        query:{
                            redirect: router.currentRoute.fullPath
                        }
                    });
                    break;
                case 404:   //没找到
                    break;
            }
            return Promise.reject(error.response);
        }
    }
);

/**
 * 封装get方法
 */
export function get(url,params={}){
    return new Promise((resolve,reject) => {
        axios.get(url,{params:params})
            .then(response =>{
                console.log(response.data);
                if (response.data.state==false){
                    // alert("账号信息已过期，请重新登录");
                    // location.href = '/'
                    // this.notify("账号信息已过期，请重新登录","false");
                }
                resolve(response.data);
            })
            .catch(err =>{
                reject(err);
            })
    });
}

/**
 * 封装post方法
 */
export function post(url,data={}){
    return new Promise((resolve,reject) => {
        // axios.post(url,data,{headers:{'token': window.localStorage['token']}})
        axios.post(url,data,)
            .then(response =>{
                console.log(response.data);
                if (response.data.state==false){
                    // alert("账号信息已过期，请重新登录");
                    // location.href = '/'
                    // this.notify("账号信息已过期，请重新登录","false");
                }
                resolve(response.data);
            })
            .catch(err =>{
                reject(err);
            })
    });
}



app.use(router)

app.use(Store)
app.use(ElementPlus)
app.mount('#app')

