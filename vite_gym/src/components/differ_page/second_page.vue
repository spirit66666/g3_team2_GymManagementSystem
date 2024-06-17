<template>

  <div class="box" @click="showContent('content1')">
    <p>体育馆信息</p>
  </div>
  <div class="box" @click="showContent('content2')">
    <p>体育馆营业时间</p>
  </div>
  <div class="box" @click="showContent('content3')">
    <p>各项运动介绍</p>
  </div>
  <div class="box" @click="showContent('content4')">
    <p>推荐训练计划</p>
  </div>

  <div class="content" id="content1">
    <p>本体育馆为北京理工大学珠海学院校内体育馆，是一个为学生提供健身和休闲活动的理想场所。
      设施齐全，包括健身房、篮球场、羽毛球场等，满足不同运动需求。开放时间灵活，
      适应学生的课程安排。专业的教练团队提供个性化的训练计划，帮助学生达成健康目标。
      环境友好，氛围轻松，是学生们放松身心，锻炼体魄的理想场所。<br><br>详情请致电:13333333
    </p>
  </div>
  <div class="content" id="content2">
    <p>本体育馆营业时间为:<br><br>周一至周日 9:00~23:00</p>
  </div>
  <div class="content" id="content3">
    <p>本体育馆的运动场所包括</p>
  </div>
  <div class="content" id="content4">
    <p>练胸<br>
      平板杠铃卧推 6组 X 10次&nbsp;&nbsp;&nbsp;&nbsp;上斜杠铃卧推 4组 X10次&nbsp;&nbsp;&nbsp;&nbsp;绳索夹胸 4组 X 10次<br>
      下斜哑铃卧推 4组 X 10次&nbsp;&nbsp;&nbsp;&nbsp;双杠臂屈伸 4组 X 10次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;器械推胸 4组 X 10次
      <br>
      <br>
       练背:<br>
      引体向上 4组 X 力竭&nbsp;&nbsp;&nbsp;&nbsp;硬拉 6组 X 10次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高位下拉 4组 X 12次<br>
      杠铃划船 4组 X 10次&nbsp;&nbsp;&nbsp;&nbsp坐姿器械划船 4组 X 10次&nbsp;&nbsp;&nbsp;单臂哑铃划船 4组 X 10次
      <br>
      <br>
      练肩:<br>
      哑铃肩上推举 4组 X 12次 &nbsp;&nbsp;&nbsp; 哑铃侧平举 4组 X 12次 &nbsp;&nbsp;&nbsp;哑铃前平举 4组 X 12次<br>
      俯身哑铃飞鸟 4组 X 10次 &nbsp;&nbsp;&nbsp; 器械推肩 4组 X 12次 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 绳索面拉 4组 X 12次<br>
      腹肌训练收尾 20分钟
      <br>
      <br>
      臀腿: <br>
      杠铃深蹲 6组 X 10次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;器械蹬腿 5组 X 10次&nbsp;&nbsp;&nbsp;坐姿腿屈伸 4组 X 10次<br>
      俯身腿弯举 4组 X 10次&nbsp;&nbsp;&nbsp;站姿提踵 4组 X 10次&nbsp;&nbsp;&nbsp;臀桥 5组 X 10次
      <br>
      <br>
      手臂: <br>
      正手引体 4组 X 每组力竭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;哑铃弯举 4组 X 12次&nbsp;&nbsp;&nbsp;牧师椅杠铃弯举 4组 X 12次<br>
      哑铃颈后臂屈伸 4组 X 12次 &nbsp;&nbsp;双杠臂屈伸 4组 X 10次 &nbsp;&nbsp;&nbsp;三头绳索下压 4组 X 12次<br>
      腹肌训练收尾
    </p>

    <div class="images">
    <img src="../../assets/健身1.jpg" style="width: 160px; height: 90px;">
    <img src="../../assets/健身2.jpg" style="width: 160px; height: 90px;">
    <img src="../../assets/健身3.jpg" style="width: 160px; height: 90px;">
    <img src="../../assets/健身4.jpg" style="width: 160px; height: 90px;">
    <img src="../../assets/健身5.jpg" style="width: 160px; height: 90px;">
  </div>
  </div>

</template>




<script>

export default {
  methods: {
    showContent(id) {
      // 隐藏所有内容容器
      var contents = document.querySelectorAll('.content');
      contents.forEach(function(content) {
        content.style.display = 'none';
      });

      // 显示特定 ID 的内容容器
      var content = document.getElementById(id);
      content.style.display = 'block';
    }
  }
}

</script>

<style>


.box {
  border: 4px solid #000;
  padding: 20px;
  margin: 20px;
  border-radius: 50px;
  width: 150px;
  font-size: 18px;
  cursor: pointer;
  transition: transform 0.2s, background-color 0.2s;
}

/* 悬停效果 */
.box:hover {
  background-color: #f0f0f0;
  transform: scale(1.1);
}

/* 点击效果 */
.box:active {
  transform: scale(0.95);
}

.content {
  font-size: 16px;
  width: 800px;
  border-radius: 10px;

  display: none; /* 初始隐藏 */
  position: absolute; /* 绝对定位 */
  top: 160px; /* 从顶部向下偏移 16px */
  left: 500px; /* 从左侧向右偏移 500px */
  padding: 20px;
  border: 4px solid #000;
}

.images {
  position: absolute; /* 绝对定位 */
  top: 30px; /* 从顶部开始 */
  right: 40px; /* 从左侧开始 */
}
.images img {
  display: block; /* 设置图片为块级元素，使其垂直排列 */
  margin-bottom: 5px; /* 图片之间的间距 */
}



</style>

