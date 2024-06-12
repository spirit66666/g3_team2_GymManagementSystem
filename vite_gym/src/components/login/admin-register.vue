<template>


  <div class="container" id="Application">
    <div class="container">
      <h1 class="title">管理员注册页面-创建你的账号</h1>

      <div  class="inputContainer">
        <div class="field">
          <label for="username">用户名</label>
          <input class="input" type="text" placeholder="请输入用户名" id="username" v-model="registerUsername" />


        </div>
        <div class="field">
          <label for="username">密码</label>
          <input class="input" type="password" placeholder="请输入密码"  v-model="registerPassword" />



        </div>


        <div class="field">
          <label for="username">手机号</label>
          <input class="input" type="password" placeholder="请输入手机" id="password" v-model="registerphone" />



        </div>

        <div class="field">
          <label for="username">邮箱</label>
          <input class="input" type="password" placeholder="请输入邮箱" id="password" v-model="registeremail" />



        </div>

      </div>
      <button @click="registeradmin" class="btn">创建账号</button>
      <button @click="back2" class="btn">返回</button>
    </div>
  </div>


</template>

<script>

import {ElMessage} from "element-plus";
import {h} from "vue";
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
      registerphone: '',
      registeremail: '',
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

    registeradmin() {

      this.$http.post('/postadmin', {
        "adminName": this.registerUsername,
        "passWord": this.registerPassword,
        "mobilePhone": this.registerphone,
        "email": this.registeremail
      }).then(response => {

        console.log(response);



        ElMessage({
          message: h('p', { style: 'line-height: 1; font-size: 14px' }, [
            h('span', null, '管理员'),
            h('i', { style: 'color: teal' }, this.registerUsername,'已经注册成功'),
          ]),
        })

        Store.commit('setAdminname', this.registerUsername);
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
      this.fetchData();},

    back2() {
      this.$router.push('/');
    }



  }

};
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