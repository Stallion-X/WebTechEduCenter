<template>
  <div id="login">
    <br />
    <h1>前端学习平台</h1>
    <div
      id="app"
      style="
        text-align: center;
        background: #fffafa;
        width: 290px;
        height: 480px;
        position: absolute;
        top: 20%;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
      "
    >
      <div v-if="seen1">
        <h3>登录</h3>
        <br />
        <br />
        <br />
        <el-input v-model="input1" placeholder="请输入账号"></el-input>
        <br />
        <el-input
          v-model="input2"
          placeholder="请输入密码"
          show-password
        ></el-input>
        <br />
        <template>
          <el-radio v-model="radio" label="0">学生</el-radio>
          <el-radio v-model="radio" label="1">教师</el-radio>
        </template>
        <br />
        <br />
        <el-button
          v-on:click="login()"
          type="primary"
          style="width: 120px; height: 40px"
          >登录</el-button
        >
        <el-button type="success" style="width: 120px; height: 40px"
          >忘记密码</el-button
        >
        <br />
        <br />
        <el-button
          v-on:click="
            seen1 = !seen1;
            seen2 = !seen2;
          "
          type="warning"
          plain
          style="width: 260px; height: 40px"
        >
          去注册</el-button
        >
      </div>
      <div v-if="seen2">
        <h3>注册</h3>
        <br />
        <br />
        <template>
          <el-radio v-model="radio" label="0">学生</el-radio>
          <el-radio v-model="radio" label="1">教师</el-radio>
        </template>
        <br />
        <br />
        <el-tooltip
          class="item"
          effect="dark"
          content="输入8位数字"
          placement="right"
        >
          <el-input v-model="input3" placeholder="请输入学号/工号"></el-input>
        </el-tooltip>
        <br />
        <el-tooltip
          class="item"
          effect="dark"
          content="输入真实姓名"
          placement="right"
        >
          <el-input v-model="input4" placeholder="请输入姓名"></el-input>
        </el-tooltip>
        <br />
        <el-tooltip
          class="item"
          effect="dark"
          content="输入8位以上数字"
          placement="right"
        >
          <el-input
            v-model="input5"
            placeholder="请输入密码"
            show-password
          ></el-input>
        </el-tooltip>
        <br />
        <el-tooltip
          class="item"
          effect="dark"
          content="输入8位以上数字"
          placement="right"
        >
          <el-input
            v-model="input6"
            placeholder="请再次输入密码"
            show-password
          ></el-input>
        </el-tooltip>
        <br />
        <el-button
          v-on:click="sign()"
          type="primary"
          style="width: 120px; height: 40px"
          >注册</el-button
        >
        <br />
        <br />
        <el-button
          v-on:click="
            seen1 = !seen1;
            seen2 = !seen2;
          "
          type="warning"
          plain
          style="width: 260px; height: 40px"
        >
          去登陆</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      radio: "0",
      seen1: true,
      seen2: false,
      input1: "",
      input2: "",
      input3: "",
      input4: "",
      input5: "",
      input6: "",
    };
  },
  methods: {
    login() {
      let logData = new FormData();
      logData.append("username", this.input1);
      logData.append("password", this.input2);
      logData.append("type", this.radio);
      this.axios
        .post("http://127.0.0.1:8080/login", logData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res.data);
          console.log(res);
          if (res.data.status_code == 1) {
            this.$message.error(res.data.info);
          } else {
            //location.reload();
            this.$message({
              message: "登录成功",
              type: "success",
            });
            if (this.radio == 0) {
              this.axios
              .get("http://127.0.0.1:8080/getStudentByNo", {
                params: { no: this.input1 },
              })
              .then((response) => {
                this.$store.state.loginData = response.data;
                this.$store.state.loginData.type = 0;
                this.$router.push({ path: "/" });
              })
              .catch((response) => {
                this.$message.error("网络错误");
              });
            }
            else {
              this.axios
              .get("http://127.0.0.1:8080/getTeacherByNo", {
                params: { no: this.input1 },
              })
              .then((response) => {
                this.$store.state.loginData = response.data;
                this.$store.state.loginData.type = 1;
                this.$router.push({ path: "/" });
              })
              .catch((err) => {
                this.$message.error("网络错误");
              });
            }
          }
        })
        .catch((err) => {
          this.$message.error("网络错误");
        });
    },
    async sign() {
      var reg = /^[0-9]{8}$/;
      var reg2 = /^[A-Za-z0-9]{8,32}$/;

      if (!reg2.test(this.input5)) {
        this.input5 = "";
        await this.$notify({
          title: "警告",
          message: "请输入8位以上作为密码",
          type: "warning",
        });
        return;
      }

      if (!reg.test(this.input3)) {
        this.input3 = "";
        await this.$notify({
          title: "警告",
          message: "请输入8位数字组成的学号或者工号",
          type: "warning",
        });
        return;
      }
      if (this.input5 != this.input6) {
        await this.$notify.error({
          title: "错误",
          message: "两次密码不一致",
        });
        return;
      }
      let regData = new FormData();
      regData.append("username", this.input3);
      regData.append("nickname", this.input4);
      regData.append("password", this.input5);
      regData.append("type", this.radio);
      this.axios
        .post("http://127.0.0.1:8080/signup", regData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.status_code == 0) {
            //location.reload();
            this.$message({
              message: "注册成功",
              type: "success",
            });
          } else {
            this.$message.error("注册失败");
          }
        }).catch((err)=>{
          this.$message.error(err);
        });
    },
  },
};
</script>
<style>
body {
  margin: 0px;
}
</style>
<style scoped>
#login {
  width: 100%;
  height: 100%;
  position: absolute;
  text-align: center;
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
}
.el-input {
  width: 260px;
  height: 50px;
}
@media (max-width: 480px) {
  
}
</style>