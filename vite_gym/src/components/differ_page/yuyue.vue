<script>
import {ElButton, ElDrawer, ElMessageBox} from "element-plus";
import {CircleCloseFilled} from "@element-plus/icons-vue";
import { computed, ref ,reactive , onMounted ,onBeforeUpdate,onUpdated ,getCurrentInstance} from 'vue'
import Store from '../../components/store/store.js'

export default {
  components: {CircleCloseFilled, ElDrawer, ElButton},
  data() {
    return {
      appointForm1:{},//预约
      isAppoint1:false,//
      appointAreaId:'',//预约的路演厅id
      appointAreaName:'',//预约的路演厅name
      remark1:'',//备注
      appointTimeArr:[],//预约选中时间数组
      title: ["日", "一", "二", "三", "四", "五", "六"],
      timeArr1:[
          { status: 0 },
          { status: 0 },
          { status: 0 },
          { status: 0 },

        { status: 0 },
        { status: 0 },
        { status: 0 },
        { status: 0 },

        { status: 0 },
        { status: 0 },
        { status: 0 },
        { status: 0 },

        { status: 0 },
        { status: 0 },
        { status: 0 },
        { status: 0 },
        { status: 0 },
        { status: 0 },

        { status: 0 },
        { status: 0 },
        { status: 0 },
        { status: 0 },
        { status: 0 },

        { status: 0 },
          { status: 0 },
      ],
      dateNow:'',//预约年月
      timeStart:'',//预约开始日期
      timeEnd:'',//预约结束日期
      currentDay: new Date().getDate(),
      currentMonth: new Date().getMonth(),
      currentYear: new Date().getFullYear(),

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
  onBeforeUpdate() {
    console.log('before update');
  },

  onUpdated() {
    console.log('updated');

  },
  computed: {
    // 获取中文的月份    显示：8月
    currentMonthChinese() {
      return new Date(this.currentYear, this.currentMonth).toLocaleString(
          "default",{ month: "short" }
      );
    },
    currentDays() {
      // Date中的月份是从0开始的
      return new Date(this.currentYear, this.currentMonth + 1, 0).getDate();
    },
    prevDays() {
      // 获取上个月的最后一行的日期
      let data = new Date(this.currentYear, this.currentMonth, 0).getDate();
      let num = new Date(this.currentYear, this.currentMonth, 1).getDay();
      const days = [];
      while (num > 0) {
        days.push(data--);
        num--;
      }
      return days.sort();
    },
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
      fetch("http://localhost:9990/facilityName" )
          .then(response => response.json()).then(response => {

        console.log(response);

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


    /* 以下日历相关*/
    //日历点击事件
    changTime1(val,index){
      if(this.appointTimeArr.length<2){
        this.timeArr1[index].status=3;
        this.appointTimeArr.push(index);
        if(this.appointTimeArr.length===1){
          this.timeStart=this.appointTimeArr[0];
          this.timeEnd=this.appointTimeArr[0];
        }else if(this.appointTimeArr.length===2){
          if(this.appointTimeArr[0]===this.appointTimeArr[1]){
            this.timeArr1[this.appointTimeArr[0]].status=0;
            this.appointTimeArr=[];
          }else{
            this.appointTimeArr=this.appointTimeArr.sort(function(a,b){return a-b});
            let len=this.appointTimeArr[1]-this.appointTimeArr[0];
            for(let i=0;i<len;i++){
              if(this.timeArr1[this.appointTimeArr[0]+i].status===1){
                this.$message.warning("已预约过的时间不允许预约！")
                this.timeStart='';
                this.timeEnd='';
                break
              }else{
                this.timeArr1[this.appointTimeArr[0]+i].status=3;
                this.timeStart=this.timeArr1[this.appointTimeArr[0]].time;
                this.timeEnd=this.timeArr1[this.appointTimeArr[1]].time;
              }
            }

          }
        }
      }else if(this.appointTimeArr.length === 3){
        for(let i=0;i<this.timeArr1.length;i++){
          if(this.timeArr1[i].status===3){
            this.timeArr1[i].status=0;
          }
        }
        this.appointTimeArr=[];
        this.appointTimeArr.push(index);
        this.timeArr1[index].status=3;
      }
    },
    //点击左侧箭头
    prev() {
      // 点击上个月，若是0月则年份-1
      // 0是1月  11是12月
      if (this.currentMonth === 0) {
        this.currentYear -= 1;
        this.currentMonth = 11;
      } else {
        this.currentMonth--;
      }
      let date=this.currentYear+'-'+(this.currentMonth+1);
      let formData={
        appointAreaId:this.appointAreaId,
        startTime:this.getFirst(date)+' '+"00:00:00",
        endTime:this.getLast(date)+' '+"23:59:59"
      }
      this.dateNow=date;
      this.getAppointed(formData)
    },
    //点击右侧箭头
    next() {
      if (this.currentMonth === 11) {
        this.currentYear++;
        this.currentMonth = 0;
      } else {
        this.currentMonth++;
      }
      let date=this.currentYear+'-'+(this.currentMonth+1);
      let formData={
        appointAreaId:this.appointAreaId,
        startTime:this.getFirst(date)+' '+"00:00:00",
        endTime:this.getLast(date)+' '+"23:59:59"
      }
      this.dateNow=date;
      this.getAppointed(formData)
    },
    /* 以上日历相关*/
    getYM(time){
      let date = new Date(time)
      return date.getFullYear() + '-' +
          (date.getMonth() + 1)
    },
    getFirst(time){
      let date = new Date(time)
      return date.getFullYear() + '-' +
          (date.getMonth() + 1) + '-' +
          date.getDate()
    },
    getLast(time){
      var y = new Date(time).getFullYear(); //获取年份
      var m = new Date(time).getMonth() + 1; //获取月份
      var d = new Date(y, m, 0).getDate(); //获取当月最后一日
      let Str=y + '-' +m + '-' + d
      return Str
    },
    //获取时间数组
    getAppointed(formData){
      appointTime(formData).then(res=>{
        this.timeArr1=res.data.data;
        if(res.data.code===200){
          this.timeArr1=res.data.data;
        }
      })
    },
    //预约
    addAppoint(val){
      this.isAppoint1=true;
      this.appointAreaId=val.appointAreaId;
      this.appointAreaName=val.appointAreaName;
      this.positionId=val.positionId;
      this.positionName=val.positionName;
      let formData={
        appointAreaId:val.appointAreaId,
        startTime:this.getFirst(this.getYM(new Date()))+' '+'00:00:00',
        endTime:this.getLast(this.getYM(new Date()))+' '+'23:59:59'
      }
      this.dateNow=this.getYM(new Date());
      this.getAppointed(formData);
    },
    saveAppoint(){
      if(this.timeStart!==''&&this.timeEnd!=''){
        this.appointForm.appointAreaId=this.appointAreaId;
        this.appointForm.appointAreaName=this.appointAreaName;
        this.appointForm.positionId=this.positionId;
        this.appointForm.positionName=this.positionName;
        this.appointForm.remark1=this.remark1;
        this.appointForm.startTime=this.timeStart+' '+"00:00:00";
        this.appointForm.endTime=this.timeEnd+' '+"23:59:59";
        appoint(this.appointForm).then(res=>{
          if(res.data.code===200){
            this.$message.success(res.data.message)
            this.remark1='';
            this.currentDay=new Date().getDate();
            this.currentMonth=new Date().getMonth();
            this.currentYear=new Date().getFullYear();
            this.isAppoint1=false;
            this.isMeeting=false
            this.getList();
          }else{
            this.$message.error(res.data.message)
          }
        })
      }else{
        this.$message.error("请选择预约时间")
        for(let i=0;i<this.timeArr1.length;i++){
          if(this.timeArr1[i].status===3){
            this.timeArr1[i].status=0
          }
        }
      }
    },
    closeAppoint(){
      this.isAppoint1=false
      this.remark1='';
      for(let i=0;i<this.timeArr1.length;i++){
        if(this.timeArr1[i].status===3){
          this.timeArr1[i].status=0;
        }
      }
      this.currentDay=new Date().getDate();
      this.currentMonth=new Date().getMonth();
      this.currentYear=new Date().getFullYear();
    },
  }
  ,

  onMounted() {
    console.log('mounted');
    this.fetchData();
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
    <h3>你选择的场馆为：{{facility[0].name}}</h3>
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
          @click="isAppoint1=true"
      />
      <el-button type="primary" @click="isAppoint1=true">预约</el-button>
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
        <span v-if="(index+1)%5===0">
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