import Vue from "vue";
import Vuex from 'vuex';
Vue.use(Vuex);
const store = new Vuex.Store ( {
    state: {}           // state 中存放的就是全局共享的数据
})
export default store