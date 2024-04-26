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
import { ElMessage } from 'element-plus';



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
        login() {
          if (this.username !== 1 || this.password !== 1) {
            this.showError = true;  // 显示错误提示框
          } else {
            // 登录成功的逻辑
            ElMessage({
              message:'登录成功',
              type:'success',
              duration:150
            })
            setTimeout(() => {
              this.$router.push('/Home');
            }, 1500);
        }

        },
      register1() {
          this.$router.push('/Appregister');
      }
    }
}
</script>

<style scoped>
#container {
    background: lightpink;
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