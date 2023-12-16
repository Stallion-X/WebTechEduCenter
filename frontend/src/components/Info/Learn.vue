<template>
  <div style="top: 35%">
    <el-empty description="请登录" v-if="!loginData"></el-empty>
    <div v-else>
      <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item v-if="loginData.type == 0"><a href="/">学习情况</a></el-breadcrumb-item>
      <el-breadcrumb-item v-else><a href="/">教学情况</a></el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="20">
      <el-col :span="6">
        <h4>课程进度</h4>
        <el-progress
          type="circle"
          :percentage="100"
          status="success"
        ></el-progress>
      </el-col>
      <el-col :span="6">
        <h4>作业平均点</h4>
        <el-progress type="circle" :percentage="25"></el-progress>
      </el-col>
      <el-col :span="6">
        <h4>考试进度</h4>
        <el-progress
          type="circle"
          :percentage="50"
          status="exception"
        ></el-progress>
      </el-col>
      <el-col :span="6">
        <h4>实验进度</h4>
        <el-progress
          type="circle"
          :percentage="100"
          status="success"
        ></el-progress>
      </el-col>
    </el-row>
    </div>
  </div>
</template>
<script>
export default {
  name: "Learn",
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
  },
  methods: {
    goBack() {
      this.$router.back();
    },
  },
};
</script>

<style scoped>
body {
  text-align: center;
}
</style>