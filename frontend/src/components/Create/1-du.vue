<template>
  <div>
    <el-row>
      <el-col :span="2">题目:</el-col>
      <el-col :span="22">
        <el-input type="textarea" autosize placeholder="" v-model="textarea">
        </el-input
      ></el-col>
    </el-row>

    <el-row>
      <el-col :span="2"></el-col>
      <el-col :span="22"
        ><el-alert
          type="warning"
          show-icon
          title="注：选中的答案即为正确答案"
        ></el-alert
      ></el-col>
    </el-row>
    <br />
    <el-checkbox-group v-model="checkList">
      <el-row>
        <el-col :span="2"> <el-checkbox label=" A"></el-checkbox></el-col>
        <el-col :span="22"> <el-input v-model="input"></el-input></el-col>
      </el-row>

      <el-row>
        <el-col :span="2"> <el-checkbox label=" B"></el-checkbox></el-col>
        <el-col :span="22"> <el-input v-model="input1"></el-input></el-col>
      </el-row>
      <el-row>
        <el-col :span="2"> <el-checkbox label="C"></el-checkbox></el-col>
        <el-col :span="22"> <el-input v-model="input2"></el-input></el-col>
      </el-row>

      <el-row>
        <el-col :span="2"> <el-checkbox label="D"></el-checkbox></el-col>
        <el-col :span="22"> <el-input v-model="input3"></el-input></el-col>
      </el-row>
    </el-checkbox-group>
    <el-row><el-button type="success" @click="b()">保存</el-button></el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      textarea: "",
      input: "",
      input1: "",
      input2: "",
      input3: "",
      input5: "",
      radio: "1",
      checkList: [],
    };
  },
  methods: {
    b() {
      console.log(this.checkList);
      console.log(this.textarea);
    },
  },
};
</script>
