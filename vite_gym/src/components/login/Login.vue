<template>
    <div id="container">
        <div id="title">
            <h1>GYM管理系统</h1>
        </div>
        <div class="input">
            <el-input v-model="name" prefix-icon="el-icon-user" placeholder="请输入用户名"></el-input>
        </div>
        <div class="input">
            <el-input v-model="password" prefix-icon="el-icon-lock" placeholder="请输入密码" auto-complete="new-password" show-password></el-input>
        </div>
        <div class="input">
            <el-button @click="login" style="width:500px" type="primary"  >登录</el-button>

        </div>
      <div class="input">
      <el-button @click="register1" style="width:500px" type="primary" >注册</el-button>
        </div>

      <div v-if="showError" class="error-message">Invalid username or password</div>
    </div>


</template>

<script>



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
        login() { this.$http.get('/us').then(response => {

          if (response.data.find(user => user.username === this.loginUsername && user.password === this.loginPassword)) {

            console.log('登陆成功');
            this.$router.push('/Home');
            // 登陆成功处理逻辑
          } else {
            console.log('登陆失败');
            // 登陆失败处理逻辑
          }
        })

        },
      register1() {
          this.$router.push('/');
      }
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
    margin: 20px auto;
    width: 500px;
}

</style>