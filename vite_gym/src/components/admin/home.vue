<script>

import router from "../../components/tools/Router.js";
import Store from "../store/store.js";
import {ElMessage} from "element-plus";
import {h} from "vue";
export default {
  name: "home",

  data() {
    return {
      look: false,
    getadminusername: Store.state.adminname
    };
  },
  methods: {
    exit() {

        ElMessage({
          message: h('p', { style: 'line-height: 1; font-size: 14px' }, [
            h('span', null, '用户'),
            h('i', { style: 'color: teal' }, Store.state.username,'已经退出登陆'),
          ]),
        })

        location.reload();
        Store.commit('setAdminname', "","");


        router.replace('/home')

    },
    logout1() {
      router.beforeEach((to, from, next) => {
        // 在跳转到新页面前执行一些操作
        // 可以在这里销毁当前页面

        next(); // 确保继续导航
      })


      console.log(Store.state.username);
      this.$router.replace('/AppHome/first_page')
    },

    selectItem(index) {

      console.log(index);
      console.log(Store.state.username);
      this.$router.push(index)
    }
    ,

    deleteUser() {
      console.log(Store.state.adminID);
      this.$http.delete("/deleteadmin/" + Store.state.adminID )
      .then(res => {
         console.log(res.data.code);
         if (res.data.code === 200) {
           this.$message.success("删除成功");
         } else {
           this.$message.error("删除失败");
         }
      })
      this.look = false;
    }
  },

}
</script>

<template>


  <el-dialog v-model="look" title="真的注销账号吗？" width="500">
    <div style="text-align: right;">
      <el-button type="primary" @click="deleteUser()">
        注销
      </el-button>
    </div>
  </el-dialog>
  <el-container id="container">
    <el-aside width="200px">
      <el-container id="top">
        <img style="width:25px;height:25px;margin:auto;margin-right:0;" src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"/>

        <div style="margin:auto;margin-left:10px;color:deeppink;font-size:17px">
          GYM 后台管理
        </div>
      </el-container>
      <el-menu
          :default-active="$route.path"
          style="height:100%;width:100%"
          background-color= pink
          text-color="black"
          active-text-color="red"
          @select="selectItem">

        <el-sub-menu index="0" text-color= blue>
          <template #title>
            <i class="el-icon-s-order"></i>
            <span>用户管理</span>
          </template>
          <el-menu-item index="/home/manageuser">用户管理</el-menu-item>
          <el-menu-item index="/home/cheackreservationstate">用户预约管理</el-menu-item>

        </el-sub-menu>
        <el-sub-menu index="1" text-color= blue>
          <template #title>
            <i class="el-icon-s-order"></i>
            <span>设施管理</span>
          </template>
          <el-menu-item index="/home/addfacility">设施管理</el-menu-item>
        </el-sub-menu>

      </el-menu>
    </el-aside>
    <el-main style="padding:0">
      <!-- 添加一个通用的头部 -->
      <el-header style="margin:0;padding:0;" height="80px">
        <el-container style="background-color:blanchedalmond;margin:0;padding:0;height:80px">
        <div style="margin: auto;margin-left:100px"><h1>欢迎您登录后台管理系统，管理员用户名：{{ getadminusername }}!</h1></div>
          <div style="margin: auto;margin-right:50px"><el-button  class="custom-btn btn" @click="logout1" v-model="route">主页</el-button>

         <el-button  class="custom-btn btn" @click="$router.push('/adminlogin')">登陆</el-button>
            <el-button  class="custom-btn btn" @click="exit">退出登录</el-button>
          <el-button  class="custom-btn btn" @click="this.look = true" >注销</el-button></div>
        </el-container>
      </el-header>
      <!-- 这里用来渲染具体的功能模块 -->
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

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
/* From www.lingdaima.com */
.custom-btn {
  width: 130px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, .5),
  7px 7px 20px 0px rgba(0, 0, 0, .1),
  4px 4px 5px 0px rgba(0, 0, 0, .1);
  outline: none;
}

.btn {
  background: linear-gradient(0deg, rgba(255, 151, 0, 1) 0%, rgba(251, 75, 2, 1) 100%);
  line-height: 42px;
  padding: 0;
  border: none;
}

.btn span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}

.btn:before,
.btn:after {
  position: absolute;
  content: "";
  right: 0;
  bottom: 0;
  background: rgba(251, 75, 2, 1);
  box-shadow: -7px -7px 20px 0px rgba(255, 255, 255, .9),
  -4px -4px 5px 0px rgba(255, 255, 255, .9),
  7px 7px 20px 0px rgba(0, 0, 0, .2),
  4px 4px 5px 0px rgba(0, 0, 0, .3);
  transition: all 0.3s ease;
}

.btn:before {
  height: 0%;
  width: 2px;
}

.btn:after {
  width: 0%;
  height: 2px;
}

.btn:hover {
  color: rgba(251, 75, 2, 1);
  background: transparent;
}

.btn:hover:before {
  height: 100%;
}

.btn:hover:after {
  width: 100%;
}

.btn span:before,
.btn span:after {
  position: absolute;
  content: "";
  left: 0;
  top: 0;
  background: rgba(251, 75, 2, 1);
  box-shadow: -7px -7px 20px 0px rgba(255, 255, 255, .9),
  -4px -4px 5px 0px rgba(255, 255, 255, .9),
  7px 7px 20px 0px rgba(0, 0, 0, .2),
  4px 4px 5px 0px rgba(0, 0, 0, .3);
  transition: all 0.3s ease;
}

.btn span:before {
  width: 2px;
  height: 0%;
}

.btn span:after {
  height: 2px;
  width: 0%;
}

.btn span:hover:before {
  height: 100%;
}

.btn span:hover:after {
  width: 100%;
}
</style>