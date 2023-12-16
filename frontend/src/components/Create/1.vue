<template>
  <el-main>
    <el-page-header
      @back="$router.push('/createWork')"
      content="录入题库"
      v-if="$store.state.fromPath == '/createWork'"
    ></el-page-header>
    <el-page-header
      @back="$router.push('/createTest')"
      content="录入题库"
      v-if="$store.state.fromPath == '/createTest'"
    ></el-page-header>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      router
    >
      <el-menu-item
        index="/oned"
        :route="{
          path: '/oned',
          query: { item_type: this.$route.query.item_type },
        }"
        >单选题</el-menu-item
      >
      <el-menu-item
        index="/onedu"
        :route="{
          path: '/onedu',
          query: { item_type: this.$route.query.item_type },
        }"
        >多选题</el-menu-item
      >
      <el-menu-item
        index="/onet"
        :route="{
          path: '/onet',
          query: { item_type: this.$route.query.item_type },
        }"
        >填空题</el-menu-item
      >
      <el-menu-item
        index="/onej"
        :route="{
          path: '/onej',
          query: { item_type: this.$route.query.item_type },
        }"
        >简答题</el-menu-item
      >
      <el-menu-item
        index="/onep"
        :route="{
          path: '/onep',
          query: { item_type: this.$route.query.item_type },
        }"
        >编程题</el-menu-item
      >
    </el-menu>
    <br />
    <router-view> </router-view>
  </el-main>
</template>
<style>
</style>
<script>
export default {
  data() {
    return {
      activeIndex: "",
    };
  },
  created() {
    console.log(this.$route.path);
    this.activeIndex = this.$route.path;
    // this.$router.beforeEach((to, from, next) => {
    //   console.log(from.path)
    // });
  },
  // watch: {
  //   $route(to, from) {
  //     console.log("✅ to.path", to, to.path);
  //     console.log("✅ from.path", from, from.path);
  //     if (from.path == "/createWork" || from.path == "/createTest") {
  //       this.fromPath = from.path;
  //     }
  //   },
  // },
  methods: {},
};
</script>