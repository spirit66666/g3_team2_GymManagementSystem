<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="date" label="Date" width="180" />
    <el-table-column prop="name" label="Name" width="180" />
    <el-table-column prop="address" label="Address" />
  </el-table>

  <el-config-provider >

    <el-table :data="tableData2">
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

<script lang="ts" setup>
import {ref, onMounted, getCurrentInstance} from 'vue'

const { proxy }: any = getCurrentInstance();
const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
const tableData2 = ref([])
const pageNumber = ref(1)
const pageSize = ref(2)
const total = ref(0)

onMounted(() => {
  fetchData()
})
const fetchData= () => {
  fetch("http://localhost:9990/page?pageNumber=" + pageNumber.value + "&pageSize="+ pageSize.value)
      .then(response => response.json()).then(response => {

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


