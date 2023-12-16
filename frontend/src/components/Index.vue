<template>
  <el-scrollbar>
      <el-container style="max-height:700px">
        <el-header><top></top></el-header>
        <el-container>
          <el-aside><left></left></el-aside>
          <el-main><router-view></router-view></el-main>
        </el-container>
        <el-footer></el-footer>
      </el-container>
  </el-scrollbar>
</template>

<script>
export default {
  created() {
    if (this.$store.state.loginData) {
      console.log(this.$store.state.loginData);
      localStorage.setItem(
        "loginData",
        JSON.stringify(this.$store.state.loginData)
      );
    }
    if (localStorage.getItem("loginData")) {
      console.log(JSON.parse(localStorage.getItem("loginData")));
    }
  },
};
</script>

<style scoped>
</style>