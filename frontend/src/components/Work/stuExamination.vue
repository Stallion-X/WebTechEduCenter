<template>
  <el-main>
    <div>
      <el-page-header @back="goBack" content="考试"></el-page-header>
      <span id="clock"></span>
      <div style="float:left;width:500px;margin-left: 100px">
        <div v-for="(item, index) in questionList" :key="index">
          <div v-if="num === index">
            <h4>{{ index + 1 }}、{{ item.question }}</h4>
            <div v-if="item.type==='0'" style="margin-top:10px;margin-bottom: 20px;">
              <div @change="rideoChange({ index: index, ans: answerList[index] })">
                <el-radio v-model="answerList[index]" label="A">A.{{item.answer1}}</el-radio><br>
                <el-radio v-model="answerList[index]" label="B">B.{{item.answer2}}</el-radio><br>
                <el-radio v-model="answerList[index]" label="C">C.{{item.answer3}}</el-radio><br>
                <el-radio v-model="answerList[index]" label="D">D.{{item.answer4}}</el-radio><br>
              </div>
            </div>
            <div v-if="item.type==='1'" style="margin-top:10px;margin-bottom: 20px;">
              <div @change="rideoChange">
                <el-radio v-model="answerList[index]" label="对">对</el-radio>
                <el-radio v-model="answerList[index]" label="错">错</el-radio>
              </div>
            </div>
            <div v-if="item.type==='2'" style="margin-top:10px;margin-bottom: 20px;">
              <div @change="rideoChange">
                <el-input v-model="answerList[index]" type="textarea" :rows="5">
                </el-input>
              </div>
            </div>
            <div v-if="item.type==='3'" style="margin-top:10px;margin-bottom: 20px;">
              <RichEditor
          :compose_type="0"
          :compose_no="composeNo"
          :item_index="index + 1"
        ></RichEditor>
            </div>
            <br>
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
            <el-row>
              <el-button-group>
                <el-button type="primary" icon="el-icon-arrow-left" @click="prex" :disabled="preDisabled">上一题</el-button>
                <el-button type="primary" @click="next" :disabled="nextDisabled">下一题<i class="el-icon-arrow-right el-icon--right" ></i></el-button>
              </el-button-group>
              <br>
            </el-row>
          </div>
        </div>
      </div>
      <div style="float:left;">
        <el-card class="box-card">
          <h1>题目</h1>
          <div v-for="item in questionList" :key="item">
            <el-col :span="4" :key="item.id" :offset="1">
              <div class="text item">
                <el-button  :id = "item.id" @click="jump(item.id)">{{item.id}}</el-button>
              </div>
            </el-col>
          </div>
        </el-card>
      </div>
      <br>
      <div style="clear:both;text-align:center;height: 100px;display: flex;justify-content: center">
        <el-col :span="12"><el-button type="success" @click="goSubmit">提交</el-button>
        </el-col>
      </div>
    </div>
    <router-view></router-view>
  </el-main>
</template>
<script>
export default {
  data() {
    return {
      preDisabled: true, //上禁用按钮
      nextDisabled: false, //下禁用按钮
      num: 0, //第几题
      allRadio: [], //每题的选项;
      answerList: [], //所有题的答案或分数
      questionList: [
        // {
        //   type: "0",
        //   id: "1",
        //   name: "one",
        //   question: "问题1111111?",
        //   answer1: "防弹少年框架的积分换深刻领会是客家话",
        //   answer2: "防弹少年框架的积分换深刻领会是客家话",
        //   answer3: "防弹少年框架的积分换深刻领会是客家话",
        //   answer4: "防弹少年框架的积分换深刻领会是客家话",
        // },
        // {
        //   type: "0",
        //   id: "2",
        //   name: "two",
        //   question: "问题222222222?",
        //   answer1: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?",
        //   answer2: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?",
        //   answer3: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?",
        //   answer4: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?",
        // },
        // {
        //   type: "0",
        //   id: "3",
        //   name: "three",
        //   question: "问题333333333?",
        //   answer1:
        //     "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?",
        //   answer2:
        //     "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?",
        //   answer3:
        //     "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?",
        //   answer4:
        //     "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?",
        // },
        // {
        //   type: "1",
        //   id: "4",
        //   name: "fore",
        //   question: "问题4444?",
        // },
        // {
        //   type: "1",
        //   id: "5",
        //   name: "five",
        //   question: "问题5555555555",
        // },
        // {
        //   type: "2",
        //   id: "6",
        //   name: "six",
        //   question: "问题6666666666",
        // },
        // {
        //   type: "3",
        //   id: "7",
        //   name: "seven",
        //   question: "问题7777777",
        // },
      ],
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
    this.ws = new WebSocket(
      "ws://127.0.0.1:8080/ws/" + this.loginData.student_no
    );
    this.ws.onopen =  () => {
      this.ws.send("clock");
    };
    this.ws.onmessage = (msg) => {
      //获取并显示结果
      //console.log(msg.data);
      document.getElementById("clock").innerHTML = msg.data;
    };
    this.composeNo = this.$route.query.no;
    this.axios
      .get("http://127.0.0.1:8080/getComposedItems", {
        params: { no: this.composeNo, type: 2 },
      })
      .then((response) => {
        console.log(response.data);
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
  beforeDestroy() {
    this.ws.close();
  },
  methods: {
    rideoChange(val) {
      console.log(val);
      //this.answerList.push(val);
      let id = String(this.num + 1);
      document.getElementById(id).style.backgroundColor = "#409EFF";
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
    jump(id){
      this.num = id-1;
    },
    goSubmit() {
      let answerData = new FormData();
      answerData.append("student_id", this.loginData.student_id);
      answerData.append("compose_no", composeNo);
      answerData.append("compose_type",2);
      answerData.append("answerList", this.answerList);
      this.axios
        .post("http://127.0.0.1:8080/stuWork", answerData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.status_code == 0) {
            this.$message({
              message: res.data.info,
              type: "success",
            });
            this.$router.push({ path: "exam" });
          } else {
            this.$message({
              message: res.data.info,
              type: "error",
            });
          }
        })
        .catch((error) => {
          this.$message.error("网络错误");
        });
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
.text {
  font-size: 8px;
}

.item {
  padding-bottom: 10px;
  margin-left:15px;
}

.box-card {
  width: 300px;
}
</style>