<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/#/teacher">个人信息</a></el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
    <el-card>
      <el-descriptions class="margin-top" title="个人简介" :column="2" border>
        <template slot="extra">
          <el-button type="primary" size="small">编辑</el-button>
        </template>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-picture-outline"></i>
            头像
          </template>
          <img class="img" :src="avatar" alt="" />
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            用户名
          </template>
          {{ account }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>
            工号
          </template>
          {{ num }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-odometer"></i>
            年龄
          </template>
          {{ age }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-male"></i>
            <i class="el-icon-female"></i>
            性别
          </template>
          <el-tag size="small">{{ sex }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-message"></i>
            邮箱Email
          </template>
          {{ email }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号码
          </template>
          {{ mobilePhoneNumber }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-magic-stick"></i>
            个性签名
          </template>
          <h4>{{ design }}</h4>
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
</template>

<script>
// import { userInfo } from "@/api/user.js";
export default {
  name: "HelloWorld",
  data() {
    return {
      avatar: "/img/user12.jpg",
      account: "王",
      age: "94",
      email: "000",
      mobilePhoneNumber: "111111",
      num: "20201220",
      sex: "男",
      design: "白天有说有笑，晚上睡个好觉。",
    };
  },
  mounted() {
    if (this.login == true) {
      this.login = null;
    }
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
      this.loading = true;
      this.login = true;
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
      this.loading = true;
      this.login = true;
    }
    if (this.loading == true && this.loginData.type == 1) {
      this.load();
    }
  },
  methods: {
    userInfo(no) {
      return this.axios.get("http://127.0.0.1:8080/getTeacherByNo", {
        params: { no: no },
      });
    },
    load() {
      this.userInfo(this.loginData.teacher_no)
        .then((res) => {
          this.avatar = res.data.memo;
          this.account = res.data.teacher_name;
          //this.age = res.data.age;
          //this.email = res.data.email;
          //this.mobilePhoneNumber = res.data.mobilePhoneNumber;
          this.num = res.data.teacher_no;
          //this.sex = res.data.sex == 1 ? "男" : "女";
          //this.design = res.data.design;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.img {
  width: 80px;
  height: 80px;
}
</style>