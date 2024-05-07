<template>
  <el-container id="container">
      <el-header style="width: 100%" height="100px">
        <Header />
      </el-header>

      <el-main>
        <Body/>

      </el-main>
      <el-footer>
        <div id="footer" @click="currentTopic">{{ desc }}</div>
      </el-footer>

    </el-container>

  </template>
  <script>

  import Header from "./AppHeader.vue";
  import Body from "./AppBody.vue";
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

        desc: "关于我们 | 联系我们 | 隐私政策 | 法律声明",
      };
    },
    mounted() {
    },
    methods: {
      currentTopic() {
        this.$router.push("/home");
      }
    },
    computed : {
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