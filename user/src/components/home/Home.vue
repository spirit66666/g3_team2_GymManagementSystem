<template>
    <el-container id="container">
        <el-aside width="250px">
            <el-container id="top">
                <img style="width:25px;height:25px;margin:auto;margin-right:0;" src="~@/assets/logo.png"/>
 
                <div style="margin:auto;margin-left:10px;color:#ffffff;font-size:17px">
                   GYM 后台管理
                </div>
            </el-container>
            <el-menu
            :default-active="$route.path"
            style="height:100%"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="selectItem">
            <el-sub-menu index="1">
                <template #title>
                <i class="el-icon-s-order"></i>
                <span>设施管理</span>
                </template>
                <el-menu-item index="/home/order/0">2</el-menu-item>
                <el-menu-item index="/home/order/1">1</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
                <template #title>
                <i class="el-icon-s-shop"></i>
                <span>设施管理</span>
                </template>
                <el-menu-item index="/home/goods/0">4</el-menu-item>

                <el-menu-item index="/home/goods/2">3</el-menu-item>
            </el-sub-menu>
            </el-menu>
        </el-aside>
        <el-main style="padding:0">
            <!-- 添加一个通用的头部 -->
            <el-header style="margin:0;padding:0;" height="80px">
                <el-container style="background-color:blanchedalmond;margin:0;padding:0;height:80px">
                    <div style="margin: auto;margin-left:100px"><h1>欢迎您登录后台管理系统，管理员用户！</h1></div>
                  <div style="margin: auto;margin-right:50px"><el-button type="primary" @click="logout1" v-model="route">{{name}}主页{{ getUsername }}</el-button></div>
                    <div style="margin: auto;margin-right:50px"><el-button type="primary" @click="logout" v-model="route">{{name}}注销{{ getUsername }}</el-button></div>
                </el-container>
            </el-header>
            <!-- 这里用来渲染具体的功能模块 -->
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
import router from "@/tools/Router";

export default {
    name: "home",

    methods: {
      logout1() {
        router.beforeEach((to, from, next) => {
          // 在跳转到新页面前执行一些操作
          // 可以在这里销毁当前页面

          next(); // 确保继续导航
        })

        this.$router.replace('/')
      },
        logout() {
          router.beforeEach((to, from, next) => {
            // 在跳转到新页面前执行一些操作
            // 可以在这里销毁当前页面

            next(); // 确保继续导航
          })

          this.$router.replace('/login')
        },
        selectItem(index) {
            this.$router.push(index)
        }
    },
}
</script>

<style scoped>
#container {
    height: 100%;
    width:100%;
    position: absolute;
}
#top {
    background-color:#545c64;
    margin-right:1px;
    text-align: center;
    height: 60px;
}

</style>