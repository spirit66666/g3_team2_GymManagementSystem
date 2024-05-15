<template>


  <el-drawer
      v-model="dialog"
      title="I have a nested form inside!"
      :before-close="handleClose"
      direction="ltr"
      class="demo-drawer"
  >
    <div class="demo-drawer__content">

      <div class="demo-drawer__footer">
        <el-button @click="cancelForm">Cancel</el-button>
        <el-button type="primary" :loading="loading" @click="onClick">
          {{ loading ? 'Submitting ...' : 'Submit' }}
        </el-button>
      </div>
    </div>
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


  <el-dialog v-model="dialogFormVisible" title="Shipping address" width="500">

    <h1>{{rowdata.gymName}}</h1>

    <el-form-item label="Activity form">
      <el-input v-model="rowdata.gymName" type="textarea" />
    </el-form-item>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          Confirm
        </el-button>
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
            <el-button size="small" plain @click="dialogForm(scope.row)">
              修改
            </el-button>
            <el-button
                color="red"
                size="small"
                type="danger"
                @click="dialog = true"
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

<script lang="ts" setup>

interface rowData {
  gymID: number
  gymName: string
  gymAddress: string
  gymPhone: string
  gymEmail: string
}
const rowdata = ref<rowData>()
import {computed, ref, reactive, watch, onMounted} from 'vue'
import { ElRow, ElCol, ElFormItem, ElSelect, ElOption, ElDatePicker, ElTable, ElTableColumn } from 'element-plus';
const value = ref('')
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
const dialogForm = (row) => {
  console.log(row);
  rowdata.value = row;
  dialogFormVisible.value = true;
}

import { ElButton, ElDrawer,ElMessageBox} from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'
const data = ref([

]);


onMounted(() => {

  fetchData();

})
const fetchData = () => {
  // Simulate API call to fetch schedule data based on selectedDate, venue, and sport
  // Assign fetched data to tableData
  fetch("http://localhost:9990/pagegym?pageNumber=" + pageNumber.value + "&pageSize="+ pageSize.value)
      .then(response => response.json()).then(datas => {

    data.value=datas.data;
  total.value = datas.total;

  });
};



const dialogFormVisible = ref(false)

const loading = ref(false)


const onClick = () => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
    dialog.value = false
  }, 400)
}
var timer
const handleClose = (done) => {
  if (loading.value) {
    return
  }
  ElMessageBox.confirm('Do you want to submit?')
      .then(() => {
        loading.value = true
         timer = setTimeout(() => {
          done()
          // 动画关闭需要一定的时间
          setTimeout(() => {
            loading.value = false
          }, 400)
        }, 200)
      })
      .catch(() => {
        // catch error
      })
}

const cancelForm = () => {
  loading.value = false
  dialog.value = false
  clearTimeout(timer)
}
const dialog = ref(false)
const visible = ref(false)
interface User {
  date: string
  name: string
  address: string
}

const search = ref('')

const tableRowClassName = ({
                             row,
                             rowIndex,
                           }: {
  row: User
  rowIndex: number
}) => {
  if (rowIndex === 1) {
    return 'warning-row'
  } else if (rowIndex === 3) {
    return 'success-row'
  }
  return ''
}
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