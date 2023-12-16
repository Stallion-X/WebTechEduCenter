<template>
  <el-scrollbar>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      router
    >
      <el-menu-item index="/">
        <i class="el-icon-setting"></i>
        <span slot="title">概况</span>
      </el-menu-item>
      <el-submenu index="1" v-if="user_type == 0">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>个人中心</span>
        </template>
        <el-menu-item v-for="(item, i) in navList" :key="i" :index="item.name">
          <i class="el-icon-menu"></i>
          <span slot="title">{{ item.navItem }}</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu index="1" v-if="user_type == 1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>个人中心</span>
        </template>
        <el-menu-item v-for="(item, i) in navList1" :key="i" :index="item.name">
          <i class="el-icon-menu"></i>
          <span slot="title">{{ item.navItem }}</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu index="2" v-if="user_type == 0">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>开始学习</span>
        </template>
        <el-menu-item index="/Work"
          ><i class="el-icon-notebook-1"></i>作业</el-menu-item
        >
        <el-menu-item index="/Experiment"
          ><i class="el-icon-s-platform"></i>实验</el-menu-item
        >
        <el-menu-item index="/Examination"
          ><i class="el-icon-tickets"></i>考试</el-menu-item
        >
      </el-submenu>
      <el-submenu index="2" v-if="user_type == 1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>开始工作</span>
        </template>
        <el-menu-item index="/createCourse">
          <template slot="title"
            ><i class="el-icon-collection"></i>课程创建
          </template>
        </el-menu-item>
        <el-menu-item index="/createWork">
          <template slot="title"
            ><i class="el-icon-message-solid"></i>录入作业
          </template>
        </el-menu-item>
        <el-menu-item index="/createLib">
          <template slot="title"
            ><i class="el-icon-message-solid"></i>录入实验
          </template>
        </el-menu-item>
        <el-menu-item index="/createTest">
          <template slot="title"
            ><i class="el-icon-message-solid"></i>录入考试
          </template>
        </el-menu-item>
        <el-menu-item index="/markingList">
          <template slot="title"
            ><i class="el-icon-message-solid"></i>批改
          </template>
        </el-menu-item>
      </el-submenu>
      <el-submenu index="3" v-if="user_type == 2">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>管理员</span>
        </template>
        <el-menu-item v-for="(item, i) in navList2" :key="i" :index="item.name">
          <i class="el-icon-menu"></i>
          <span slot="title">{{ item.navItem }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </el-scrollbar>
</template>

<script>
export default {
  name: "Nav",
  data() {
    return {
      user_type: 0,
      navList: [
        { name: "/user", navItem: "个人信息" },
        { name: "/imf", navItem: "修改信息" },
        { name: "/work1", navItem: "作业查询" },
        { name: "/expe", navItem: "实验查询" },
        { name: "/exam", navItem: "考试查询" },
      ],
      navList1: [
        { name: "/teacher", navItem: "个人信息" },
        { name: "/class", navItem: "班级信息" },
      ],
      navList2: [
        { name: "/learn", navItem: "学习情况" },
        { name: "/student", navItem: "个人信息" },
        { name: "/imf", navItem: "修改信息" },
      ],
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
    if (this.loginData) {
      this.user_type = this.loginData.type;
    }
    // if (localStorage.getItem("navIndex")) {
    //   this.loginData = localStorage.getItem("navIndex");
    // }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
  computed: {
    activeIndex() {
      return "/"+this.$route.path.split("/").reverse()[0];
    }
  },
  watch: {
    $route(to, from) {
      // console.log("✅ to.path", to, to.path);
      // console.log("✅ from.path", from, from.path);
      if (from.path == "/createWork" || from.path == "/createTest") {
        this.$store.state.fromPath = from.path;
      }
    },
  },
  // beforeDestroy() {
  //   localStorage.setItem("navIndex",this.activeIndex);
  // }
};
</script>

<style>
</style>