

<script>
import {ElButton, ElDrawer, ElMessageBox} from "element-plus";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import { computed, ref ,reactive , onMounted ,onBeforeUpdate,onUpdated ,getCurrentInstance} from 'vue'
import Store from '../../components/store/store.js'
import router from "../../components/tools/Router.js";

export default {
  components: {CircleCloseFilled, ElDrawer, ElButton},


  data() {
    return {

      look: false,
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
          is_active: 0,
        }

      ],
      isAppoint: false,
      appointForm: {
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

       dateArray :[] ,
       is_active: []
    };
  },







created() {
    console.log('created');
    this.fetchData();
  },
  computed: {
    aa() {
      var myDate = new Date(); // 获取今天日期
      myDate.setDate(myDate.getDate());

      var dateTempObj;
      var flag = 1;

      this.dateArray = []; // Initialize dateArray

      for (var i = 0; i < 7; i++) {
        dateTempObj = {
          date: myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate(),
          is_active: 0 // Default state
        };
        this.dateArray.push(dateTempObj);

        myDate.setDate(myDate.getDate() + flag);
      }
console.log(this.dateArray);
      return this.dateArray;
    },

  },
  methods: {

    routerlook() {

      router.push('/AppHome/third_page').then(() => {
        location.reload();})
    },
    changWeek(item, index) {

      if(Store.state.username===""){ this.visible = true}
      this.appointForm.date = item.date;
      this.dateArray.forEach(item => {
        item.is_active = 0;
      });
      item.is_active = 1;
      // Reset `status` state in timeArr
      this.timeArr.forEach(timeItem => {
        timeItem.status = 0;
      });
    },
    changTime(item, index) {
      this.timeArr.forEach(item => {
        item.status = 0;

      });
      item.status = 1;
      if(Store.state.username===""){

        this.visible = true}
      else{
      this.appointForm.time = item.time;
       }
    },

    fetchData() {
      this.$http.get("/getfacility" )
          .then(response => {

        this.facility=response.data;

      })

      },

    confirm  ()  {
      this.$http.get('/getreserve').then(res => {

      if(res.data.find(reserve=>
           reserve.reserveTime===this.appointForm.time && reserve.reserveDate===this.appointForm.date)){

        ElMessageBox.alert('该时间段已有预约，请选择其他时间段预约！')

        console.log('该时间段已有预约，请选择其他时间段预约！')
        this.dialogFormVisible = false;

        this.timeArr.status = 1;
      }
      else{
        console.log(Store.state.userID);
        this.$http.post('/addreserve', {
          "userID": Store.state.userID,
          "reserveDate":this.appointForm.date,
          "reserveTime": this.appointForm.time,
      "reserveGym": this.facility.facilityName,
        }).then((response) => {
          console.log(response.data);
          ElMessageBox.alert('Submission successful!')
          this.look=true;
            })
            .catch((error) => {

              console.log(error)
              ElMessageBox.alert('Submission failed!')

              // 处理错误

            })

        this.dialogFormVisible = false;
      }


    })
    },

    addfacility(index) {
      // Reset `is_active` state in facility array
      this.facility.forEach(facilityItem => {
        facilityItem.is_active = 0;
      });

      // Set `is_active` for the selected item
      this.facility[index].is_active = 1;

      // Assuming `facilityName` is part of the `facility` object or a property elsewhere
      this.facility.facilityName = this.facility[index].facilityName;

      console.log(this.facility);
    }

  },


};
</script>


<template>
<el-dialog v-model="look" title="Submitted successfully!" width="500">
  <p>Your reservation has been submitted successfully!</p>
  <p>You can check your reservation status in the "My Reservations" page.</p>
  <div style="text-align: right;">
    <el-button type="primary" @click="routerlook()">
      查看我的预约
    </el-button>
  </div>
</el-dialog>
  <el-dialog v-model="dialogFormVisible" title="请核对你的信息" width="500">
    <h3>请确认你的预约信息</h3>
    <h3>你选择的场馆为：{{facility.facilityName}}</h3>
    <h3>你选择的日期为：{{appointForm.date}}</h3>
    <h3>你选择的时间为：{{appointForm.time}}</h3>

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

      <el-link
          :underline="false"
          v-for="(item, index) in facility"
          :key="index"
          @click="addfacility(index)"
          :class="{'top_style': item.is_active === 0, 'top_active': item.is_active === 1}"
      >
        {{ item.facilityName }}
      </el-link>
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

            <span v-for="(item,index) in dateArray" :key="index">
              <el-button style="height:75px;line-height:20px;" @click="changWeek(item,index)"  :class="{'top_style':item.is_active===0,'top_active':item.is_active===1}">{{item.date}}</el-button>
            </span>

      </div>


      <div style="display:flex;margin:20px 50px;font-size:18px;justify-content:space-between;">
        <div style="display:flex;"><div style="background-color:#C8C9CC;width:40px;height:20px;margin-right:10px;"></div><div>不可预约</div></div>
        <div style="display:flex;"><div style="background-color:#ffa4a4;width:40px;height:20px;margin-right:10px;"></div><div>当前预约</div></div>
        <div style="display:flex;"><div style="background-color:#3EA7F1;width:40px;height:20px;margin-right:10px;"></div><div>已有预约</div></div>
      </div>

      <div class="time-buttons-container">
        <span v-for="(item,index) in timeArr" :key="index">
           <span>
        <el-button  @click="changTime(item,index)"
                    :type="item.status===0?'':item.status===1?'danger':item.status===2?'info':'primary'"
                    :disabled="item.status===1||item.status===2" >
          {{item.time}}
        </el-button>
             </span>
          <span v-if="(index+1)%5===0">
            <br>
          </span>
        </span>

      </div>
    </div>

    <el-button class="custom-btn btn" @click="dialogFormVisible = true">预约提交</el-button>
    <el-row :gutter="20">
      <el-col :span="18">

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

.time-buttons-container {
  margin: 20px 50px; /* 上下边距 */
  height: 150px;
  display: flex;
  flex-wrap: wrap;
  gap: 60px; /* 按钮之间的间距 */
}

.time-buttons-container .el-button {

  margin-bottom: 10px; /* 按钮之间的下边距 */
  border: 3px solid #ccc; /* 按钮的边框样式 */
}


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
/* From www.lingdaima.com */
.custom-btn {
  width: 130px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: absolute;

  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, .5),
  7px 7px 20px 0px rgba(0, 0, 0, .1),
  4px 4px 5px 0px rgba(0, 0, 0, .1);
  outline: none;
}

.btn {
  background: linear-gradient(0deg, rgba(255, 151, 0, 1) 0%, rgba(251, 75, 2, 1) 100%);
  line-height: 42px;
  padding: 0;
  border: none;
}

.btn span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}

.btn:before,
.btn:after {
  position: absolute;
  content: "";
  right: 0;
  bottom: 0;
  background: rgba(251, 75, 2, 1);
  box-shadow: -7px -7px 20px 0px rgba(255, 255, 255, .9),
  -4px -4px 5px 0px rgba(255, 255, 255, .9),
  7px 7px 20px 0px rgba(0, 0, 0, .2),
  4px 4px 5px 0px rgba(0, 0, 0, .3);
  transition: all 0.3s ease;
}

.btn:before {
  height: 0%;
  width: 2px;
}

.btn:after {
  width: 0%;
  height: 2px;
}

.btn:hover {
  color: rgba(251, 75, 2, 1);
  background: transparent;
}

.btn:hover:before {
  height: 100%;
}

.btn:hover:after {
  width: 100%;
}

.btn span:before,
.btn span:after {
  position: absolute;
  content: "";
  left: 0;
  top: 0;
  background: rgba(251, 75, 2, 1);
  box-shadow: -7px -7px 20px 0px rgba(255, 255, 255, .9),
  -4px -4px 5px 0px rgba(255, 255, 255, .9),
  7px 7px 20px 0px rgba(0, 0, 0, .2),
  4px 4px 5px 0px rgba(0, 0, 0, .3);
  transition: all 0.3s ease;
}

.btn span:before {
  width: 2px;
  height: 0%;
}

.btn span:after {
  height: 2px;
  width: 0%;
}

.btn span:hover:before {
  height: 100%;
}

.btn span:hover:after {
  width: 100%;

 .m-4{
   font-family: "Times New Roman";
   font-size: 20px;
 }







}
</style>