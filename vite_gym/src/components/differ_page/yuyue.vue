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
      dialogFormVisible: false,
      dialog: false,
      facility:  [
        {
          facilityID: 1,
          facilityName: '音响设备',
          facilityNum: 1,
          remainNum: 1,
        }

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
      remark: '',
      timeList: [],
       dateArray :[]
    };
  },
  created() {
    console.log('created');
    this.fetchData();
  },
  computed: {
    aa () {
      var myDate = new Date() // 获取今天日期
      myDate.setDate(myDate.getDate() - 6)

      var dateTemp
      var flag = 1
      for (var i = 0; i < 7; i++) {
        dateTemp = (myDate.getMonth() + 1) + '-' + myDate.getDate()
        this.dateArray.push(dateTemp)
        myDate.setDate(myDate.getDate() + flag)
      }

      console.log('123456+', this.dateArray)

      return this.dateArray;
    },

  },
  methods: {
    changWeek(item, index) {
      console.log(this.value1.toLocaleDateString());

      if(Store.state.username===""){ this.visible = true}
      this.appointForm.date = item;
      this.dateArray.forEach(item => {
        item.is_active = 0;

      });
      item.is_active = 1;
    },
    changTime(item, index) {
      // Implement changTime method logic here
      // Example:
      if(Store.state.username===""){

        this.visible = true}
      else{
      this.appointForm.time = item.time;
      this.timeArr.forEach(item => {
        item.status = 0;

      });
      item.status = 1;
        this.dialogFormVisible = true;}
    },

    fetchData() {
      fetch("http://localhost:9990/getfacility" )
          .then(response => response.json()).then(response => {

        this.facility=response;
        console.log(this.facility);

      })
      },

    confirm  ()  {
      this.$http.get('/us')
          .then((response) => {
            console.log(response.data)
            ElMessageBox.alert('Submitted successfully!')
          })
          .catch((error) => {
            console.log(error)
            ElMessageBox.alert('Submission failed!')

            // handle error

          })

      this.dialogFormVisible = false;
    },



  },


};
</script>


<template>
  <el-dialog title="预约"  v-model="isAppoint1" width="40%" :before-close="closeAppoint">
    <el-form label-width="120px" :model="appointForm1">
      <div class="calender">
        <div class="calender_title">
          <div class="arrow arrow-left" @click="prev()"><</div>
          <div class="data">{{ currentYear }}-{{ currentMonthChinese }}</div>
          <div class="arrow arrow-right" @click="next()">></div>
        </div>
        <div class="calender_content">
          <div class="row title">
            <span class="title_span" v-for="item in title" :key="item">{{item}}</span>
          </div>
          <div class="row content">
            <span style="margin-bottom:5px;width:60px;margin-left:10px;" class="button_no" v-for="(item,index) in prevDays" :key="index+'a'"></span>
            <el-button class="content_button" v-for="(item,index) in timeArr1" :key="index" @click="changTime1(item,index)" :type="item.status===0?'':item.status===1?'danger':item.status===2?'info':'primary'" :disabled="item.status===1||item.status===2">{{index+1 }}</el-button>
          </div>
        </div>
      </div>
      <div class="button_wrap">
        <div style="display:flex;"><div style="background-color:#C8C9CC;width:40px;height:20px;margin-right:10px;"></div><div>不可预约</div></div>
        <div style="display:flex;"><div style="background-color:#ffa4a4;width:40px;height:20px;margin-right:10px;"></div><div>已有预约</div></div>
        <div style="display:flex;"><div style="background-color:#3EA7F1;width:40px;height:20px;margin-right:10px;"></div><div>当前预约</div></div>
      </div>
      <el-row style="width:500px;margin:0 auto;">
        <el-col>
          <el-form-item label="备注：" label-width="60px">
            <el-input placeholder="请输入" v-model="remark1" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer" >
      <el-button @click="closeAppoint">取消</el-button>
      <el-button type="primary" @click="saveAppoint" style="margin-left:20px;">确定</el-button>
    </div>
  </el-dialog>

  <el-dialog v-model="dialogFormVisible" title="请核对你的信息" width="500">
    <h3>请确认你的预约信息</h3>
    <h3>你选择的场馆为：{{facility.facilityName}}</h3>
    <h3>你选择的日期为：{{appointForm.date}}</h3>
    <h3>你选择的时间为：{{appointForm.time}}</h3>
    <h3>{{timeArr.time}}</h3>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">返回</el-button>

        <el-button type="primary" @click="confirm">
          确认
        </el-button>
      </div>
    </template>
  </el-dialog>

  <div>
    <div class="m-4">
      <el-text class="mx-1" type="primary">场馆名称:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1"
               v-model:facility="facility[item].facilityName"
               @click="selectFacility(item,facility[item].facilityName)">{{facility[item].facilityName}}</el-link>
    </div>
    <div class="m-4">
      <el-text class="mx-1" type="primary">项目名称:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1"
               v-model="facility[item].facilityName"
               @click="selectFacility(item,facility[item].facilityName)"
      >{{facility[item].name}}</el-link>
    </div>
    <div class="m-4">
      <el-text class="mx-1" type="primary">场馆:  </el-text>
      <el-link :underline="false" v-for="item in facility.length-1"
               v-model:facility="facility[item].value"
               @click="selectFacility(item,facility[item].value)">{{facility[item].disabled}}</el-link>
    </div>

  </div>



  <el-form label-width="120px" :model="appointForm">
    <div style="margin:20px;">

      <h1 v-for="(item,index) in aa" :key="index" ></h1>
      <div style="display:flex;justify-content:space-between;">

            <span v-for="(item,index) in dateArray" :key="index" :class="{'top_style':item.is_active===0,'top_active':item.is_active===1}" @click="changWeek(item,index)">

              <div style="height:25px;line-height:20px;">{{item}}</div>
            </span>
      </div>


      <div style="display:flex;margin:20px 50px;font-size:18px;justify-content:space-between;">
        <div style="display:flex;"><div style="background-color:#C8C9CC;width:40px;height:20px;margin-right:10px;"></div><div>不可预约</div></div>
        <div style="display:flex;"><div style="background-color:#ffa4a4;width:40px;height:20px;margin-right:10px;"></div><div>已有预约</div></div>
        <div style="display:flex;"><div style="background-color:#3EA7F1;width:40px;height:20px;margin-right:10px;"></div><div>当前预约</div></div>
      </div>



      <div style="margin:20px 50px;height:250px">
        <span v-for="(item,index) in timeArr" :key="index">
           <span >
        <el-button  @click="changTime(item,index)" :type="item.status===0?'':item.status===1?'danger':item.status===2?'info':'primary'" :disabled="item.status===1||item.status===2" >{{item.time}}
        </el-button>
             </span>
          <span v-if="(index+1)%5===0">

            <br>
          </span>
        </span>

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
.button_wrap{
  margin: 0 auto;
  width: 480px;
  display: flex;
  font-size: 18px;
  justify-content: space-between;
  margin-bottom: 20px;
}
.button_no{
  display: inline-block;
  line-height: 1;
  white-space: nowrap;
  background: #FFFFFF;
  color: #606266;
  -webkit-appearance: none;
  text-align: center;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  outline: none;
  margin: 0;
  -webkit-transition: 0.1s;
  transition: 0.1s;
  font-weight: 400;
  padding: 12px 20px;
  font-size: 14px;
  border-radius: 4px;
}
.calender {
  width: 500px;
  height: 300px;
  margin: 0 auto;

}

.calender_title {
  display: flex;
  width: 100%;
  height: 40px;
  line-height: 40px;
  text-align: center;
}
.arrow {
  width: 50px;
  height: 100%;
}
.data {
  font-size: 20px;
}
.title_span {
  width: calc(100% / 7);
  text-align: center;
  height: 40px;
  line-height: 40px;
}
.row {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.prevDay {
  color: #fff;
  background-color: #eee;
}

.content_span {
  width: calc(100% / 7);
  height: 30px;
  line-height: 30px;
  text-align: center;
}
.content_button{
  margin-bottom:5px;
  width:60px;
  margin-left:10px;
}

.calender_content {
  width: 100%;
  height: 250px;
}
.content {
  -webkit-box-pack: start;
  -ms-flex-pack: start;
  justify-content: flex-start;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
}
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