<template>

  <div id="title">
    <h1>管理员GYM管理系统</h1>
  </div>
  <div class="input">
    <el-input v-model="loginUsername" placeholder="请输入用户名"></el-input>
  </div>
  <div class="input">
    <el-input v-model="loginPassword" placeholder="请输入密码" auto-complete="new-password" show-password></el-input>
  </div>

  <div class="input">
    <el-button @click="loginadmin" class="btn2"   >登录</el-button>
    <el-button @click="backOut2"   class="btn2"   >返回</el-button>
  </div>


</template>

<script>

import Store from "../store/store.js";

export default {
  data() {
    return {
      tableData: [],
      pageNumber: 1,
      pageSize: 2,
      total: 0,
      registerUsername: '',
      registerPassword: '',
      loginUsername: '',
      loginPassword: ''
    };
  },

  methods: {



    backOut2() {
      this.$router.push('/');},//返回按钮

    loginadmin() {

          this.$http.post('/adminlogin', {
            username: this.loginUsername,
            password: this.loginPassword
          }).then((response) => {
            console.log(response.data.code);
            if (response.data.code === 200) {
              console.log('Login response:', response.data); // Check the response data
              localStorage.setItem('Adtoken', response.data.data); // Store the token
              console.log('Token stored in local storage:', localStorage.getItem('Adtoken')); // Check if the token is stored


              Store.commit('setAdminname', this.loginUsername);
              this.$router.push({path: '/home'})

              this.$http.get('/getadmin/'+this.loginUsername).then((response) => {
                console.log(response.data[0].adminID);
                Store.commit('setAdminID',response.data[0].adminID );
              });
            }
          }).catch(() => {
            console.log("用户名或密码错误！");
            this.$message.error("用户名或密码错误！");
          });


    },}
};
</script>

<style scoped>
#container {
  background: black;
  height: 100%;
  width: 100%;
  position: absolute;
}

.error-message {
  color: red;
  margin-top: 5px;
}

#title {
  text-align: center;
  color: #152ade;
  margin-top: 200px;
}

.input {
  margin: 20px auto;
  width: 500px;
}
#el-icon-user {
  color: red;
}
.el-icon-lock {
  color: #13c2c2;
}


.btn2 {
  border-radius: 10px;
  height: 40px;
  width: 500px;
  background-color: deepskyblue;
  border-color: blue;
  color: white;
  display: block;
  margin: 5px;
  margin-top: 50px;
  margin-bottom: 50px;
}

</style>