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
                src="~@/assets/logo.png"
                alt="Element logo"
            />
          </el-menu-item>
          <h1 id="title"  style="margin: 0; padding: 0">GYM</h1>
          <div class="flex-grow" />
          <el-sub-menu index="2"
                       @select="selectItem1">
            <template #title>个人信息</template>
            <el-menu-item @click="login">登录</el-menu-item>
            <el-menu-item @click="regist1">注册</el-menu-item>

          </el-sub-menu>
        </el-menu>

      </el-header>

      <el-main style="margin: 40px; padding: 0">

        <el-menu class="container"
        @select="selectItem1"
        >
            <el-menu-item index="/AppHome/zhuye/0">首页</el-menu-item>
          <el-menu-item index="/AppHome/reservations/0">预约</el-menu-item>
          <el-menu-item index="/AppHome/zhuye/1">更多信息</el-menu-item>
          <el-menu-item index="/AppHome/reservations/1">4</el-menu-item>

          <div >
            <div class="input">


              <el-avatar style="float:right; margin-right: 20px" @click="login">
                <img src="~@/assets/logo.png" alt="Element logo" />
              </el-avatar>
            </div>
          </div>

        </el-menu>

      </el-main>

    </el-container>

  </template>
  
  <script>

  import { ref } from 'vue'
  import router from "@/tools/Router";

  export default {
    name: "AppHeader",
    props: ["items"],

    methods: {
      regist1() {



        router.replace('/Appregister')
      },


        selectItem(index) {
            this.$emit(index)
        },
      login() {	 this.RouteState = false;
        this.$nextTick(()=>{
          this.RouteState = true;
        });
        router.replace('/login')
      },
      selectItem1(index) {
        this.$router.push(index)
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