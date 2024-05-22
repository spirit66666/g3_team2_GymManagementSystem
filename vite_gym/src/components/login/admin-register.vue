<template>

  <h2>管理员登陆页面</h2>
  <form @submit.prevent="loginUser">
    <label for="loginUsername">用户名:</label>
    <input type="text" id="loginUsername" v-model="loginUsername">
    <label for="loginPassword">密码:</label>
    <input type="password" id="loginPassword" v-model="loginPassword">
    <button type="submit">登陆</button>
  </form>


</template>

<script>

import {ElMessage} from "element-plus";
import {h} from "vue";

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

  created() {
    this.fetchData();

  },
  methods: {
    fetchData() {
      this.$http.get("/page?pageNumber=" + this.pageNumber + "&pageSize="+ this.pageSize)
          .then(response => response.json()).then(response => {

        console.log(response);
        this.total = response.total;

        this.tableData = response.data;


      });
    },
    registerUser() {
      this.$http.post('/post?username='+this.registerUsername+'&password='+this.registerPassword).then(response => {

        console.log(response);
      })
    },
    loginUser() {
      this.$http.put('/fetch').then(response => {
        this.tableData = response.data;

        console.log(this.tableData);
        if (response.data.find(user => user.username === this.loginUsername && user.password === this.loginPassword)) {

          console.log('登陆成功');
          this.$router.push('/home');
          // 登陆成功处理逻辑
        } else {
          console.log('登陆失败');
          // 登陆失败处理逻辑
        }
      })
    },
    registeradmin() {
      this.$http.post('/postuser?userName='+this.registerUsername+'&passWord='+this.registerPassword+'&mobilePhone='+this.registerphone+'&email='+this.registeremail).then(response => {

        console.log(response);



        ElMessage({
          message: h('p', { style: 'line-height: 1; font-size: 14px' }, [
            h('span', null, '用户'),
            h('i', { style: 'color: teal' }, this.registerUsername,'已经注册成功'),
          ]),
        })

        setTimeout(() => {

          this.$router.push('/home');
        }, 400)
      })
    },
    handleCurrentChange(currentPage) {
      console.log(currentPage);
      this.pageNumber = currentPage;
      this.fetchData();
    },
    handleSizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      this.fetchData();}

  }
};
</script>
