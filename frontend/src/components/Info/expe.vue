<template>
  <div>
    <h2 style="text-align: center">实验情况</h2>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="e_num" label="序号" width="180"> </el-table-column>
      <el-table-column
        prop="tag"
        label="状态"
        width="180"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.name === '已完成' ? 'primary' : 'success'"
            disable-transitions
            >{{ scope.row.name }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="e_name" label="实验名称"> </el-table-column>
      <el-table-column prop="ddl" label="操作">
        <template slot-scope="scope">
          <input type="file" :id="'doc-'+scope.row.e_num" value="选择实验模板" />
          <el-button @click="generate(scope.row)">生成实验报告</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import Docxtemplater from "docxtemplater";
import PizZip from "pizzip";
import { saveAs } from "file-saver";
export default {
  name: "exam",
  data() {
    return {
      tableData: [
        // {
        //   e_num: "1",
        //   name: "已完成",
        //   e_type: "success",
        //   e_name: "html简单开发",
        // },
        // {
        //   e_num: "2",
        //   name: "未完成",
        //   e_type: "warning",
        //   e_name: "JavaScript基础",
        // },
        // {
        //   e_num: "3",
        //   name: "未完成",
        //   e_type: "info",
        //   e_name: "jdbc链接",
        // },
        // {
        //   e_num: "4",
        //   name: "未完成",
        //   e_type: "info",
        //   e_name: "mvc模式开发",
        // },
      ]
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
        params: { no: this.loginData.clazz_no, type: 1 },
      })
      .then((response) => {
        response.data.forEach((element) => {
          if (element.compose_state == 0) {
            element.compose_state = "未完成";
          } else {
            element.compose_state = "已完成";
          }
          this.tableData.push({
            e_num: element.compose_no,
            e_name: element.compose_name,
            name: element.compose_state,
            ddl: element.end_time,
          });
        });
        //console.log(this.questionList);
      })
      .catch((response) => {
        this.$message.error("网络错误");
      });
  },
  methods: {
    async generate(row) {
      var templateData = [];
      await this.axios
      .get("http://127.0.0.1:8080/getComposedItemsInfo", {
        params: { compose_no: row.e_num, type:1, student_id: this.loginData.student_id},
      })
      .then((response) => {
        response.data.forEach((element, index) => {
          templateData[index] = element.content;
        });
      })
      .catch((response) => {
        this.$message.error("网络错误");
      });
      console.log(templateData);
      //如不使用async await 则会导致乱序执行 templateData[0]输出undefined 然后再输出templateData的值
      //因为axios(Ajax)本身就是异步请求
      console.log(templateData[0]);

      var docs = document.getElementById("doc-"+row.e_num);
      var reader = new FileReader();
      reader.readAsBinaryString(docs.files.item(0));

      reader.onerror = function (evt) {
        console.log("error reading file", evt);
        alert("error reading file" + evt);
      };
      reader.onload = function (evt) {
        const content = evt.target.result;
        var zip = new PizZip(content);
        var doc = new Docxtemplater(zip, {
          paragraphLoop: true,
          linebreaks: true,
        });

        
        // Render the document (Replace {first_name} by John, {last_name} by Doe, ...)
        doc.setData({
          one: templateData[0],
          two: templateData[1],
          three: templateData[2],
          four: templateData[3],
          five: templateData[4],
          six: templateData[5]
        });
        try {
          // render the document (replace all occurences of {first_name} by John, {last_name} by Doe, ...)
          doc.render();
        } catch (error) {
          // The error thrown here contains additional information when logged with JSON.stringify (it contains a properties object containing all suberrors).
          function replaceErrors(key, value) {
            if (value instanceof Error) {
              return Object.getOwnPropertyNames(value).reduce(function(
                error,
                key
              ) {
                error[key] = value[key];
                return error;
              },
              {});
            }
            return value;
          }
          console.log(JSON.stringify({ error: error }, replaceErrors));

          if (error.properties && error.properties.errors instanceof Array) {
            const errorMessages = error.properties.errors
              .map(function(error) {
                return error.properties.explanation;
              })
              .join("\n");
            console.log("errorMessages", errorMessages);
            // errorMessages is a humanly readable message looking like this :
            // 'The tag beginning with "foobar" is unopened'
          }
          throw error;
        }

        const blob = doc.getZip().generate({
          type: "blob",
          mimeType:
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
          // compression: DEFLATE adds a compression step.
          // For a 50MB output document, expect 500ms additional CPU time
          compression: "DEFLATE",
        });
        // Output the document using Data-URI
        saveAs(blob, "output.docx");
      };
    },
  },
};
</script>

<style scoped>
</style>