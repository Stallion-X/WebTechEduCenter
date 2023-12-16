<template>
  <div style="text-align: center">
    <el-row class="tac">
      <el-col :span="4">
        <h2>个人主页</h2>
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            router
        >
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            <span slot="title">{{item.navItem}}</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="15">
        <router-view>123123</router-view>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "home",
  data(){
    return{
      navList:[
        {name:'/user',navItem: '个人信息'},
        {name:'/imf',navItem: '修改信息'},
        {name:'/work',navItem: '作业查询'},
        {name:'/expe',navItem: '实验查询'},
        {name:'/exam',navItem: '考试查询'},
      ]
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>
<style scoped>
</style>