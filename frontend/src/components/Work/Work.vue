<template>
  <el-main>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>开始学习</el-breadcrumb-item>
      <el-breadcrumb-item>作业</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table :data="tableData">
      <el-table-column prop="work" label="作业名称" width="auto">
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
    <router-view></router-view>
  </el-main>
</template>

<script>
export default {
  name: "Work",
  data() {
    return {
      tableData: [
        // {
        //   no: "1",
        //   work: "第一次作业",
        //   state: "已完成",
        //   ddl: "2022-10-31",
        // },
        // {
        //   no: "2",
        //   work: "第二次作业",
        //   state: "已完成",
        //   ddl: "2022-11-01",
        // },
        // {
        //   no: "3",
        //   work: "第三次作业",
        //   state: "未完成",
        //   ddl: "2022-11-15",
        // },
      ],
    };
  },
  methods: {
    look(row) {
      this.$router.push({ path: "stuWork", query: { no: row.no } });
    },
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
        params: { no: this.loginData.clazz_no, type:0 },
      })
      .then((response) => {
        //console.log(response.data);
        // function* res() {
        //   let m = response.data;
        //   for (let i of m) {
        //     yield i; //每次执行到yield都会暂停
        //   }
        // }
        // let resGen = res();
        // for (let i =0; i<response.data.length; i++) {
        //   //console.log(resGen.next().value);
        //   let element = resGen.next().value;
        //   if (element.compose_state == 0) {
        //     element.compose_state = "未完成";
        //   } else {
        //     element.compose_state = "已完成";
        //   }
        //   this.tableData.push({
        //     no: element.compose_no,
        //     work: element.compose_name,
        //     state: element.compose_state,
        //     ddl: element.end_time,
        //   });
        // }
 
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
        //console.log(this.questionList);
      })
      .catch((response) => {
        this.$message.error("网络错误");
      });
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
  height: 200px;
  display: block;
}
</style>