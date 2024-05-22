
<script setup>
import { ref } from 'vue'
import { ElCarousel, ElCarouselItem } from 'element-plus'
import 'element-plus/dist/index.css'

const items = ref([
  { src: new URL('../../assets/640.gif', import.meta.url).href },
  { src: new URL('../../assets/640.gif', import.meta.url).href },
  { src: new URL('../../assets/640.gif', import.meta.url).href },
  { src: new URL('../../assets/640.gif', import.meta.url).href }
])
</script>


<template>

  <el-carousel :interval="4000" type="card" height="400px">
    <el-carousel-item v-for="(item, index) in items"  :key="index">
      <img :src="item.src" style="width: 100%; height: 100%;"  alt=""/>
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
