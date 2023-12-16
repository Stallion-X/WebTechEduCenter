<template>
  <div>
    <el-page-header @back="$router.back()" content="自动组卷"></el-page-header>
    <br />
    <el-row>
      <el-col :span="1">标题</el-col>
      <el-col :span="10">
        <el-input v-model="input" placeholder="请输入内容"></el-input
      ></el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="1">总分</el-col>
      <el-col :span="2"> <el-input v-model="input1"></el-input></el-col>
      <el-col :span="1">分</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="1">时间</el-col>
      <el-col :span="2"> <el-input v-model="input2"></el-input></el-col>
      <el-col :span="1">h</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">单选题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="input3"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">多选题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="input4"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">填空题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="input5"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">简答题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="input6"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="4">编程题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="input7"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />
    <el-row><el-button type="success">生成</el-button></el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      input: "",
      input1: "",
      input2: "",
      input3: "",
      input4: "",
      input5: "",
      input6: "",
      input7: "",
    };
  },
};
