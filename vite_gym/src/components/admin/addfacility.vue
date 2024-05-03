<template>


  <el-drawer
      v-model="dialog"
      title="I have a nested form inside!"
      :before-close="handleClose"
      direction="ltr"
      class="demo-drawer"
  >
    <div class="demo-drawer__content">
      <el-form :model="form">
        <el-form-item label="Name" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Area" :label-width="formLabelWidth">
          <el-select
              v-model="form.region"
              placeholder="Please select activity area"
          >
            <el-option label="Area1" value="shanghai" />
            <el-option label="Area2" value="beijing" />
          </el-select>
        </el-form-item>
      </el-form>
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


  <el-table :data="filterTableData" style="width: 100%"
            :row-class-name="tableRowClassName">
    <el-table-column label="Image">
      <template #default="scope">
        <el-image
            :src="scope.row.image"
            alt="Image"
            fit="cover"
            style="width: 80px; height: 80px;"
        />
      </template>
    </el-table-column>
    <el-table-column label="Date" prop="date" />
    <el-table-column label="Name" prop="name" />
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="small" @click="visible = true">
          查看
        </el-button>
        <el-button
            color="lightblue"
            size="small"
            type="danger"
            @click="dialog = true"
        >
          预约
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { computed, ref ,reactive } from 'vue'

import { ElButton, ElDrawer,ElMessageBox} from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'

let timer
const loading = ref(false)

const onClick = () => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
    dialog.value = false
  }, 400)
}
const formLabelWidth = '80px'
const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})

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
        }, 2000)
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
const drawer = ref(false)
interface User {
  date: string
  name: string
  address: string
}

const search = ref('')
const filterTableData = computed(() =>
    tableData.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    )
)
const handleEdit = (index: number, row: User) => {
  tableData.push({
    date: "",
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  })
}
const handleDelete = (index: number, row: User) => {
  tableData.splice(index, 1)
}

const tableData: User[] = [
  {
    image: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    image: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    date: '2016-05-02',
    name: 'John',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    image: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    date: '2016-05-04',
    name: 'Morgan',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    image: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    date: '2016-05-01',
    name: 'Jessy',
    address: 'No. 189, Grove St, Los Angeles',
  },
]

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
</style>