<template>
  <div>
    <h2>修改信息</h2>
    <el-row :gutter="20">
      <el-col :span="2">&nbsp;
      </el-col>
      <el-col :span="5">
          <el-avatar :src="imageUrl" style="width: 160px;height: 160px"></el-avatar>
      </el-col>
      <el-col :span="10">
        <br>
        <br>
        <el-input placeholder="请输入密码" v-model="input" show-password></el-input>
        <br>
        <br>
        <el-input placeholder="再次请输入密码" v-model="input2" show-password></el-input>
      </el-col>
    </el-row>
    <br>
    <br>
    <el-button type="success" plain v-on:click="sign()">修改</el-button>
  </div>
</template>
<script>
export default {
  name: "imf",
  succ:false,
    data() {
      return {
        imageUrl: "/img/sc.jpg",
        input: '',
        input2: '',
      };
    },
    methods: {
      async sign(){
        if(this.input!=this.input2){
          await this.$notify.error({
            title: '错误',
            message: '两次密码不一致'
          });
        }else {
          let imfData=new FormData()
          imfData.append("password", this.input);
          await this.axios
              .post("http://127.0.0.1:8080/imf", imfData, {
                headers: {"Content-Type": "multipart/form-data"},

              }).then((res) => {
                this.$message({
                  message: "修改成功",
                  type: "success",
                })
              })
        }
      },
    }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>