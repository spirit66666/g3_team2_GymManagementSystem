<template>

  <div class="container" id="Application">
    <div class="container">
      <h1 class="title">创建你的账号</h1>

      <div  class="inputContainer">
        <div class="field">
          <label for="username">用户名</label>
          <input class="input" type="text" placeholder="请输入用户名" id="username" v-model="registerUsername" />


        </div>
        <div class="field">
          <label for="username">密码</label>
          <input class="input" type="password" placeholder="请输入密码" id="password" v-model="registerPassword" />



        </div>


        <div class="field">
          <label for="username">手机号</label>
          <input class="input" type="password" placeholder="请输入手机" id="password" v-model="registerphone" />



        </div>

        <div class="field">
          <label for="username">邮箱</label>
          <input class="input" type="password" placeholder="请输入邮箱" id="password" v-model="registeremail" />



        </div>
        <el-link type="primary" @click="$router.push('/admin-register')">管理员注册</el-link>

      </div>
      <button @click="registerUser" class="btn">创建账号</button>

    </div>
  </div>

</template>


<script>

import {ElMessage} from "element-plus";
import {h} from "vue";

export default {
  data() {
    return {

      registerUsername: "",
      registerPassword: "",

      registerphone: "",
      registeremail: "",
      receiveMsg:false
    }
  },
  computed:{
    name: {
      get() {
        return this.fields[0].model
      },
      set(value){
        this.fields[0].model = value
      }
    },
    email: {
      get() {
        return this.fields[1].model
      },
      set(value){
        this.fields[1].model = value
      }
    },
    password: {
      get() {
        return this.fields[2].model
      },
      set(value){
        this.fields[2].model = value
      }
    }
  },
  methods:{
    registerUser() {
      this.$http.post('/postuser', {
        "userName": this.registerUsername,
        "passWord": this.registerPassword,
        "mobilePhone": this.registerphone,
        "email": this.registeremail
      }).then(response => {

        console.log(response);



        ElMessage({
          message: h('p', { style: 'line-height: 1; font-size: 14px' }, [
            h('span', null, '用户'),
            h('i', { style: 'color: teal' }, this.registerUsername,'已经注册成功'),
          ]),
        })

        setTimeout(() => {

          this.$router.push('/login');
        }, 400)
      }).catch(error => {
        console.log(error);
        ElMessage({
          message: '注册失败，请检查输入信息',
          type: 'error'
        })
      })
    },
  }

}
</script>

<style scoped>
.container {
  margin:0 auto;
  margin-top: 70px;
  text-align: center;
  width: 300px;
}
.subTitle {
  color:gray;
  font-size: 14px;
}
.title {
  font-size: 45px;
}
.input {
  width: 90%;
}
.inputContainer {
  text-align: left;
  margin-bottom: 20px;
}
.subContainer {
  text-align: left;
}
.field {
  font-size: 14px;
}
.input {
  border-radius: 6px;
  height: 25px;
  margin-top: 10px;
  border-color: silver;
  border-style: solid;
  background-color: cornsilk;
}
.tip {
  margin-top: 5px;
  font-size: 12px;
  color: gray;
}
.setting {
  font-size: 9px;
  color: black;
}
.label {
  font-size: 12px;
  margin-left: 5px;
  height: 20px;
  vertical-align:middle;
}
.checkbox {
  height: 20px;
  vertical-align:middle;
}
.btn {
  border-radius: 10px;
  height: 40px;
  width: 300px;
  margin-top: 30px;
  background-color: deepskyblue;
  border-color: blue;
  color: white;
}
</style>
 