import { createApp } from 'vue'
import Router from './tools/Router'
import APP from './APP.vue'
// 引入Element Plus模块
import ElementPlus from 'element-plus'
// 引入CSS样式
import 'element-plus/dist/index.css'

// 引入图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const axios = require('axios');
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据

const app = createApp(APP)
// 遍历ElementPlusIconsVue中的所有组件进行祖册
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    // 向应用实例中全局注册图标组件
    app.component(key, component)
}

app.config.globalProperties.$axios = axios
app.use(ElementPlus)
app.use(Router)
app.mount('#app')
