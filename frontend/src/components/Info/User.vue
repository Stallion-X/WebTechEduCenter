<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>个人信息</el-breadcrumb-item>
    </el-breadcrumb>
    <h2 style="text-align: center">个人信息</h2>
    <el-empty description="请登录" v-if="!login"></el-empty>
    <el-row :gutter="20" v-loading="loading" v-else>
      <el-col :span="2">&nbsp; </el-col>
      <el-col :span="10">
        <el-avatar
          :src="imgurl"
          style="width: 160px; height: 160px"
        ></el-avatar>
      </el-col>
      <el-col :span="10">
        <h5>姓名：{{ name }}</h5>
        <h5 v-if="loginData.type == 0">学号：{{ num }}</h5>
        <h5 v-else>工号：{{ num }}</h5>
        <h5 v-if="loginData.type == 0">班级：{{ clazz }}</h5>
        <h5 v-else>所教班级：{{ clazz }}</h5>
      </el-col>
      <el-col :span="10">
        <h3>作业&实验一览表</h3>
        <h5>注：占比为总分除所得分数</h5>
        <h5>例：单选满分25分，得分20分，为80</h5>
        <div id="main" style="width: 600px; height: 600px"></div>
      </el-col>
      <el-col :span="6"> </el-col>
      <el-col :span="6"> </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts/core";
import {
  DatasetComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
} from "echarts/components";
import { LineChart, PieChart } from "echarts/charts";
import { UniversalTransition, LabelLayout } from "echarts/features";
import { CanvasRenderer } from "echarts/renderers";
echarts.use([
  DatasetComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  LineChart,
  PieChart,
  CanvasRenderer,
  UniversalTransition,
  LabelLayout,
]);
export default {
  name: "User",
  data() {
    return {
      name: "",
      imgurl: "",
      num: "",
      clazz: "",
      loading: false,
      login: null,
    };
  },
  created() {
    if (this.login == true) {
      this.login = null;
    }
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
      this.loading = true;
      this.login = true;
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
      this.loading = true;
      this.login = true;
    }
    if (this.loading == true) {
      if (this.loginData.type == 0) {
        this.axios
          .get("http://127.0.0.1:8080/getStudentByNo", {
            params: { no: this.loginData.student_no },
          })
          .then((response) => {
            response.data.type = 0;
            this.$store.state.loginData = response.data;
            this.name = response.data.student_name;
            this.imgurl = response.data.memo;
            this.num = response.data.student_no;
            this.clazz = response.data.clazz_no;
            this.loading = false;
          })
          .catch((error) => {
            this.$message.error("网络错误");
          });
      } else {
        this.axios
          .get("http://127.0.0.1:8080/getTeacherByNo", {
            params: { no: this.loginData.teacher_no },
          })
          .then((response) => {
            response.data.type = 1;
            this.$store.state.loginData = response.data;
            this.name = response.data.teacher_name;
            this.imgurl = response.data.memo;
            this.num = response.data.teacher_no;
            this.axios
              .get("http://127.0.0.1:8080/getClazz", {
                params: { id: this.loginData.teacher_id },
              })
              .then((response) => {
                this.clazz = "";
                if (response.data.length > 1) {
                  response.data.forEach((element) => {
                    this.clazz += "," + element.clazz_no;
                  });
                } else {
                  this.clazz += response.data[0].clazz_no;
                }
              })
              .catch((response) => {
                this.$message.error("网络错误");
              });
            this.loading = false;
          })
          .catch((response) => {
            this.$message.error("网络错误");
          });
      }
    }
  },
  mounted() {
    var chartDom = document.getElementById("main");
    var myChart = echarts.init(chartDom);
    var option;
    option = {
      legend: {},
      tooltip: {
        trigger: "axis",
        showContent: false,
      },
      dataset: {
        source: [
          ["product", "作业1", "作业2", "作业3", "作业4", "作业5", "作业6"],
          ["单选得分占比", 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
          ["多选得分占比", 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
          ["判断得分占比", 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
          ["简答得分占比", 25.2, 37.1, 41.2, 18, 33.9, 49.1],
        ],
      },
      xAxis: { type: "category" },
      yAxis: { gridIndex: 0 },
      grid: { top: "55%" },
      series: [
        {
          type: "line",
          smooth: true,
          seriesLayoutBy: "row",
          emphasis: { focus: "series" },
        },
        {
          type: "line",
          smooth: true,
          seriesLayoutBy: "row",
          emphasis: { focus: "series" },
        },
        {
          type: "line",
          smooth: true,
          seriesLayoutBy: "row",
          emphasis: { focus: "series" },
        },
        {
          type: "line",
          smooth: true,
          seriesLayoutBy: "row",
          emphasis: { focus: "series" },
        },
        {
          type: "pie",
          id: "pie",
          radius: "30%",
          center: ["50%", "25%"],
          emphasis: {
            focus: "self",
          },
          label: {
            formatter: "{b}: {@2012} ({d}%)",
          },
          encode: {
            itemName: "product",
            value: "2012",
            tooltip: "2012",
          },
        },
      ],
    };
    myChart.on("updateAxisPointer", function (event) {
      const xAxisInfo = event.axesInfo[0];
      if (xAxisInfo) {
        const dimension = xAxisInfo.value + 1;
        myChart.setOption({
          series: {
            id: "pie",
            label: {
              formatter: "{b}: {@[" + dimension + "]} ({d}%)",
            },
            encode: {
              value: dimension,
              tooltip: dimension,
            },
          },
        });
      }
    });
    option && myChart.setOption(option);
  },
  methods: {},
};
</script>

<style scoped>
</style>