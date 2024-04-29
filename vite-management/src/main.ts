import { createApp } from 'vue'
//import './style.css'
// 引入根组件
import App from './App.vue'
//import {initStore} from './store'
import {initRouter} from './router'

import {initStore} from './store'

import { createPinia } from 'pinia'
//import Router from './router' 
//const pinia = createPinia();

const app = createApp(App);

//app.use(pinia)
initStore(app);

// 实例pinia
initRouter(app);
//initStore(app);

app.config.globalProperties.$filterValue = "ww";

app.mount('#app');


var globaVar='globaVar';