<script>
export default {
  name: "zhuye",

  data() {
    return {
      msg: "Welcome to Vue.js",
      items: [
        { url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' },
        { url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' },
        { url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' },
        { url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' },
        { url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' },
      ]
    }
}}

</script>

<template>
  <el-carousel :interval="4000" type="card" height="400px">
    <el-carousel-item v-for="item in items" :key="item">
      <img :src="items[0].url" style="width: 100%; height: 100%;" />
      <img :src="items[1].url" style="width: 100%; height: 100%;" />
    </el-carousel-item>
  </el-carousel>
</template>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
