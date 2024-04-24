<template>


    <el-container style="height: 100%">
      <el-main style="padding:0; margin:50px">
        <router-view></router-view>



      </el-main>
    </el-container>
  </template>

<script>
import MarkDown from "./MarkDown.vue";
import FileManager from '../../tools/FileManager.js'
import axios from 'axios';
import mock from "@/mock/Mock";
export default {
  mounted () {
    // 组件挂载的时候，加载默认的首篇文章

  },
  // topic为当前选中的专题名称
  props: ["items","topic"],
  data() {
    return {
      // 在侧边栏上当前选中的文章
      currentIndex:0,
      // 文档的markdown内容
      content:"",
      userData: {
        name: '',
        pwd: '',
        email: '',
        phone: ''
      }
    }
  },
  components: {
    MarkDown: MarkDown,
  },
  created() {
    this.getUserData();

  },
  methods: {

    async getUserData() {
      const endpoint = 'https://localhost:9990/';

      const name = 'admin';
      const pwd = 'admin123';
      const email = 'admin@localhost';
      const phone = '12345678901';
      const requestData = {
        // 在这里添加您的请求数据，比如可能的身份验证信息
        // 这里假设请求的数据是用户名、密码、邮箱、手机号

        name: name,
        pwd: pwd,
        email: email,
        phone: phone,
      };

      try {
        const response = await axios.post(endpoint, requestData);
        const userData = response.data;
        this.userData.name = userData.name;
        this.userData.pwd = userData.pwd;
        this.userData.email = userData.email;
        this.userData.phone = userData.phone;
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    },





    selectItem(index) {
      this.currentIndex = index
    }
  },


  // 监听userData变化
  watch: {
    // 监听选中的文章变化
    currentIndex: function(val) {
        FileManager.getPostContent(this.topic, this.items[val].title).then((res)=>{
          this.content = res.data;
        })
    },
    // 监听选中的主题变化
    topic: function(val) {
        FileManager.getPostContent(val, this.items[this.currentIndex].title).then((res)=>{
          this.content = res.data;
        })
    }
  },


};
</script>
<style scoped>
.el-menu-item is-active {
background-color: darkorange !important;
}
.container {
  margin: 50px;
}
.content {
  margin: 20px;
}
.tab {
  width: 300px;
  position: absolute;
}
.item {
  border: gray 1px solid;
  width: 148px;
  text-align: center;
  transition: all 0.8s ease;
  display: inline-block;
}
.list-enter-active {
  transition: all 1s ease;
}

.list-enter-from,
.list-leave-to {
  opacity: 0;
}

.list-move {
  transition: transform 1s ease;
}
.list-leave-active {
  position: absolute;
  transition: all 1s ease;
}
</style>