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

    <el-row>
      <el-col :span="2">
        <el-radio v-model="radio" label="A">A</el-radio></el-col
      >
      <el-col :span="22"> <el-input v-model="input0"></el-input></el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">
        <el-radio v-model="radio" label="B">B</el-radio></el-col
      >
      <el-col :span="22"> <el-input v-model="input1"></el-input></el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">
        <el-radio v-model="radio" label="C">C</el-radio></el-col
      >
      <el-col :span="22"> <el-input v-model="input2"></el-input></el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">
        <el-radio v-model="radio" label="D">D</el-radio></el-col
      >
      <el-col :span="22"> <el-input v-model="input3"></el-input></el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">满分:</el-col>
      <el-col :span="5">
        <el-input placeholder="" v-model="score"> </el-input
      ></el-col>
    </el-row>
    <br />
    <el-row><el-button type="success" @click="save()">保存</el-button></el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      textarea: "",
      input0: "",
      input1: "",
      input2: "",
      input3: "",
      radio: "",
      score: "",
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
  },
  methods: {
    change() {
      console.log(this.input0);
    },
    valid() {
      try {
        Object.keys(this._data).forEach((e) => {
          //console.log(Reflect.get(this, e));
          if (Reflect.get(this, e) == "") {
            throw new Error();
          }
        });
      } catch (e) {
        return false;
      }
      return true;
    },
    save() {
      //document.getElementById("num").innerHTML = 2;
      //console.log(Object.keys(this._data))
      console.log(this.valid());
      if (this.valid()) {
        console.log(this.textarea);
        console.log(this.input0);
        console.log(this.input1);
        console.log(this.input2);
        console.log(this.input3);
        console.log(this.radio);
        console.log(this.score);
        let ops = [];
        for (let i = 0; i < 4; i++) {
          ops.push({
            op: String.fromCodePoint("A".codePointAt(0) + i),
            content: Reflect.get(this, "input" + i),
          });
        }
        console.log(ops);
        let itemData = new FormData();
        itemData.append("item_type", this.$route.query.item_type);
        itemData.append("question_type", 0);
        itemData.append("teacher_id", this.loginData.teacher_id);
        itemData.append("content", this.textarea);
        //itemData.append("ops",ops);
        ops.forEach((value, index) => {
          itemData.append(`ops[${index}].op`, value.op);
          itemData.append(`ops[${index}].content`, value.content);
        });
        itemData.append("answer", this.radio);
        itemData.append("full_score", this.score);
        console.log(itemData);
        this.axios
          .post("http://127.0.0.1:8080/createItem", itemData, {
            headers: { "Content-Type": "application/json" },
          })
          .then((res) => {
            console.log(res.data);
            if (res.data.status_code == 0) {
              this.$message({
                message: res.data.info,
                type: "success",
              });
              Object.keys(this._data).forEach((e) => {
                Reflect.set(this, e, "");
              });
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
      }
    },
  },
};
</script>
