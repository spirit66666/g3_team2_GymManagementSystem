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
        <el-button type="primary" @click="confirm">
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


  </div>

    <el-config-provider class="m-4"  >
      <el-table :data="scheduleData" style="width: 100%"
                height="200"
                border
                :cell-style="cellStyle"
      >
        <el-table-column label="Date" prop="timeRange" />

        <el-table-column
            v-for="(item, index) in scheduleData"
            :key="index"

            :label="item.name"
            >
          <template #default="scope">
            <el-button type="text" @click="cellclick(scope.row, scope.column)">
              {{ canreserve }}
            </el-button>
          </template>
        </el-table-column>

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
</template>

<script lang="ts" setup>
import { computed, ref ,reactive , onMounted ,onBeforeUpdate,onUpdated ,getCurrentInstance} from 'vue'


const { proxy }: any = getCurrentInstance();
onBeforeUpdate(() => {

})
const value1 = ref('')
//ref要用value
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
const canreserve = ref('')
const warningColor = ref(false);
const alarmingColor = ref(false);

interface ScheduleItem {
  id: number;
  name: string
  // other properties...
  timeRange: string;
}
const scheduleData: ScheduleItem[] = [
  { id: 1, timeRange: "8:00-9:00",name:"羽毛球" },
  { id: 2, timeRange: "9:00-9:00",name:"羽毛球2" },
  { id: 3, timeRange: "10:00-9:00",name:"羽毛球3" },

  { id: 4, timeRange: "11:00-9:00",name:"羽毛球4" },

  { id: 1, timeRange: "8:00-9:00",name:"羽毛球5" },
];

onMounted(() => {

  fetch('https://jsonplaceholder.typicode.com/posts')
      .then((response) => response.json())
      .then((data) => {
            console.log(data)
            scheduleData.values = data.map((item) => {
              return {
                id: item.id,
                name: item.title,
                timeRange: item.body,
              }
            })
          }
      )

})

const confirm = () => {
  dialogFormVisible.value = false;
  proxy.$http.get('/us')
     .then((response) => {
        console.log(response.data)
        ElMessageBox.alert('Submitted successfully!')
      })
     .catch((error) => {
       console.log(error)
       ElMessageBox.alert('Submission failed!')

       // handle error

     })
}
const cellclick = (row, column) => {

  if(warningColor.value==true){
    dialogFormVisible.value = true;
  }
}

onUpdated(() => {


})
const cellStyle = ({  row, column, rowIndex, columnIndex }) => {
console.log(warningColor.value)
      if ( columnIndex === 1&& row.id >= 2) {
        canreserve.value = '可以预约'
        warningColor.value = true;


        return { backgroundColor: "green" };
      }
      else if (rowIndex === 1) {
        warningColor.value = false;
        alarmingColor.value = true;

         canreserve.value = '不能预约'

        return { backgroundColor: "red" };
    }


};

const value = ref('')
import { ElButton, ElDrawer,ElMessageBox} from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'


let timer
interface data {
  name: string
  value: string
  disabled: boolean
}


const onClick = () => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
    dialog.value = false
  }, 400)
}
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
const filterTableData = computed(() =>
    tableData.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    )
)

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