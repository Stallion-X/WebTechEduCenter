<template>
  <div style="top: 35%" >
    <el-page-header @back="goBack" content="个人信息"></el-page-header>
    <el-row :gutter="20">
      <el-col :span="2">&nbsp;
      </el-col>
      <el-col :span="10">
        <el-avatar :src="imgurl" style="width: 160px;height: 160px"></el-avatar>
      </el-col>
      <el-col :span="10">
        <h5>姓名：{{name}}</h5>
        <h5>学号：{{num}}</h5>
        <h5>班级：{{clazz}}</h5>
        <h5>学校：{{school}}</h5>
      </el-col>
      <el-col :span="6">
      </el-col>
      <el-col :span="6">
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Student",
  data(){
    return{
      name:"ads",
      imgurl:"/img/user12.jpg",
      num:"17201206",
      clazz:"202012",
      school:"南昌航空大学"
    }
},
methods: {
    goBack() {
        this.$router.back();
      }
  }
}
</script>

<style scoped>

</style>