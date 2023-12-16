<template>
  <el-main>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>开始学习</el-breadcrumb-item>
      <el-breadcrumb-item>考试</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table
      :data="tableData"
      border
      style="width: 100%; margin-top: 20px"
      :header-cell-style="{
        background: 'rgb(242, 243, 244)',
        color: '#515a6e',
      }"
    >
      <el-table-column prop="name" label="考试名称"> </el-table-column>
      <el-table-column prop="score" label="考试总分"> </el-table-column>
      <el-table-column prop="time" label="考试时间"> </el-table-column>
      <el-table-column prop="startTime" label="开始时间"> </el-table-column>
      <el-table-column prop="state" label="状态"> </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="joinExamination(scope.row)"
            >参加考试</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px; float: right">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        :current-page="currentPage"
        background
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
    <div>
      <el-dialog title="参加考试" :visible.sync="dialogFormVisible">
        <el-form :model="examination" ref="examination">
          <el-form-item
            style="display: none"
            label="testpaper"
            label-width="100px"
            prop="testpaper"
          >
            <el-input v-model="examination.testpaper"></el-input>
          </el-form-item>
          <el-form-item label="考试名称：" label-width="100px">
            {{ examination.name }}
          </el-form-item>
          <el-form-item label="考试总分：" label-width="100px">
            {{ examination.score }}
          </el-form-item>
          <el-form-item label="考试时间：" label-width="100px">
            {{ examination.time }} 分钟
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="surejoin()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </el-main>
</template>

<script>
export default {
  data() {
    return {
      examination: {
        id:null,
        name: "",
        time: "",
        score: "",
        state: "",
        startTime: "",
      },
      total: 0,
      pageSize: 5,
      currentPage: 1,
      tableData: [
        // {
        //   name: "前端",
        //   score: "100",
        //   time: "60",
        //   state: "未完成",
        //   startTime: "2022-12-01 8:00:00",
        // },
      ],
      dialogFormVisible: false,
      dialogFormVisibleExam: false,
      testPaper: {},
      joinid: "",
      seconds: 0,
      djs: "",
      timer: null,
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
    this.axios
      .get("http://127.0.0.1:8080/sGetComposeList", {
        params: { no: this.loginData.clazz_no, type: 2 },
      })
      .then((response) => {
        response.data.forEach((element) => {
          if (element.compose_state == 0) {
            element.compose_state = "未完成";
          } else {
            element.compose_state = "已完成";
          }
          this.tableData.push({
            no: element.compose_no,
            name: element.compose_name,
            score: element.full_score,
            time: element.total_time,
            state: element.compose_state,
            startTime: element.start_time,
          });
        });
      })
      .catch((response) => {
        this.$message.error("网络错误");
      });
  },
  methods: {
    joinExamination(row) {
      this.examination.id = row.no;
      this.examination.name = row.name;
      this.examination.type = row.type;
      this.examination.time = row.time;
      this.examination.score = row.score;
      //this.examination.password = "";
      //this.examination.testpaper = row.testpaper;
      this.examination.subjectName = row.subjectName;
      this.examination.qualifiedscore = row.qualifiedscore;
      this.dialogFormVisible = true;
    },
    surejoin() {
      this.$router.push({
        path: "stuExamination", //rooter配置的name值
        query: { no: this.examination.id }
      });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
  },
};
</script>
<style>
</style>