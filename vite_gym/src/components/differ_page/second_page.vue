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


  <div>

    <div class="m-4">
      <el-text class="mx-1" type="primary">场馆名称:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1" >{{facility[item].name}}</el-link>
    </div>
    <div class="m-4">


      <el-text class="mx-1" type="primary">场馆名称:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1" >{{facility[item].name}}</el-link>
    </div>

    <div class="m-4">


      <el-text class="mx-1" type="danger">选择日期:   </el-text>
      <el-date-picker
          v-model="value1"
          type="date"
          placeholder="Pick a day"
      />
    </div>

    <div class="m-4">


      <el-text class="mx-1" type="primary">预约状态:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1" >{{facility[item].name}}</el-link>
    </div>


    <el-config-provider class="m-4"  >

      <el-table :data="scheduleData" style="width: 100%"
                height="200"
                border
                :cell-style="cellStyl"
      >

        <el-table-column label="Date" prop="timeRange" />

        <el-table-column
            v-for="(item, index) in scheduleData"
            :key="index"

            :label="item.name"
            />

        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="small" placeholder="Type to search" />
          </template>
          <template #default="scope">
            <el-button size="small" plain @click="dialogFormVisible = true">
              修改
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
      <el-pagination :total=tableData.length  />
    </el-config-provider>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref ,reactive } from 'vue'

const cellStyle = ({ row, column, rowIndex, columnIndex }) => {
  let warningColor = false;
  let alarmingColor = false;
  Object.keys(row).forEach((key) => {
    // column.property为每项绑定的prop,
    // 与之前的prop="address.value"不同，prop="address"方便key与prop对应
    // console.log(key,column,column.property);
    if (row[key].state == 1 && key == column.property) {
      if (rowIndex === rowIndex && columnIndex === columnIndex) {
        warningColor = true;
      }
    } else if (row[key].state == 2 && key == column.property) {
      if (rowIndex === rowIndex && columnIndex === columnIndex) {
        alarmingColor = true;
      }
    }
  });
  if (warningColor) {
    return "success-row";
  }
  //多加一个颜色的需求再正常不过了
  if (alarmingColor) {
    return "alarm-row";
  }
  return "";
};
const cellStyl= ({ row, column, rowIndex, columnIndex })=> {

  if (rowIndex === 1|| columnIndex === 2) {
    return { "background":"green",
      "color":"red"}
  }

  if (columnIndex === 1  || columnIndex === 2 || columnIndex === 3) {
    return { "background":"red",
      "color":"red"}
  }

}



const value = ref('')
import { ElButton, ElDrawer,ElMessageBox} from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'

let timer
interface data {
  name: string
  value: string
  disabled: boolean
}
const state = reactive({
  checkNumber: "",  // 存储选中的
})



interface ScheduleItem {
  id: number;
  name: string
  // other properties...
  timeRange: string;
}
const scheduleData: ScheduleItem[] = [
  { id: 1, timeRange: "8:00-9:00",name:"羽毛球" },
  { id: 1, timeRange: "8:00-9:00",name:"羽毛球" },
  { id: 1, timeRange: "8:00-9:00",name:"羽毛球" },

  { id: 1, timeRange: "8:00-9:00",name:"羽毛球" },

  { id: 1, timeRange: "8:00-9:00",name:"羽毛球" },
];
const value1 = ref('')
const dialogFormVisible = ref(false)
const facility: data[] = [

  {
    name: 'Facility1',
    value: 'Facility1',
    disabled: false,
  },
  {
    name: 'Facility2',
    value: 'Facility2',
    disabled: false,
  },
  {
    name: 'Facility3',
    value: 'Facility3',
    disabled: false,
  },
  {
    name: 'Facility4',
    value: 'Facility4',
    disabled: false,
  },
  {
    name: 'Facility5',
    value: 'Facility5',
    disabled: false,
  },
]
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
.el-link {
  margin-right: 8px;
}
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
.m-4 {
  height: 50px;
}
</style>