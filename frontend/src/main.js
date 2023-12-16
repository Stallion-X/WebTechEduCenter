import Vue from "vue";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from "./router/router";
import store from "./store/store"
import axios from "axios";
import useComponent from "./useComponent";
Vue.use(ElementUI);
Vue.use(useComponent);
Vue.prototype.axios = axios;
new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
});