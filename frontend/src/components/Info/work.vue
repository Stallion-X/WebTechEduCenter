<template>
  <div>
    <h2 style="text-align: center">作业情况</h2>
    <el-select v-model="value" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option> </el-select
    >&nbsp;&nbsp;
    <el-button type="primary" @click="ok(value)">查询</el-button>
    <br />
    <br />
    <br />
    <br />
    <el-row :gutter="20">
      <el-col :span="6">
        <h5>单选题</h5>
        <br />
        <el-progress type="circle" :percentage="percentage1 * 4"></el-progress>
      </el-col>
      <el-col :span="6">
        <h5>多选题</h5>
        <br />
        <el-progress type="circle" :percentage="percentage2 * 4"></el-progress>
      </el-col>
      <el-col :span="6">
        <h5>判断题</h5>
        <br />
        <el-progress type="circle" :percentage="percentage3 * 4"></el-progress>
      </el-col>
      <el-col :span="6">
        <h5>简答题</h5>
        <br />
        <el-progress type="circle" :percentage="percentage4 * 4"></el-progress>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "work",
  data() {
    return {
      percentage1: 5,
      percentage2: 15,
      percentage3: 20,
      percentage4: 25,
      options: [
        {
          value: "选项1",
          label: "作业1",
        },
        {
          value: "选项2",
          label: "作业2",
        },
        {
          value: "选项3",
          label: "作业3",
        },
        {
          value: "选项4",
          label: "作业4",
        },
        {
          value: "选项5",
          label: "作业5",
        },
      ],
      value: "",
    };
  },
  methods: {
     ok(workid){
      this.percentage1=workid*5
      this.percentage2=workid*(-0.3)+25
      this.percentage3=workid*2+5
      this.percentage4=workid*1.3+9

      console.log(workid)
      let workdata=new FormData()
      workdata.append("workid",workid)
    }
  },
};
</script>

<style scoped>
</style>