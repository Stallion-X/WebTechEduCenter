<template>
<div>
    <input type="file" :id="'doc-'+1" value="选择实验模板" />
    <el-button @click="generate()">生成实验报告</el-button>
    <span id="clock"></span>
</div>
</template>

<script>
export default {
  data() {
    return {};
  },
  created() {

  },
}
</script>