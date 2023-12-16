<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="作业" name="/mWork"
      ><router-view></router-view
    ></el-tab-pane>
    <el-tab-pane label="实验" name="/mExperiment"
      ><router-view></router-view
    ></el-tab-pane>
    <el-tab-pane label="考试" name="/mExamination"
      ><router-view></router-view
    ></el-tab-pane>
  </el-tabs>
</template>

<script>
export default {
  data() {
    return {
      activeName: "",
    };
  },
  created() {
    this.activeName = this.$route.path;
  },
  methods: {
    handleClick(tab, event) {
      //console.log(tab, event);
      // if (this.activeName == "work") {
      //   this.$router.push("mWork");
      // }
      // else if (this.activeName == "lab") {
      //   this.$router.push("mExperiment");
      // }
      // else if (this.activeName == "exp") {
      //   this.$router.push("mExamination");
      // }
      this.$router.push(this.activeName);
    },
  },
};
</script>

<style>
</style>