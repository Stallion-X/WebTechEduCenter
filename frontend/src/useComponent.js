import Header from './components/Header.vue';
import Nav from './components/Nav.vue';
import RichEditor from './components/WangEditor.vue'
// 当我们需要进行多个组件的注册时候, 假如说都写到main.js里面的话, 
// 这样就会造成main.js文件的臃肿与杂乱
export default {
  install(Vue) {
    Vue.component('top', Header);
    Vue.component('left', Nav);
    Vue.component('RichEditor',RichEditor);
  }
}