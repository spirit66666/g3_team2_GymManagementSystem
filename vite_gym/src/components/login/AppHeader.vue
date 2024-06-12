<template>
    <el-container >
      <el-header style="margin: 0; padding: 0">
        <el-menu
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"

        >
          <el-menu-item index="0">
            <img
                style="width: 60px ; height: 60px; margin-right: 20px"
                src="../../assets/SchoolLogo.jpg"
                alt="Element logo"
            />
          </el-menu-item>
            <h1 id="title"  style="margin: 0; padding: 0" @click="regist1">GYM</h1>

            <el-menu class="container"
                     @select="selectItem1"
            >
              <el-menu-item index="/AppHome/first_page">首页</el-menu-item>
              <el-menu-item index="/AppHome/yuyue">预约</el-menu-item>
              <el-menu-item index="/AppHome/third_page">我的预约</el-menu-item>
              <el-menu-item index="/AppHome/second_page">更多信息</el-menu-item>


            </el-menu>
          <el-sub-menu index="2"
                       v-model="username"
                       class="flex-grow"
                       @select="selectItem1">

            <template #title>
              <h1 style="height: 100%; margin: 0; padding: 0;font-size: 25px;">
              {{this.username}}个人信息
              </h1></template>

            <el-menu-item style="margin-right: 0px"  @click="login"

                           v-model="tuichu"
            >{{tuichu}}</el-menu-item>
            <el-menu-item style="margin-right: 0px"@click="$router.push('/Appregister')" >注册</el-menu-item>
          </el-sub-menu>

        </el-menu>
      </el-header>
    </el-container>
  </template>
  
  <script>



  import Store from '../../components/store/store.js'
  import router from "../../components/tools/Router.js";
  import {ElMessage} from "element-plus";
  import {h} from "vue";

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
    computed: {
      LoggedIn() {
        console.log("LoggedIn", Store.state.LoggedIn);
        return Store.state.LoggedIn;

      },

      username() {
        console.log("username", Store.state.username);

        return Store.state.username;

      }

    },
    watch: {


    },
    methods: {
      loadData()
      {
      },
      adminlogin() {
        router.replace('/adminlogin')
      },

      selectItem(index) {
            this.$emit(index)
        },
      login() {
        if(Store.state.LoggedIn === "退出登陆"){
          ElMessage({
            message: h('p', { style: 'line-height: 1; font-size: 14px' }, [
              h('span', null, '用户'),
              h('i', { style: 'color: teal' }, Store.state.username,'已经退出登陆'),
            ]),
          })

          location.reload();
          Store.commit('setUsername', "","");
          Store.commit('setLoggedIn', "登陆");


          router.replace('/')}
        else{
        router.replace('/login')}
      },
      selectItem1(index) {
        router.push(index)
      },

    }

  };
  </script>
  <style scoped>
  #title {
    color: green;
    font-size: 50px;
    font-weight: bold;
    font-family: Georgia, "Times New Roman", Times, serif;

    position: relative; /* 设置菜单容器为相对定位 */
    right: 50px; /* 向右移动 */
    top: -10px;/* 向上移动 */
  }
  .flex-grow {
    flex-grow: 5;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: right;
  }
  #text {
    font-size: 20px;
  }
  .container {
    display: flex;
    justify-content: space-between;
    align-items: center;

    mode:"horizontal";
    background-color: white;
    text-color:black;
    font-size: 30px;
    font-weight: bold;
    font-family: Arial, Helvetica, sans-serif;
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

  .el-menu-item {
    margin-bottom: 30px; /* 设置菜单项之间的垂直间隙 */
    margin-right: 60px; /* 设置菜单项之间的水平间隙 */
    font-size: 25px;
    align-items: center;
    justify-content: center;
    display: flex;

  }




  </style>