<template>
  <div>
    <img :src="imgurl" @click="$router.push('/')" />
    <el-button type="text" @click="exit()" class="right-btn" v-if="loginData"
      >退出</el-button
    >
    <el-button
      type="text"
      @click="$router.push('/user')"
      class="right-btn"
      v-if="loginData&&loginData.type == 0"
      >欢迎您，{{ loginData.student_name }}&nbsp;&nbsp;</el-button
    >
    <el-button
      type="text"
      @click="$router.push('/user')"
      class="right-btn"
      v-else-if="loginData&&loginData.type == 1"
      >欢迎您，{{ loginData.teacher_name }}&nbsp;&nbsp;</el-button
    >
    <el-button
      type="text"
      @click="$router.push('/login')"
      class="right-btn"
      v-else
      >登录</el-button
    >
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgurl: "../../img/logo.png",
      loginData: null,
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
  },
  methods: {
    exit() {
      localStorage.removeItem("loginData");
      location.reload();
    },
  },
};
</script>

<style scoped>
img {
  padding: 12px 20px;
}
.right-btn {
  float: right;
  height: 100%;
  line-height: 50px;
  /* margin-left: auto; */
}
</style>