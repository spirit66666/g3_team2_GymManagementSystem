<template>


    <el-config-provider >
<el-button type="primary" @click="userdialogVisible=true">增加用户</el-button>
      <el-table :data="tableData">
        <el-table-column prop="userID" label="ID"></el-table-column>
        <el-table-column prop="userName" label="用户名"></el-table-column>
        <el-table-column prop="passWord" label="密码"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="mobilePhone" label="手机号"></el-table-column>
        <el-table-column label="操作">
          <template  #default="scope">
            <el-button type="text" @click="editUser(scope.$index)">编辑</el-button>
            <el-button type="text" @click="deleteUser(scope.$index)">删除</el-button>
          </template>
        </el-table-column>

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

<el-dialog title="增加用户" v-model="userdialogVisible">
  <el-form :model="form" ref="form" label-width="80px">
    <el-form-item label="用户名" prop="userName">
      <el-input v-model="form.userName"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="passWord">


      <el-input type="password" v-model="form.passWord"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="mobilePhone">
      <el-input v-model="form.mobilePhone"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="userdialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">确 定</el-button>
  </div>
</el-dialog>


</template>

<script>

export default {
  data() {
    return {
      form: [],
      userdialogVisible: false,
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
    deleteUser(index) {
      console.log(index);
      this.$http.delete("/deleteuser/" + this.tableData[index].userID).then(response => {
            console.log(response);
            this.$message({
              message: '用户删除成功',
              type: 'success'

            });
            this.fetchData();
          }

      ).catch(error => {
        console.log(error);
        this.$message.error('用户删除失败');
      });
    },
    editUser(index) {
      console.log(index);
      this.form = this.tableData[index];
      this.userdialogVisible = true;
    },
    addUser() {
      console.log(this.form);
      this.$http.post("/postuser", {

        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.form)
      }).then(response => {
            console.log(response);
            this.$message({
              message: '用户添加成功',
              type: 'success'
            });
            this.userdialogVisible = false;
            this.fetchData();
          }

      ).catch(error => {
        console.log(error);
        this.$message.error('用户添加失败');
      });
    },
    fetchData() {
      this.$http.get("/pageuser?pageNumber=" + this.pageNumber + "&pageSize="+ this.pageSize)
          .then(response => {

        console.log(response);
        this.total = response.data.total;

        this.tableData = response.data.list;


      });
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

  },
};
</script>
