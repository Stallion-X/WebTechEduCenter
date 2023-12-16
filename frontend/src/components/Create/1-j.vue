<template>
  <div>
    <el-row>
      <el-col :span="2">题目:</el-col>
      <el-col :span="22">
        <el-input type="textarea" autosize placeholder="" v-model="textarea">
        </el-input
      ></el-col>
    </el-row>
    <br />
    <br />
    <el-row>
      <el-col :span="2"></el-col>
      <el-col :span="22"
        ><el-alert
          type="warning"
          show-icon
          title="注：请将参考答案填在下列输入框"
        ></el-alert
      ></el-col>
    </el-row>
    <br />
    <br />
    <el-row>
      <el-col :span="2">参考答案:</el-col>
      <el-col :span="22">
        <el-input type="textarea" :rows="2" placeholder="" v-model="textarea1">
        </el-input
      ></el-col>
    </el-row>
    <br /><br />
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
    <el-row><el-button type="success" @click="d()">保存</el-button></el-row>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      textarea: "",
      textarea1: "",
    };
  },
  methods: {
    d() {
      console.log(this.textarea);
      console.log(this.textarea1);
    },
  },
};
</script>
  