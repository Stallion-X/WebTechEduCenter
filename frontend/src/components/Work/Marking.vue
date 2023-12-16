<template>
  <el-main>
    <div v-for="(item, index) in questionList" :key="index">
      <div v-if="num === index">
        <h4>{{ index + 1 }}、{{ item.question }}</h4>
        <div v-if="item.type==='0'" style="margin-top:10px;margin-bottom: 20px;">
          <div @change="rideoChange">
            <el-radio v-model="allRadio[index]" label="A">A.{{item.answer1}}</el-radio><br>
            <el-radio v-model="allRadio[index]" label="B">B.{{item.answer2}}</el-radio><br>
            <el-radio v-model="allRadio[index]" label="C">C.{{item.answer3}}</el-radio><br>
            <el-radio v-model="allRadio[index]" label="D">D.{{item.answer4}}</el-radio><br>
          </div>
        </div>
        <div v-if="item.type==='1'" style="margin-top:10px;margin-bottom: 20px;">
          <div>
            <el-radio v-model="allRadio[index]" label="对">对</el-radio>
            <el-radio v-model="allRadio[index]" label="错">错</el-radio>
          </div>
        </div>
        <div v-if="item.type==='2'" style="margin-top:10px;margin-bottom: 20px;">
          <div>
            <el-input v-model="allRadio[index]" type="textarea" :rows="5">
            </el-input>
          </div>
        </div>
        <div v-if="item.type==='3'" style="margin-top:10px;margin-bottom: 20px;">
          <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </div>
        <br>
        <div v-if="num === index">
          <el-input
              id="an"
              v-model="answer"
              :disabled="true">
          </el-input>
          <br>
          <el-row>
            <h4>&emsp;本题得分：<el-input-number id="index" v-model="num2" controls-position="right" @change="handleChange" :min="0" :max="20" ></el-input-number>分</h4>
          </el-row>
        </div>
        <br>
        <el-row>
          <el-col :span="3">
            <el-button class="mar10" @click="prex" :disabled="preDisabled">上一题</el-button>
          </el-col>
          <el-col :span="6">
            <el-button class="mar10" @click="next" :disabled="nextDisabled">下一题</el-button>
          </el-col>
          <br>
          <el-col :span="12"><el-button type="primary" class="mar10" @click="goSubmit">提交</el-button>
          </el-col>
        </el-row>
      </div>
    </div>
    <router-view></router-view>
  </el-main>
</template>
<script>
export default {
  name: "list",
  data() {
    return {
      num2: 0,
      answer:'正确答案！！！',
      preDisabled: true, //上禁用按钮
      nextDisabled: false, //下禁用按钮
      num: 0, //第几题
      allRadio: [], //每题的选项;
      answerList: [], //所有题的答案或分数
      questionList: [
        {
          type:"0",
          id: "1",
          name: "one",
          question: "问题1111111?",
          answer1: "防弹少年框架的积分换深刻领会是客家话",
          answer2: "防弹少年框架的积分换深刻领会是客家话",
          answer3: "防弹少年框架的积分换深刻领会是客家话",
          answer4: "防弹少年框架的积分换深刻领会是客家话"
        },
        {
          type:"0",
          id: "2",
          name: "two",
          question: "问题222222222?",
          answer1: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?",
          answer2: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?",
          answer3: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?",
          answer4: "分类开始动画佛丹斯科了解封惹急人都是会计发发多少咯科技和?"
        },
        {
          type:"0",
          id: "3",
          name: "three",
          question: "问题333333333?",
          answer1: "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?",
          answer2: "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?",
          answer3: "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?",
          answer4: "的数据库和 非打死不回复欧水回复而交话费渡水复渡水附近的时刻?"
        },
        {
          type:"1",
          id: "4",
          name: "fore",
          question: "问题4444?",
        },{
          type:"1",
          id:"5",
          name:"five",
          question: "问题5555555555",
        },{
          type:"2",
          id:"6",
          name:"six",
          question: "问题6666666666"
        },{
          type:"3",
          id:"7",
          name:"seven",
          question: "问题7777777"
        }],
    }
  },
  methods: {
    rideoChange(val) {
      console.log(val);
      this.answerList.push(val);
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
    goSubmit() {
      this.$message({
        message: '提交成功',
        type: 'success'
      });
      this.$router.push({path: 'exam'});
    },
    handleChange(value) {
      console.log(value);
    }
  },
  watch: {
    //第一题和最后一题禁用按钮
    num(now, old) {
      this.nextDisabled = now === this.questionList.length - 1;
      if (now < 1) {
        this.preDisabled = true;
      }
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
</style>