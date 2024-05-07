<template>


  <el-drawer
      v-model="dialog"
      title="I have a nested form inside!"
      :before-close="handleClose"
      direction="ltr"
      class="demo-drawer"
  >
    <div class="demo-drawer__content">

      <el-time-select
          v-model="value"
          style="width: 240px"
          start="08:30"
          step="00:15"
          end="18:30"
          placeholder="Select time"
      />
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


  <el-dialog v-model="dialogFormVisible" title="Shipping address" width="500">

    <el-time-select
        v-model="value"
        style="width: 240px"
        start="08:30"
        step="00:15"
        end="18:30"
        placeholder="Select time"
    />
    <el-form :model="form">
      <el-form-item label="Promotion name" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Zones" :label-width="formLabelWidth">
        <el-select v-model="form.region" placeholder="Please select a zone">
          <el-option label="Zone No.1" value="shanghai" />
          <el-option label="Zone No.2" value="beijing" />
        </el-select>
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>

  <div class="schedule-container">
    <el-row :gutter="16">
      <el-col span="8">
        <el-form-item label="场馆选择" prop="venue">
          <el-select v-model="venues" placeholder="选择场馆">
            <el-option
                v-for="item in venues"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="运动项目选择" prop="sport">
          <el-select v-model="sports" placeholder="选择运动项目">
            <el-option
                v-for="item in sports"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-col>

    </el-row>
  </div>

  <div>
    <el-config-provider >

      <el-table :data="filterTableData" style="width: 100%"
                :row-class-name="tableRowClassName"
                :default-page-size=1
      >

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
            <el-button size="small" plain @click="dialogFormVisible = true">
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
      <el-pagination :total=tableData.length  />
    </el-config-provider>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref ,reactive ,watch } from 'vue'

import { ElRow, ElCol, ElFormItem, ElSelect, ElOption, ElDatePicker, ElTable, ElTableColumn } from 'element-plus';

const value = ref('')
import { ElButton, ElDrawer,ElMessageBox} from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'

let timer

interface TimeSlot {
  value: string;
  label: string;
}

interface ScheduleData {
  time: string;
  status: string;
  // Add more properties for each time slot (羽1, 羽2, etc.)
}
const venues = [
  { value: 'complex', label: '综合体育馆' },
  { value: 'pingpong', label: '乒羽馆' },
  { value: 'swimming', label: '游泳场' },
];

const sports = [
  { value: 'badminton', label: '羽毛球' },
  { value: 'pingpong', label: '乒乓球' },
  // Add more sports options here
];

const date = ref(null);
const timeSlots = [
  { value: '08:00-09:00', label: '8:00-9:00', status: 'available' },
  { value: '09:00-10:00', label: '9:00-10:00', status: 'available' },
  // Add more time slots here
];

const fetchData = async (selectedDate) => {
  // Simulate API call to fetch schedule data based on selectedDate, venue, and sport
  // Assign fetched data to tableData
};

const bookSlot = (slot) => {
  if (slot.status === 'available') {
    // Simulate API call to book the slot
    // Update the slot status in tableData
  }
};

watch(date, async (val) => {
  if (val) {
    await fetchData(val);
  }
});


const dialogFormVisible = ref(false)

const formLabelWidth = '140px'
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
const loading = ref(false)

const value1 = ref('')

const onClick = () => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
    dialog.value = false
  }, 400)
}

const centerDialogVisible = ref(false)
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