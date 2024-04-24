<template>

<router-view></router-view>
  <el-container id="container">
      <el-header style="width: 100%" height="120px">
        <Header :items="navItems" v-on:selected="changeSelected"></Header>
      </el-header>

      <el-main>
        <Body/>

      </el-main>
      <el-footer>
        <div id="footer">{{ desc }}</div>
      </el-footer>

    </el-container>

  </template>
  <script>

  import router from "@/tools/Router";
  import Header from "./AppHeader.vue";
  import Body from "./AppBody.vue";
  import FM from "../../tools/FileManager.js"
  export default {
    name: "AppHome",
    beforeRouteEnter (to, from, next) {
      window.scrollTo(0, 0);
      next();
    },
    components: {
      Header: Header,
      Body: Body,
    },
    data() {
      return {

        desc: "这是一个基于Vue的GYm管理系统",
        navItems: FM.getAllTopic().map((item,ind)=>{
            return {
                index:ind,
                title:item
            }
        }),
        currentTopicIndex:0
      };
    },
    mounted() {
        FM.getPostContent('HTML专题','文本标签').then((res)=>{
            console.log(res)

          router.beforeEach((to, from, next) => {
            // 在跳转到新页面前执行一些操作
            // 可以在这里销毁当前页面

            next(); // 确保继续导航
          })
        })
    },
    methods: {
        changeSelected(index) {
            this.currentTopicIndex = index
        }
    },
    computed : {
        bodyItems() {
            return FM.getPosts(this.currentTopicIndex).map((item,ind)=>{
                return {
                index:ind,
                title:item
                }
            })
        }
    }
  };
  </script>
  <style scoped>
  #container {
    margin-left: 10px;
    margin-right: 10px;
    margin-top: 30px;
    height: 800px;
  }
  #footer {
    text-align: center;
    background-color: bisque;
    height: 40px;
    line-height: 40px;
    color: #717171;
  }
  </style>