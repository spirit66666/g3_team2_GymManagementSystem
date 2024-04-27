<template>
  <div>
    <h2>注册页面</h2>
    <form @submit.prevent="registerUser">
      <label for="username">用户名:</label>
      <input type="text" id="username" v-model="registerUsername">
      <label for="password">密码:</label>
      <input type="password" id="password" v-model="registerPassword">
      <button type="submit">注册</button>
    </form>

    <h2>登陆页面</h2>
    <form @submit.prevent="loginUser">
      <label for="loginUsername">用户名:</label>
      <input type="text" id="loginUsername" v-model="loginUsername">
      <label for="loginPassword">密码:</label>
      <input type="password" id="loginPassword" v-model="loginPassword">
      <button type="submit">登陆</button>
    </form>


    <h2 prop="username"></h2>
    <ul>
      <li v-for="user in users" :key="user.id">{{ loginUsername  }}</li>
    </ul>

    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
    </el-table>
  </div>


</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      tableData: [],
      registerUsername: '',
      registerPassword: '',
      loginUsername: '',
      loginPassword: ''
    };
  },
  methods: {
    registerUser() {
      axios.post('http://localhost:9990/us', {
        username: this.registerUsername,
        password: this.registerPassword
      })
          .then(response => {
            console.log(response.data);
            console.log('注册成功');
            this.registerUsername = response.data.username;
            // 注册成功处理逻辑
          })
          .catch(error => {
            console.error(error);
            console.log('注册失败');
            // 注册失败处理逻辑
          });
    },
    loginUser() {
      this.$http.get('/us').then(response => {
            console.log(response.data);
            // 登陆成功处理逻辑
            console.log('登陆成功');
            this.tableData = response.data;
          })
    }
  }
};
</script>
