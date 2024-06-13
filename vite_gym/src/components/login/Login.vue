<template>
    <div id="container">
        <div id="title">
            <h1>GYM管理系统</h1>
        </div>
        <div class="input">
            <el-input v-model="name" placeholder="请输入用户名"></el-input>
        </div>
        <div class="input">
            <el-input v-model="password" placeholder="请输入密码" auto-complete="new-password" show-password></el-input>
        </div>

        <div class="input">
            <el-button @click="login" class="btn3" style="width:500px" type="primary"  >登录</el-button>

            <el-button @click="backOut1" class="btn3" style="width:500px" type="primary"  >返回主页</el-button>
        </div>

      <div class="input">
      <el-link type="primary" @click="$router.push('/adminlogin')">管理员登陆</el-link>
        </div>
      <div v-if="showError" class="error-message">Invalid username or password</div>
    </div>
</template>
<script>
import Store from '../../components/store/store.js'
import { h } from 'vue'
import { ElMessage } from 'element-plus'
export default {
    name:"login",
  beforeRouteEnter (to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
    data() {
        return {
            name:"",
            password:"",
          showError: false
        }
    },
    mounted () {
        this.name = ""
        this.password = ""
    },
    computed: {

        disabled(){
            return this.name.length === 0 || this.password.length === 0;
        }
    },
    methods: {

      backOut1() {
        this.$router.push('/');
      },

        login() {

            this.$http.post('/userlogin', {
              username: this.name,
              password: this.password
            }).then((response) => {
              if (response.data.code === 200) {
                console.log('Login response:', response.data); // Check the response data
                localStorage.setItem('token', response.data.data); // Store the token
                console.log('Token stored in local storage:', localStorage.getItem('token')); // Check if the token is stored

                ElMessage({
                  message: h('p', { style: 'line-height: 1; font-size: 14px' }, [
                    h('span', null, '用户'),
                    h('i', { style: 'color: teal' }, this.name,'已经登陆成功'),
                  ]),
                })
                this.$http.post('/getuserid/'+this.name).then((response) => {


                  console.log('获取用户信息id');
                  console.log(response.data);
                  Store.commit('setUserID', response.data);
                })
                Store.commit('setUsername', this.name,this.password);
                Store.commit('setLoggedIn', "退出登陆");
                console.log(Store.state.userID+'登陆成功');
                // 登陆成功提示
                setTimeout(() => {

                  this.$router.push('/');
                }, 400)
              }
          else {
            console.log('登陆失败');
            // 登陆失败处理逻辑
            ElMessage({
              message: h('p', { style: 'line-height: 1; font-size: 14px' }, [
                h('span', null, '用户'),
                h('i', { style: 'color: teal' }, this.name,'登陆失败'),
              ]),
            })
            Store.commit('setLoggedIn', "登陆");

          }
        }).catch((error) => {
              console.log(error);
              this.$message.error("用户名或密码错误！");
              this.showError = true;
            });

        },



}
}
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
    color: azure;
    margin-top: 200px;
}

.input {
    margin: 30px auto;
    width: 500px;
}
#el-icon-user {
    color: red;
}
.el-icon-lock {
    color: #13c2c2;
}
.btn3 {
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