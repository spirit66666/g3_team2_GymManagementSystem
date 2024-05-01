<template>


    <el-container style="height: 100%">
      <el-main style="padding:0; margin:50px">
        <router-view></router-view>



      </el-main>
    </el-container>
  </template>

<script>
export default {
  name: 'AppBody'
}
</script>
<style scoped>
.el-menu-item is-active {
background-color: darkorange !important;
}
.container {
  margin: 50px;
}
.content {
  margin: 20px;
}
.tab {
  width: 300px;
  position: absolute;
}
.item {
  border: gray 1px solid;
  width: 148px;
  text-align: center;
  transition: all 0.8s ease;
  display: inline-block;
}
.list-enter-active {
  transition: all 1s ease;
}

.list-enter-from,
.list-leave-to {
  opacity: 0;
}

.list-move {
  transition: transform 1s ease;
}
.list-leave-active {
  position: absolute;
  transition: all 1s ease;
}
</style>