import { createApp } from 'vue'
//import './style.css'
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
initRouter(app);
//initStore(app);

app.mount('#app');
