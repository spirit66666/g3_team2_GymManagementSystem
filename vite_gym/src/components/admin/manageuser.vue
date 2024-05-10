<template>


    <el-config-provider >

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
