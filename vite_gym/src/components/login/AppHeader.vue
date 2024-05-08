<template>
    <el-container style="margin: 0; padding: 0">
      <el-header style="margin: 0; padding: 0">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
        >
          <el-menu-item index="0">
            <img
                style="width: 80px ; height: 60px; margin-right: 20px"
                src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"
                alt="Element logo"
            />
          </el-menu-item>
          <h1 id="title"  style="margin: 0; padding: 0" @click="regist1">GYM</h1>
          <div class="flex-grow" />
          <el-sub-menu index="2"
                       @select="selectItem1">
            <template #title>{{this.username}}个人信息</template>
            <el-menu-item  @click="login">{{tuichu}}</el-menu-item>
            <el-menu-item @click="regist1" >注册</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-header>
      <el-main style="margin: 40px; padding: 0">
        <el-menu class="container"
        @select="selectItem1"
        >
            <el-menu-item index="/AppHome/first_page">首页</el-menu-item>
          <el-menu-item index="/AppHome/second_page">预约</el-menu-item>
          <el-menu-item index="/AppHome/first_page">更多信息</el-menu-item>
          <el-menu-item index="/home/addfacility">预约</el-menu-item>

          <div >

            <div class="input">

              <el-avatar style="float:right; margin-right: 20px" @click="adminlogin">
                <img src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif" alt="Element logo" />
              </el-avatar>
            </div>
          </div>

        </el-menu>
      </el-main>
    </el-container>
  </template>
  
  <script>

  import Store from '../../components/store/store.js'
  import router from "../../components/tools/Router.js";

  export default {
    name: "AppHeader",
    props: ["items"],
    data() {
      return {
        tuichu: Store.state.LoggedIn,
       username: Store.state.username,
        password: Store.state.userPassword,
      };
    },
    created() {

    },
    components : {
      Login: () => import("../../components/login/Login.vue"),
    },
    mounted() {
    },
    methods: {
      loadData()
      {
      },
      adminlogin() {
        router.replace('/adminlogin')
      },
      regist1() {
        router.replace('/Appregister')
      },
      selectItem(index) {
            this.$emit(index)
        },
      login() {
        router.replace('/login')
      },
      selectItem1(index) {
        router.push(index)
      }
    }

  };
  </script>
  <style scoped>
  #title {
    color: deeppink;
    font-size: 40px;
    font-weight: bold;
    font-family: Georgia, "Times New Roman", Times, serif;
  }
  .flex-grow {
    flex-grow: 1;
  }
  #text {
    font-size: 20px;
  }
  .container {
    display: flex;
    justify-content: space-between;
    align-items: center;

    mode:"horizontal";
    background-color: pink;
    text-color:blue;
  }

  .left {
    display: flex;
  }

  .right {
    display: flex;
    align-items: center;
  }

  /* 假设头像样式 */
  .avatar {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    margin-left: 10px;
  }
  </style>