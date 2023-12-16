<template>
  <div>
    <el-page-header @back="$router.back()" content="自动组题"></el-page-header>
    <br />
    <el-row>
      <el-col :span="2">作业标题</el-col>
      <el-col :span="10">
        <el-input v-model="inputData.input" placeholder="请输入内容"></el-input
      ></el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="2">开始时间</el-col>
      <el-col :span="8">
        <el-date-picker
          v-model="inputData.value1"
          type="datetime"
          placeholder="开始时间"
          align="right"
          :picker-options="pickerOptions"
        >
        </el-date-picker>
      </el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">截止时间</el-col>
      <el-col :span="8">
        <el-date-picker
          v-model="inputData.value2"
          type="datetime"
          placeholder="截止时间"
          align="right"
          :picker-options="pickerOptions"
        >
        </el-date-picker>
      </el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">布置班级</el-col>
      <el-col :span="8">
        <el-select v-model="inputData.clazz" placeholder="请选择">
          <el-option
            v-for="item in clazzData"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-loading="loading"
          >
          </el-option>
        </el-select>
      </el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">单选题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="inputData.input3"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">多选题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="inputData.input4"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">简答题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="inputData.input5"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />

    <el-row>
      <el-col :span="4">填空题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="inputData.input6"></el-input></el-col>
      <el-col :span="1">道</el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="4">编程题：</el-col>
      <el-col :span="3">从题库中选：</el-col>
      <el-col :span="2"> <el-input v-model="inputData.input7"></el-input></el-col>
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
      inputData: {
        input: "",
        input1: "",
        input2: "",
        input3: "",
        input4: "",
        input5: "",
        input6: "",
        input7: "",
        clazz: "",
      },
      clazzData: [],
      loading: true,
      pickerOptions: {},
    };
  },
};
</script>
