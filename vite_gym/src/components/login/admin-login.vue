<template>

  <div id="title">
    <h1>管理员管理系统</h1>
  </div>
  <div class="input">
    <el-input v-model="name" placeholder="请输入用户名"></el-input>
  </div>
  <div class="input">
    <el-input v-model="password" placeholder="请输入密码" auto-complete="new-password" show-password></el-input>
  </div>

  <div class="input">
    <el-button @click="login" style="width:500px" type="primary"  >登录</el-button>
  </div>

  <div v-if="showError" class="error-message">Invalid username or password</div>



</template>

<script>

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
</style>