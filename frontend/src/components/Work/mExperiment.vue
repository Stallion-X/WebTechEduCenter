<template>
  <el-main>
    <el-table :data="tableData">
      <el-table-column prop="work" label="实验名称" width="auto">
      </el-table-column>
      <el-table-column prop="state" label="状态" width="auto">
      </el-table-column>
      <el-table-column prop="ddl" label="截至时间" width="auto">
      </el-table-column>
      <el-table-column prop="ddl" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="look(scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-main>
</template>

<script>
export default {
  name: "Experiment",
  data() {
    return {
      tableData: [],
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
      .get("http://127.0.0.1:8080/tGetComposeList", {
        params: { id: this.loginData.teacher_id, type: 1 },
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
            work: element.compose_name,
            state: element.compose_state,
            ddl: element.end_time,
          });
        });
      })
      .catch((response) => {
        this.$message.error("网络错误");
      });
  },
  methods: {
    look(row) {
      this.$router.push({ path: "marking", query: { no: row.no, type: 1 } });
    },
  },
};
</script>

<style>
</style>