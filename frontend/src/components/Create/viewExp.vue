<template>
  <el-main>
    <div class="main">
      <el-page-header @back="goBack" content="实验"></el-page-header>
      <h1>前端实验报告</h1>
      <el-form
        :label-position="labelPosition"
        label-width="80px"
        :model="formLabelAlign"
      >
        <el-form-item label="实验名称">
          <el-input v-model="formLabelAlign.expname"></el-input>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="formLabelAlign.id"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <h2>一、实验目的</h2>
        <!-- <el-input v-model="formLabelAlign.purpose" type="textarea" :rows="5">
        </el-input> -->
        <RichEditor :compose_type="1" :compose_no="composeNo" :item_index="1"></RichEditor>
      </div>
    </div>
    <div>
      <h2>二、实验任务</h2>
      <!-- <el-input v-model="formLabelAlign.tasks" type="textarea" :rows="5">
      </el-input> -->
      <RichEditor :compose_type="1" :compose_no="composeNo" :item_index="2"></RichEditor>
    </div>
    <div>
      <h2>三、实验步骤</h2>
      <!-- <el-input v-model="formLabelAlign.steps" type="textarea" :rows="5">
      </el-input> -->
      <RichEditor :compose_type="1" :compose_no="composeNo" :item_index="3"></RichEditor>
    </div>
    <div>
      <h2>四、实验结果</h2>
      <!-- <el-input v-model="formLabelAlign.result" type="textarea" :rows="5">
      </el-input> -->
      <RichEditor :compose_type="1" :compose_no="composeNo" :item_index="4"></RichEditor>
    </div>
    <div>
      <h2>五、实验小结</h2>
      <!-- <el-input v-model="formLabelAlign.summary" type="textarea" :rows="5">
      </el-input> -->
      <RichEditor :compose_type="1" :compose_no="composeNo" :item_index="5"></RichEditor>
    </div>
    <div>
      <h2>六、附件</h2>
      <!-- <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList">
        <el-button size="small" type="primary" v-model="fileList">点击上传</el-button>
      </el-upload> -->
      <RichEditor :compose_type="1" :compose_no="composeNo" :item_index="6"></RichEditor>
    </div>
    <br />
    <!-- <el-row>
      <el-col :span="12"
        ><el-button type="primary" class="mar10" @click="goSubmit"
          >提交</el-button
        >
      </el-col>
    </el-row> -->
  </el-main>
</template>

<script>
export default {
  name: "stuExperiment",
  data() {
    return {
      composeNo: null,
      fileList: [],
      labelPosition: "right",
      formLabelAlign: {
        name: "",
        expname: "",
        id: "",
        purpose: "",
        tasks: "",
        steps: "",
        result: "",
        summary: "",
        code: "",
      },
    };
  },
  created() {
    this.composeNo = this.$route.query.no;
  },
  methods: {
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    goBack() {
      this.$router.back();
    },
  },
};
</script>

<style scoped>
</style>