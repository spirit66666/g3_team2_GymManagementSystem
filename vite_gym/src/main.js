
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import Store from './components/store/store.js'
import App from './App.vue'


import axios from 'axios'
import router from "./components/tools/Router.js";
axios.defaults.baseURL = 'http://localhost:9990'
const app = createApp(App)





app.config.globalProperties.$http = axios

app.use(router)

app.use(Store)
app.use(ElementPlus)
app.mount('#app')