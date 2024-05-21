<template>


  <el-drawer
      v-model="dialog"
      title="增加场馆"
      direction="ltr"
      class="demo-drawer"
  >
    <el-form ref="form" :model="addFacilityData" label-position="left" label-width="100px">
      <el-form-item label="gymName">
        <el-input v-model="addFacilityData.gymName" />
      </el-form-item>
      <el-form-item label="gymAddress">
        <el-input v-model="addFacilityData.gymAddress" />
      </el-form-item>

    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addFacility">确认</el-button>
      </div>
    </template>
  </el-drawer>

  <el-drawer v-model="visible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">This is a custom header!</h4>
      <el-button type="danger" @click="close">
        <el-icon class="el-icon--left"><CircleCloseFilled /></el-icon>
        Close
      </el-button>
    </template>
    This is drawer content.
  </el-drawer>


  <el-button type="primary" @click="dialog = true">增加场馆</el-button>


  <el-dialog v-model="dialogFormVisible" title="修改场馆" width="500">
    <el-form ref="form" :model="rowdata" label-position="left" label-width="100px">
      <el-form-item label="gymID">
        <el-input v-model="rowdata.gymID" disabled />
      </el-form-item>
      <el-form-item label="gymName">
        <el-input v-model="rowdata.gymName" />
      </el-form-item>
      <el-form-item label="gymAddress">
        <el-input v-model="rowdata.gymAddress" />
      </el-form-item>

    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateFacilityData">修改</el-button>
      </div>
    </template>
  </el-dialog>

  <div>
    <el-config-provider >
      <el-table :data="data" style="width: 100%"
                :row-class-name="tableRowClassName"
                :default-page-size=1
      >
        <el-table-column label="gymID" prop="gymID" />
        <el-table-column label="gymName" prop="gymName" />
        <el-table-column label="gymAddress" prop="gymAddress" />
        <el-table-column label="gymPhone" prop="gymPhone" />
        <el-table-column label="gymEmail" prop="gymEmail" />
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="small" placeholder="Type to search" />
          </template>
          <template #default="scope">
            <el-button size="small" plain @click="updateFacility(scope.row)">
              修改
            </el-button>
            <el-button
                color="red"
                size="small"
                type="danger"
                @click="deleteFacility(scope.row.gymID)"
            >
              删除
            </el-button>
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
  </div>
</template>

<script  setup>

import axios from "axios";

import {computed, ref, reactive, watch, onMounted, getCurrentInstance} from 'vue'

const addFacilityData = reactive({
  gymName: '',
  gymAddress: '',
})// 创建一个响应式对象
const rowdata = reactive({
  gymID: 0,
  gymName: '',
  gymAddress: ''
});
const currentInstance = getCurrentInstance();
const { $http } = currentInstance.appContext.config.globalProperties;
import { ElRow, ElCol, ElFormItem, ElSelect, ElOption, ElDatePicker, ElTable, ElTableColumn } from 'element-plus';
const total = ref(0)
const pageSize = ref(3)
const pageNumber = ref(1)
const tableData = ref([])
const handleCurrentChange = (currentPage) => {

  console.log(currentPage);
pageNumber.value = currentPage;
  fetchData();
}
const handleSizeChange = (pageSize) => {

  console.log(pageSize);
  pageSize.value = pageSize;
  fetchData();
}
const updateFacility = (row) => {
  dialogFormVisible.value = true;

  rowdata.gymID = row.gymID;
  rowdata.gymName = row.gymName;
  rowdata.gymAddress = row.gymAddress;

}
const deleteFacility = (gymID) => {
  console.log(gymID)
  ElMessageBox.confirm('确认删除该场馆吗？').then(() => {
    $http.delete("/deletegym/"+ gymID
    ).then((response) => {
      console.log(response.data);
      ElMessageBox.alert('删除成功');
      fetchData();
    });
  }).catch(() => {
    console.log('cancel');
  });
}
const updateFacilityData = () => {
  $http.post("/updategym" , {
    gymID: rowdata.gymID,
    gymName: rowdata.gymName,
    gymAddress: rowdata.gymAddress,
  }).then((response) => {
    console.log(response.data);
    dialogFormVisible.value = false;
    ElMessageBox.alert('修改成功');
    fetchData();
  });

}

const dialog = ref(false)
import { ElButton, ElDrawer,ElMessageBox} from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'
const data = ref([

]);


const addFacility = () => {

  $http.post("/addgym", {
    gymName: addFacilityData.gymName,
    gymAddress: addFacilityData.gymAddress,
  }).then((response) => {
    console.log(response.data);
    ElMessageBox.alert('添加成功');
    dialog.value = false;
    fetchData();
}
)
}

onMounted(() => {
  fetchData();

})
const fetchData = () => {
  // Simulate API call to fetch schedule data based on selectedDate, venue, and sport
  // Assign fetched data to tableData
  $http.get("/pagegym?pageNumber=" + pageNumber.value + "&pageSize="+ pageSize.value)
     .then(datas => {


    data.value=datas.data.data;
  total.value = datas.data.total;

  });
};

const dialogFormVisible = ref(false)

const visible = ref(false)

const search = ref('')

</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}

.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: stretch;
}
.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
}
</style>