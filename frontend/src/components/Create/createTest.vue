<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      router
    >
      <el-menu-item index="/one" :route="{ path: '/one', query: { item_type: 2 } }">新建试题</el-menu-item>
      <el-submenu index="1">
        <template slot="title">新建考试</template>
        <el-menu-item index="/testh">手动创建</el-menu-item>
        <el-menu-item index="/testa">自动创建</el-menu-item>
      </el-submenu>
    </el-menu>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="no" label="序号" width="180"> </el-table-column>
      <el-table-column prop="title" label="标题" width="180"> </el-table-column>
      <el-table-column prop="class" label="班级" width="180"> </el-table-column>
      <el-table-column prop="stime" label="开始时间" width="180">
      </el-table-column>
      <el-table-column prop="etime" label="截止时间" width="180">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="180">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >预览</el-button
          >
          <!-- <el-button type="text" size="small" @click="handleScore(scope.row)">批改</el-button> -->
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1",
      tableData: [
        // {
        //   no: "试卷1",
        //   title: "第一次考试",
        //   class: "202012",
        //   stime: "2022-10-10 7:00",
        //   etime: "2022-10-10 9:00",
        // },
        // {
        //   no: "试卷2",
        //   title: "第二次考试",
        //   class: "202012",
        //   stime: "2022-10-12 7:00",
        //   etime: "2022-10-12 9:00",
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
    this.axios
      .get("http://127.0.0.1:8080/tGetComposeList", {
        params: { id: this.loginData.teacher_id, type: 2 },
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
            title: element.compose_name,
            class: element.clazz_no,
            stime: element.start_time,
            etime: element.end_time,
          });
        });
      })
      .catch((response) => {
        this.$message.error("网络错误");
      });
  },
  methods: {
    handleClick(row) {
      console.log(row);
      this.$router.push({ path: "viewExa", query: { no: row.no } });
    },
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
  },
};
</script>