<template>
<div>
    <input type="file" :id="'doc-'+1" value="选择实验模板" />
    <el-button @click="generate">生成实验报告</el-button>
    <span id="clock"></span>
</div>
</template>

<script>
import Docxtemplater from "docxtemplater";
import PizZip from "pizzip";
import { saveAs } from "file-saver";
export default {
  data() {
    return {
    };
  },
  created() {
    if (localStorage.getItem("loginData")) {
      this.loginData = JSON.parse(localStorage.getItem("loginData"));
    }
    if (this.$store.state.loginData) {
      this.loginData = this.$store.state.loginData;
    }
    this.ws = new WebSocket(
      "ws://127.0.0.1:8080/ws/" + this.loginData.student_no
    );
    this.ws.onopen =  () => {
      this.ws.send("clock");
    };
    this.ws.onmessage = (msg) => {
      document.getElementById("clock").innerHTML = msg.data;
    };
  },
  //beforeDestroy
  beforeDestroy() {
    this.ws.close();
  },
  methods: {
    async generate() {
      var templateData = [];
      await this.axios
        .get("http://127.0.0.1:8080/getComposedItemsInfo", {
          params: {
            compose_no: 1,
            type: 1,
            student_id: this.loginData.student_id,
          },
        })
        .then((response) => {
          response.data.forEach((element, index) => {
            templateData[index] = element.content;
          });
        })
        .catch((response) => {
          this.$message.error("网络错误");
        });
        //
      console.log(templateData);
      console.log(templateData[0]);

      var docs = document.getElementById("doc-" + 1);
      //FileReader
      var reader = new FileReader();
      reader.readAsBinaryString(docs.files.item(0))

      reader.onerror = function (evt) {
        console.log("error reading file", evt);
        alert("error reading file" + evt);
      };
      reader.onload = function (evt) {
        const content = evt.target.result;
        //pizzip、doc
        let zip = new PizZip(content);
        let doc = new Docxtemplater(zip,{
            paragraphLoop:true,
            linebreaks:true
        })

        // Render the document (Replace {first_name} by John, {last_name} by Doe, ...)
        doc.setData({
          one: templateData[0],
          two: templateData[1],
          three: templateData[2],
          four: templateData[3],
          five: templateData[4],
          six: templateData[5],
        });
        try {
          // render the document (replace all occurences of {first_name} by John, {last_name} by Doe, ...)
          doc.render();
        } catch (error) {
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
        saveAs(blob,"o.docx");
      };
    },
  },
};
</script>

<style>
</style>