<template>
  <div style="border: 1px solid #ccc">
    <Toolbar
      style="border-bottom: 1px solid #ccc"
      :editor="editor"
      :defaultConfig="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="height: 300px; overflow-y: hidden"
      v-model="html"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="onCreated"
    />
    <el-upload
      class="disabled-upload"
      action="https://jsonplaceholder.typicode.com/posts/"
      multiple
      :limit="1"
      :on-exceed="handleExceed"
    >
      <el-button
        type="primary"
        round
        class="el-icon-upload"
        :id="'file-upload-button-' + item_index"
      ></el-button>
    </el-upload>
  </div>
</template>

<script>
import Vue from "vue";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { Boot } from "@wangeditor/editor";
export default Vue.extend({
  components: { Editor, Toolbar },
  props:['compose_type','compose_no','item_index'],
  data() {
    return {
      editor: null,
      html: "",
      toolbarConfig: {},
      editorConfig: { placeholder: "请输入内容..." },
      mode: "default", // or 'simple'
    };
  },
  methods: {
    onCreated(editor) {
      this.toolbarConfig.insertKeys = {};
      this.editor = Object.seal(editor); // 一定要用 Object.seal() ，否则会报错
      let item_index = this.item_index;
      let compose_no = this.compose_no;
      let compose_type = this.compose_type;
      const menu1Conf = {
        key: `menu-${compose_type}-${compose_no}-${item_index}`, // 定义 menu key ：要保证唯一、不重复（重要）
        factory() { 
          return new MyButtonMenu(item_index);
        },
      };
      //还差个调用Vuex做全局判断是否为第一次打开 不然就不用注册菜单的步骤没写
      //可以试试Map
      Boot.registerMenu(menu1Conf);
      this.toolbarConfig.insertKeys = {
        index: 24, // 插入的位置，基于当前的 toolbarKeys
        keys: [`menu-${compose_type}-${compose_no}-${item_index}`],
      };
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
  },
  mounted() {
    // 模拟 ajax 请求，异步渲染编辑器
    // setTimeout(() => {
    //   this.html = "<p>模拟 Ajax 异步设置内容 HTML</p>";
    // }, 1500);
  },
  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy(); // 组件销毁时，及时销毁编辑器
  },
});

class MyButtonMenu {
  // JS 语法
  constructor(item_index) {
    this.title = "上传文件"; // 自定义菜单标题
    // this.iconSvg = '<svg>...</svg>' // 可选
    this.tag = "button";
    this.item_index = item_index;
  }
  // 获取菜单执行时的 value ，用不到则返回空 字符串或 false
  getValue(editor) {
    // JS 语法
    return " hello ";
  }
  // 菜单是否需要激活（如选中加粗文本，“加粗”菜单会激活），用不到则返回 false
  isActive(editor) {
    // JS 语法
    return false;
  }
  // 菜单是否需要禁用（如选中 H1 ，“引用”菜单被禁用），用不到则返回 false
  isDisabled(editor) {
    // JS 语法
    return false;
  }
  // 点击菜单时触发的函数
  exec(editor, value) {
    // JS 语法
    if (this.isDisabled(editor)) return;
    //editor.insertText(value); // value 即 this.value(editor) 的返回值
    console.log("#file-upload-button-" + this.item_index);
    document.querySelector("#file-upload-button-" + this.item_index).click();
  }
}
</script>

<style src="@wangeditor/editor/dist/css/style.css">
</style>
<style scoped>
.disabled-upload {
  display: none;
}
</style>