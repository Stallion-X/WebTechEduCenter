<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="courseForm"
  >
    <el-form-item label="课程名称" prop="name">
      <el-input v-model="ruleForm.name" placeholder="前端技术"></el-input>
    </el-form-item>
    <el-form-item label="课程教师" prop="tname">
      <el-input
        v-model="ruleForm.tid"
        :placeholder="loginData.teacher_name"
        :value="loginData.teacher_id"
        :disabled="true"
      ></el-input>
    </el-form-item>
    <el-form-item label="选择学期" prop="region">
      <el-col :span="4">
        <!-- <el-select
          v-model="ruleForm.region"
          placeholder="请选择"
          clearable
          filterable
          @click.native="loadTerms()"
          @blur="clearTerms()"
          @dblclick="clearTerms()"
        > -->
        <el-select
          v-model="ruleForm.region"
          placeholder="请选择"
          clearable
          filterable
          @click.native.once="loadTerms()"
        >
          <el-option
            v-for="item in termList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-loading="termsLoading"
          ></el-option>
        </el-select>
      </el-col>
    </el-form-item>
    <el-form-item label="课程封面" prop="pic">
      <el-col :span="4">
        <el-upload action="#" list-type="picture-card" :auto-upload="false">
          <i slot="default" class="el-icon-plus"></i>
          <div slot="file" slot-scope="{ file }">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url"
              alt=""
            />
            <span class="el-upload-list__item-actions">
              <span
                class="el-upload-list__item-preview"
                @click="handlePictureCardPreview(file)"
              >
                <i class="el-icon-zoom-in"></i>
              </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleDownload(file)"
              >
                <i class="el-icon-download"></i>
              </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleRemove(file)"
              >
                <i class="el-icon-delete"></i>
              </span>
            </span>
          </div>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="" />
        </el-dialog>
      </el-col>
    </el-form-item>

    <el-form-item>
      <el-col :span="6">
        <el-button type="primary" @click="submitForm('ruleForm')"
          >立即创建</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="primary" @click="dialogClick()">开课</el-button>
        <el-dialog
          title="开课"
          :visible.sync="createClazzVisible"
          width="30%"
          :before-close="handleClose"
        >
          <el-form :model="startForm" ref="startForm" label-width="100px">
            <el-form-item label="选择课程" prop="course">
              <el-col :span="4">
                <el-select
                  v-model="startForm.course_id"
                  placeholder="请选择"
                  clearable
                  filterable
                >
                  <el-option
                    v-for="item in courseList"
                    :key="item.value"
                    :label="`${item.label} ${item.term}`"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="选择班级" prop="clazz">
              <el-col :span="4">
                <el-select
                  v-model="startForm.clazz_no"
                  placeholder="请选择"
                  clearable
                  filterable
                >
                  <el-option
                    v-for="item in clazzList"
                    :key="item.value"
                    :label="item.value"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-col>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="handleClose()">取 消</el-button>
            <el-button type="primary" @click="startCourse()">确 定</el-button>
          </span>
        </el-dialog>
      </el-col>
    </el-form-item>
  </el-form>
</template>
  <script>
export default {
  name: "CreateCourse.vue",
  data() {
    return {
      dialogVisible: false,
      createClazzVisible: false,
      termsLoading: true,
      ruleForm: {
        name: "",
        tid: "",
        region: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入课程名称", trigger: "blur" },
          { min: 1, max: 9, message: "长度在 1 到 9个字符", trigger: "blur" },
        ],
        region: [{ required: true, message: "请选择学期", trigger: "change" }],
      },
      termList: [],
      clazzList: [],
      courseList: [],
      startForm: {
        course_id: null,
        clazz_no: "",
      },
      dialogImageUrl: "",
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
    this.ruleForm.tid = this.loginData.teacher_id;
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //alert("submit!");
          //let formData = new FormData(document.querySelector(".courseForm"));
          let formData = new FormData();
          formData.append("name", this.ruleForm.name);
          formData.append("tid", this.ruleForm.tid);
          formData.append("term_id", this.ruleForm.region);
          console.log(this.ruleForm.tid);
          this.axios
            .post("http://127.0.0.1:8080/createCourse", formData, {
              headers: { "Content-Type": "multipart/form-data" },
            })
            .then((res) => {
              console.log(res.data);
              if (res.data.status_code == 0) {
                //location.reload();
                this.$message({
                  message: res.data.info,
                  type: "success",
                }).catch((error) => {
                  this.$message.error("网络错误");
                });
              } else {
                this.$message.error(res.data.info);
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    loadTerms() {
      this.axios
        .get("http://127.0.0.1:8080/getTerms", {})
        .then((response) => {
          response.data.forEach((element) => {
            let item = {
              value: element.term_id,
              label: element.term_name,
            };
            this.termList.push(item);
          });
          this.termsLoading = false;
        })
        .catch((error) => {
          this.$message.error("网络错误");
          this.termsLoading = false;
        });
    },
    clearTerms() {
      setTimeout(() => {
        this.termList = [];
      }, 100);
      this.termsLoading = true;
    },
    dialogClick() {
      this.createClazzVisible = true;
      this.axios
        .get("http://127.0.0.1:8080/getClazz", {
          params: { id: this.loginData.teacher_id },
        })
        .then((response) => {
          response.data.forEach((element) => {
            let item = {
              value: element.clazz_no,
            };
            this.clazzList.push(item);
          });
        })
        .catch((error) => {
          this.$message.error("网络错误");
        });
      this.axios
        .get("http://127.0.0.1:8080/getCourse", {
          params: { id: this.loginData.teacher_id },
        })
        .then((response) => {
          response.data.forEach((element) => {
            let item = {
              value: element.course_id,
              label: element.course_name,
              term: "",
            };
            this.axios
              .get("http://127.0.0.1:8080/getTermById", {
                params: { term_id: element.term_id },
              })
              .then((response) => {
                item.term = response.data.term_name;
                this.courseList.push(item);
                //console.log(item);
              })
              .catch((error) => {
                this.$message.error("网络错误");
              });
          });
        });
    },
    startCourse() {
      this.createClazzVisible = false;
      let formData = new FormData();
      formData.append("course_id", this.startForm.course_id);
      formData.append("clazz_no", this.startForm.clazz_no);
      this.axios
        .post("http://127.0.0.1:8080/startCourse", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.status_code == 0) {
            //location.reload();
            this.$message({
              message: res.data.info,
              type: "success",
            });
          } else {
            this.$message.error(res.data.info);
          }
        })
        .catch((err) => {
          this.$message.error(err);
        });
    },
    handleClose() {
      this.createClazzVisible = false;
      this.clazzList = [];
      this.courseList = [];
    },
  },
};
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
  border-color: #409eff;
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
.el-input {
  width: 260px;
  height: 50px;
}
</style>