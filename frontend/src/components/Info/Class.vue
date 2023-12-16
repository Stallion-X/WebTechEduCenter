<template>
  <div>
    <el-empty description="请登录" v-if="!login"></el-empty>
    <div v-else>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/#/class">班级信息</a></el-breadcrumb-item>
      </el-breadcrumb>
      <h4>
        选择班级：
        <el-select v-model="value" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-loading="loading"
          >
          </el-option> </el-select
        >&emsp;&emsp;
        <el-button type="primary" @click="loadInfo(value)">查询</el-button>
      </h4>
      <el-table
        :data="tableData"
        border
        style="width: 100%"
        v-loading="loading"
      >
        <el-table-column fixed prop="class" label="班级" width="150">
        </el-table-column>
        <el-table-column prop="s_num" label="学号" width="170">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="150">
        </el-table-column>
        <el-table-column label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small"
              >查看</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Class",
  methods: {
    handleClick(row) {
      console.log(row);
    },
  },
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
            this.options.push(item);
            this.loading = false;
          });
        })
        .catch((error) => {
          this.$message.error("网络错误");
          this.loading = false;
        });
    }
  },
  methods: {
    loadInfo(value) {
      this.axios
        .get("http://127.0.0.1:8080/getClazzInfo", {
          params: { no: value },
        })
        .then((response) => {
          response.data.forEach((element) => {
            let item = {
              class: element.clazz_no,
              name: element.student_name,
              s_num: element.student_no,
            };
            this.tableData.push(item);
          });
        });
    },
  },
  data() {
    return {
      loading: true,
      tableData: [
        // {
        //   class: "202012",
        //   name: "王小虎",
        //   s_num: 200333,
        // },
        // {
        //   class: "202012",
        //   name: "王小虎",
        //   s_num: 200333,
        // },
        // {
        //   class: "202012",
        //   name: "王小虎",
        //   s_num: 200333,
        // },
        // {
        //   class: "202012",
        //   name: "王小虎",
        //   s_num: 200333,
        // },
      ],
      options: [
        // {
        //   value: "202011",
        //   label: "202011班",
        // },
        // {
        //   value: "202012",
        //   label: "202012班",
        // },
        // {
        //   value: "202013",
        //   label: "202013班",
        // },
      ],
      value: "",
    };
  },
};
</script>

<style scoped>
</style>