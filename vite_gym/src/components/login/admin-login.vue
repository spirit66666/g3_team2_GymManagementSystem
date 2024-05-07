<template>
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
  <div>


    <el-config-provider >
      <el-pagination
      />
    <el-table :data="tableData">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>

    </el-table>

      <el-pagination  :total="total"
                      :page-size="pageSize"
                      :current-page="pageNumber"
                      @current-change="handleCurrentChange"
                      @size-change="handleSizeChange"
                      :page-sizes="[1, 2, 3, 4]"


                     layout="total, sizes, prev, pager, next, jumper"
                   />
        </el-config-provider>
  </div>


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
      fetch("http://localhost:9990/page?pageNumber=" + this.pageNumber + "&pageSize="+ this.pageSize)
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
