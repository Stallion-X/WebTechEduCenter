<template>
  <el-main>
    <el-page-header @back="goBack" content="作业"></el-page-header>
    <div v-for="(item, index) in questionList" :key="index">
      <h4>{{ index + 1 }}、{{ item.question }}</h4>
      <div
        v-if="item.type == '0'"
        style="margin-top: 10px; margin-bottom: 20px"
      >
        <div @change="rideoChange({ index: index, ans: answerList[index] })">
          <el-radio v-model="answerList[index]" label="A"
            >A.{{ item.answer1 }}</el-radio
          ><br />
          <el-radio v-model="answerList[index]" label="B"
            >B.{{ item.answer2 }}</el-radio
          ><br />
          <el-radio v-model="answerList[index]" label="C"
            >C.{{ item.answer3 }}</el-radio
          ><br />
          <el-radio v-model="answerList[index]" label="D"
            >D.{{ item.answer4 }}</el-radio
          ><br />
        </div>
      </div>
      <div
        v-if="item.type == '1'"
        style="margin-top: 10px; margin-bottom: 20px"
      >
        <div>
          <el-radio v-model="answerList[index]" label="对">对</el-radio>
          <el-radio v-model="answerList[index]" label="错">错</el-radio>
        </div>
      </div>
      <div
        v-if="item.type == '3'"
        style="margin-top: 10px; margin-bottom: 20px"
      >
        <!-- <div>
          <el-input v-model="answerList[index]" type="textarea" :rows="5">
          </el-input>
        </div> -->
        <RichEditor
          :compose_type="0"
          :compose_no="composeNo"
          :item_index="index + 1"
        ></RichEditor>
      </div>
      <div
        v-if="item.type == '4'"
        style="margin-top: 10px; margin-bottom: 20px"
      >
        <!-- <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
        >
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload> -->
        <RichEditor
          :compose_type="0"
          :compose_no="composeNo"
          :item_index="index + 1"
        ></RichEditor>
      </div>
      <br />
    </div>
    <!-- <el-row>
      <el-col :span="12"
        ><el-button type="primary" class="mar10" @click="goSubmit"
          >提交</el-button
        >
      </el-col>
    </el-row> -->
    <router-view></router-view>
  </el-main>
</template>
<script>
export default {
  data() {
    return {
      composeNo: null,
      fileList: [],
      preDisabled: true, //上禁用按钮
      nextDisabled: false, //下禁用按钮
      num: 0, //第几题
      answerList: [], //每题的选项
      //所有题的答案或分数
      questionList: [],
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
    this.composeNo = this.$route.query.no;
    this.axios
      .get("http://127.0.0.1:8080/getComposedItems", {
        params: { no: this.composeNo, type: 0 },
      })
      .then((response) => {
        //console.log(response.data);
        response.data.forEach((element) => {
          this.questionList.push({
            type: element.question_type,
            id: element.item_index,
            name: "",
            question: element.content,
            answer1: element.ops[0].content,
            answer2: element.ops[1].content,
            answer3: element.ops[2].content,
            answer4: element.ops[3].content,
          });
        });
        //console.log(this.questionList);
      })
      .catch((error) => {
        this.$message.error("网络错误");
      });
  },
  methods: {
    rideoChange(val) {
      console.log(val);
      //this.answerList.push(val);
      console.log(this.answerList);
    },
    next() {
      this.preDisabled = false;
      if (this.num < this.questionList.length - 1) {
        this.num += 1;
      }
    },
    prex() {
      if (this.num === 0) {
        this.num = 0;
      } else {
        this.num -= 1;
      }
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    handleRemove(file, fileList) {},
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    goBack() {
      this.$router.back();
    },
  },
  watch: {
    //第一题和最后一题禁用按钮
    num(now, old) {
      this.nextDisabled = now === this.questionList.length - 1;
      if (now < 1) {
        this.preDisabled = true;
      }
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
</style>