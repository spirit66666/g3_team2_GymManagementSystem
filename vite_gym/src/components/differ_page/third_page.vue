<template>

  <el-config-provider >

    <el-table :data="alreadyTableData">
      <el-table-column prop="reserveID" label="预约ID"></el-table-column>
      <el-table-column prop="" label="场馆名字"></el-table-column>
      <el-table-column prop="reserveTime" label="预约时间"></el-table-column>

      <el-table-column prop="reserveDate" label="预约日期"></el-table-column>
      <el-table-column prop="reserveStatus" label="预约状态"></el-table-column>

      <el-table-column label="操作"> <template #default="scope">
        <el-button type="text" size="mini" @click="handleDelete(scope.$index)">

        删除预约
      </el-button>
      </template></el-table-column>

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

<script setup>
import {ref, onMounted, getCurrentInstance} from 'vue'

import Store from '../../components/store/store.js'


const currentInstance = getCurrentInstance();
const { $http } = currentInstance.appContext.config.globalProperties;
const alreadyTableData = ref([])
const tableData2 = ref([])
const pageNumber = ref(1)
const pageSize = ref(2)
const total = ref(0)
const handleDelete = (index) => {

  console.log(index)
  const reserveID = alreadyTableData.value[index].reserveID
  console.log(reserveID)
  $http.delete("/deletereserve/" + reserveID).then(response => {
    console.log(response)
    fetchData()
  })
}
onMounted(() => {
  fetchData()
})
const fetchData= () => {

  $http.get("/getreserve/"+Store.state.username)

      .then(response => {
        console.log(response.data);

        alreadyTableData.value = response.data;
      });


  $http.get("/page?pageNumber=" + pageNumber.value + "&pageSize="+ pageSize.value)
      .then(response => {

    console.log(response);
    total.value = response.total;

    tableData2.value = response.data;

  });
}

const handleCurrentChange = (currentPage) => {
  console.log(currentPage);
  pageNumber.value = currentPage
  fetchData()
}
const handleSizeChange = (size) => {
  pageSize.value = size
  fetchData()
}
</script>


