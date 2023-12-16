<template>
  <div>
    <el-row>
      <el-col :span="2">习题标题</el-col>
      <el-col :span="8">
        <el-input v-model="inputData.input"></el-input>
      </el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">开始时间</el-col>
      <el-col :span="16">
        <el-date-picker
          v-model="inputData.value1"
          type="datetime"
          placeholder="开始时间"
          align="right"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd HH:mm:ss"
        >
        </el-date-picker>
      </el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">截止时间</el-col>
      <el-col :span="16">
        <el-date-picker
          v-model="inputData.value2"
          type="datetime"
          placeholder="截止时间"
          align="right"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd HH:mm:ss"
        >
        </el-date-picker>
      </el-col>
    </el-row>
    <br />
    <el-row>
      <el-col :span="2">布置班级</el-col>
      <el-col :span="8">
      <el-select v-model="inputData.clazz" placeholder="请选择">
        <el-option
          v-for="item in clazzData"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          v-loading="loading"
        >
        </el-option>
      </el-select>
      </el-col>
    </el-row>
    <br />
    <el-button type="text" @click="openDialog()">添加题目</el-button>
    <el-dialog
      title="添加习题"
      :visible.sync="dialogTableVisible"
      :before-close="handleClose"
    >
      <el-table :data="gridData" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column
          property="no"
          label="序号"
          width="100"
        ></el-table-column>
        <el-table-column
          property="title"
          label="题干"
          width="300"
        ></el-table-column>
        <el-table-column
          property="type"
          label="类型"
          width="200"
        ></el-table-column>
      </el-table>
      <br /><br />
      <el-row>
        <el-col :span="8">
          <el-button plain @click="handreturn">返回</el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="primary" plain @click="handtrue">确定</el-button>
        </el-col>
      </el-row>
    </el-dialog>
    <el-table :data="tableData">
      <el-table-column
        property="item_index"
        label="题号"
        width="100"
      ></el-table-column>

      <el-table-column prop="title" label="题干" width="400"> </el-table-column>
      <el-table-column prop="type" label="题型" width="200"> </el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >预览</el-button
          >
          <el-button
            @click.native.prevent="deleteRow(scope.$index, tableData)"
            type="text"
            size="small"
            color="red"
            >移除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <br />
    <el-row>
      <el-col :span="8">
        <el-button plain @click="$router.back()">返回</el-button>
      </el-col>
      <el-col :span="8">
        <el-button type="primary" plain @click="submit()">确定</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  created() {
    if (this.login == true) {
      this.login = null;
    }
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
      this.login = true;
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
      this.login = true;
    }
    if (this.loading == true) {
      this.axios
        .get("http://127.0.0.1:8080/getClazz", {
          params: { id: this.loginData.teacher_id },
        })
        .then((response) => {
          response.data.forEach((element) => {
            let item = {
              value: element.clazz_no,
              label: element.clazz_no + "班",
            };
            this.clazzData.push(item);
            this.loading = false;
          });
        })
        .catch((error) => {
          this.$message.error("网络错误");
          this.loading = false;
        });
    }
  },
  data() {
    return {
      inputData: {
        input: "",
        value1: "",
        value2: "",
        clazz: "",
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      tableData: [
        // {
        //   no: "1",
        //   title: "256321255",
        //   type: "单选题",
        // },
        // {
        //   no: "2",
        //   title: "1111111111",
        //   type: "多选",
        // },
      ],
      gridData: [
        // {
        //   no: "1",
        //   title: "1111111111",
        //   type: "单选",
        // },
        // {
        //   no: "2",
        //   title: "1111111111",
        //   type: "单选",
        // },
      ],
      pickerOptions: {},
      multipleSelection: [],
      clazzData: [],
      loading: true,
    };
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    handtrue() {
      this.dialogTableVisible = false;
      if (this.tableData.length == 0) {
        this.multipleSelection.forEach((value, index, array) => {
          let temp = {
            item_index: index + 1,
            item_id: value.no,
            title: value.title,
            type: value.type,
          };
          this.tableData.push(temp);
        });
      } else {
        let now = this.tableData.length;
        this.multipleSelection.forEach((value, index, array) => {
          let temp = {
            item_index: index + now + 1,
            item_id: value.no,
            title: value.title,
            type: value.type,
          };
          this.tableData.push(temp);
        });
      }

      this.handleClose();
    },
    handreturn() {
      this.dialogTableVisible = false;
      setTimeout(() => {
        this.gridData = [];
      }, 200);
    },
    handleClose() {
      this.dialogTableVisible = false;
      setTimeout(() => {
        this.gridData = [];
      }, 200);
    },
    openDialog() {
      this.dialogTableVisible = true;
      this.axios
        .get("http://127.0.0.1:8080/getItemInfoLite", {
          params: { teacher_id: this.loginData.teacher_id, item_type: 0 },
        })
        .then((response) => {
          response.data.forEach((e) => {
            let item = {
              no: e.item_id,
              title: e.content,
              type: e.question_type,
            };
            this.gridData.push(item);
          });
        })
        .catch((error) => {
          this.$message.error(error);
        });
    },
    submit() {
      let composeData = new FormData();
      composeData.append("compose_type", 0);
      composeData.append("compose_name", this.inputData.input);
      composeData.append("teacher_id", this.loginData.teacher_id);
      composeData.append("clazz_no", this.inputData.clazz);
      composeData.append("start_time",this.inputData.value1);
      composeData.append("end_time",this.inputData.value2);
      this.tableData.forEach((value, index) => {
        composeData.append(`index_id[${index}].item_index`, value.item_index);
        composeData.append(`index_id[${index}].item_id`, value.item_id);
      });
      this.axios
          .post("http://127.0.0.1:8080/compose", composeData, {
            headers: { "Content-Type": "application/json" },
          })
          .then((res) => {
            console.log(res.data);
            if (res.data.status_code == 0) {
              this.$message({
                message: res.data.info,
                type: "success",
              });
              Object.keys(this._data.inputData).forEach((e) => {
                Reflect.set(this.inputData, e, "");
              });
              this.tableData = [];
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
  },
};
</script>
