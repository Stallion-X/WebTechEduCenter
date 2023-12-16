<template>
<div>
  <el-page-header @back="$router.back();" content="录入题库"></el-page-header>
  <br/>
    <el-row> 
          <el-col :span="2">实验标题</el-col>
          <el-col :span="8"><el-input v-model="input" ></el-input></el-col>
          
        </el-row>
        <br>
        <el-row><el-col :span="2">开始时间</el-col>
          <el-col :span="8">  
    
    <el-date-picker
      v-model="value1"
      type="datetime"
      placeholder="开始时间"
      align="right"
      :picker-options="pickerOptions">
    </el-date-picker>
  </el-col></el-row>
  <br>
  <el-row><el-col :span="2">截止时间</el-col>
          <el-col :span="8">  
    
    <el-date-picker
      v-model="value2"
      type="datetime"
      placeholder="截止时间"
      align="right"
      :picker-options="pickerOptions">
    </el-date-picker>
  </el-col></el-row>
  <br><br>
  <el-row>
    <el-col :span="2">实验内容</el-col>
    <el-col :span="20"> <el-input
  type="textarea"
  :rows="2"
  placeholder=""
  v-model="textarea1">
</el-input></el-col>
  </el-row>
  <br>
  <br>
  <el-row>
    <el-col :span="2">上传附件</el-col>
    <el-col :span="20">  
        <el-upload
  class="upload-demo"
  action="https://jsonplaceholder.typicode.com/posts/"
  :on-change="handleChange"
  :file-list="fileList">
  <el-button size="small" type="primary">点击上传</el-button>
 
</el-upload>
    </el-col>
  </el-row>
  <br>
<el-row><el-button type="success"  >保存</el-button></el-row>
</div>

</template>
<script>
export default {
  data() {
    return {
      text: '',
      textarea: '',
      input:'',
      value2:'',
      value1:'',
      textarea1:'',
      fileList:[]
      
    }
  }
}
</script>