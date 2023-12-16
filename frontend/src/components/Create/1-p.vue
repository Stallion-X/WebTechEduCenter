<template>
  <div>
    <el-row>
      <el-col :span="2">题目:</el-col>
      <el-col :span="22">
        <el-input type="textarea" autosize placeholder="" v-model="textarea">
        </el-input>
      </el-col>
    </el-row>
    <br />
    <br />
    <el-row>
      <el-alert
        type="warning"
        show-icon
        title="注：请将测试用例输入在下列输入框中"
      ></el-alert>
    </el-row>
    <br />
    <br />
    <el-row>
      <el-col :span="4">输入用例</el-col>
      <el-col :span="20"> <el-input v-model="input"></el-input></el-col>
    </el-row>
    <br />
    <br />
    <el-row>
      <el-col :span="4">输出用例</el-col>
      <el-col :span="20"> <el-input v-model="input1"></el-input></el-col>
    </el-row>
    <br />
    <br />
    <el-row>
      <el-row>
        <el-col :span="2">上传附件</el-col>
        <el-col :span="20">
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-change="handleChange"
            :file-list="fileList"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-col>
      </el-row>
      <br />
      <el-button type="success" @click="c()">保存</el-button>
    </el-row>
  </div>
</template>
  
<script>
export default {
  data() {
    return {
      textarea: "",
      input: "",
      input1: "",
    };
  },
  methods: {
    c() {
      console.log(this.textarea);
      console.log(this.value);
    },
  },
};
</script>
  