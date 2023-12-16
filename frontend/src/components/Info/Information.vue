<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>修改信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- <el-page-header @back="goBack" content="修改信息"></el-page-header> -->
    <el-row :gutter="20">
      <el-col :span="2">&nbsp;
      </el-col>
      <el-col :span="10">
        <div>
          <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
      </el-col>
      <el-col :span="10">
        <br>
        <el-input placeholder="请输入密码" v-model="input" show-password></el-input>
        <br>
        <el-input placeholder="再次请输入密码" v-model="input2" show-password></el-input>
        <el-button type="success" plain>成功按钮</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Information",
    data() {
      return {
        imageUrl: '',
        input: '',
        input2: '',
        async sign(){
          if(this.input!=this.input2){
            await this.$notify.error({
              title: '错误',
              message: '两次密码不一致'
            });
          }
        },
      };
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      goBack() {
        this.$router.back();
      }
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