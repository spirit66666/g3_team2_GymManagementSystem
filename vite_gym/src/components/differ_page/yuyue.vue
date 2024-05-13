<script>
import {ElButton, ElDrawer, ElMessageBox} from "element-plus";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import { computed, ref ,reactive , onMounted ,onBeforeUpdate,onUpdated ,getCurrentInstance} from 'vue'

import Store from '../../components/store/store.js'
export default {
  components: {CircleCloseFilled, ElDrawer, ElButton},
  data() {
    return {
      proxy: getCurrentInstance(),
      visible: false,
      value1: '',
      formLabelWidth :  '140px',
      loading: false,
      value: '',
      dialog: false,
      facility:  [
        {
          name: 'Facility1',

          value: 'Facility1',
          disabled: false,
          'type': '',
        },
        {
          name: 'Facility2',
          value: 'Facility2',
          disabled: false,
          'type': '',
        },
        {
          name: 'Facility3',
          value: 'Facility3',
          disabled: false,
          'type': '',
        },
        {
          name: 'Facility4',
          value: 'Facility4',
          disabled: false,
          'type': '',
        },
        {
          name: 'Facility5',
          value: 'Facility5',
          disabled: false,
          'type': '',
        },
      ],
      isAppoint: false,
      appointForm: {
        // Define properties for the appointForm model here
        // Example:
        date: '',
        time: ''
      },
      week: [
        // Define week array elements here
        // Example:
        { month: 'Jan', date: 1, day: 'Monday', is_active: 0 },
        { month: 'Jan', date: 2, day: 'Tuesday', is_active: 0 },
        { month: 'Feb', date: 3, day: 'Fri', is_active: 0 },
        { month: 'Feb', date: 4, day: 'Sat', is_active: 0 },
        { month: 'Mar', date: 5, day: 'Sun', is_active: 0 },
        { month: 'Mar', date: 6, day: 'Mon', is_active: 0 },
        { month: 'Mi', date: 7, day: 'Sun', is_active: 0 },
      ],
      timeArr: [
        // Define timeArr array elements here
        // Example: // 0: Available, 1: Booked, 2: Current
        { time: '09:00 AM', status: 0 },

        { time: '10:00 AM', status: 0 },
        { time: '11:00 AM', status: 0 },
        { time: '12:00 PM', status: 0 },
        { time: '13:00 PM', status: 0 },
        { time: '14:00 PM', status: 0 },
        { time: '15:00 PM', status: 0 },
        { time: '16:00 PM', status: 0 },
        { time: '17:00 PM', status: 0 },
        { time: '18:00 PM', status: 0 },
        { time: '19:00 PM', status: 0 },
        { time: '20:00 PM', status: 0 },
        { time: '21:00 PM', status: 0 },
        { time: '22:00 PM', status: 0 },
        { time: '23:00 PM', status: 0 },

      ],
      remark: ''
    };
  },
  methods: {

    changWeek(item, index) {
      // Implement changWeek method logic here
      // Example:

      if(Store.state.username===""){ this.visible = true}
      this.appointForm.date = item.date;
      this.week.forEach(item => {
        item.is_active = 0;

      });
      item.is_active = 1;
    },
    changTime(item, index) {
      // Implement changTime method logic here
      // Example:

      if(Store.state.username===""){ this.visible = true}
      this.appointForm.time = item.time;
      this.timeArr.forEach(item => {
        item.status = 0;

      });
      item.status = 2;
    },

    fetchData() {
      fetch("http://localhost:9990/facilityName"+facility.values.name )
          .then(response => response.json()).then(response => {

        facility.values = response.data;
        console.log(response);

      })
      },

    confirm  ()  {
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
,

  }
};
</script>
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
      <el-link :underline="false" v-for="item in facility.length-1"
               v-model:facility="facility[item].name"
               @click="selectFacility(item,facility[item].name)">{{facility[item].name}}</el-link>
    </div>
    <div class="m-4">


      <el-text class="mx-1" type="primary">项目名称:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1"

               v-model="facility[item].name"
               @click="selectFacility(item,facility[item].name)"
      >{{facility[item].name}}</el-link>
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


      <el-text class="mx-1" type="primary">场馆:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1"
               v-model:facility="facility[item].value"
               @click="selectFacility(item,facility[item].value)">{{facility[item].disabled}}</el-link>
    </div>

    <div >
    <span v-for="(item,index) in timeList" :key="index">
       <span style="padding-left: 5px">
          <el-button  style="width: 80px" :type="item.type" :disabled="item.flag"
                      @click="selectTime(index,item.time)">{{item.time}}</el-button>
        </span>
      <!--每5个一行-->
        <span v-if="(index+1)%5==0">
          <br>
        </span>
    </span>
    </div>

  </div>



  <el-form label-width="120px" :model="appointForm">
    <div style="margin:20px;">
      <div style="display:flex;justify-content:space-between;">
            <span v-for="(item,index) in week" :key="index" :class="{'top_style':item.is_active===0,'top_active':item.is_active===1}" @click="changWeek(item,index)">
              <div style="height:25px;line-height:20px;">{{item.month}}-{{item.date}}</div>
              <div style="height:25px;line-height:20px;">{{item.day}}</div>
            </span>
      </div>
      <div style="display:flex;margin:20px 50px;font-size:18px;justify-content:space-between;">
        <div style="display:flex;"><div style="background-color:#C8C9CC;width:40px;height:20px;margin-right:10px;"></div><div>不可预约</div></div>
        <div style="display:flex;"><div style="background-color:#ffa4a4;width:40px;height:20px;margin-right:10px;"></div><div>已有预约</div></div>
        <div style="display:flex;"><div style="background-color:#3EA7F1;width:40px;height:20px;margin-right:10px;"></div><div>当前预约</div></div>
      </div>
      <div style="margin:20px 50px;height:250px" class="button_wrap">
        <el-button v-for="(item,index) in timeArr" :key="index" @click="changTime(item,index)" :type="item.status===0?'':item.status===1?'danger':item.status===2?'info':'primary'" :disabled="item.status===1||item.status===2" class="button_style">{{item.time}}</el-button>
      </div>
    </div>
    <el-row :gutter="20">
      <el-col :span="18">
        <el-form-item label="备注：">
          <el-input placeholder="请输入" v-model="remark" clearable></el-input>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>


  <el-dialog v-model="visible" :show-close="false" width="500">
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header">
        <h4 :id="titleId" :class="titleClass">请登录后再进行操作</h4>
        <el-button type="danger" @click="close">
          <el-icon class="el-icon--left"><CircleCloseFilled /></el-icon>
          Close
        </el-button>
      </div>
    </template>

  </el-dialog>
</template>

<style scoped>

.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 16px;
}
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
.top_style,.top_active{
  border:1px solid #AAA;
  padding:3px 20px;
  text-align:center;
}
.top_active{
  border-color:#02A7F0;
  color: #02A7F0;
}
.button_style{
  text-align:center;
  float:left;
  width: 80px;
}
</style>