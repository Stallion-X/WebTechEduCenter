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
            <i class="el-icon-menu"></i>
            <span slot="title">{{item.navItem}}</span>
          </el-menu-item>

        </el-menu>
      </el-col>
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="15">
        <router-view></router-view>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
      navList:[
        {name:'/learn',navItem: '学习情况'},
        {name:'/student',navItem: '个人信息'},
        {name:'/imf',navItem: '修改信息'},
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