// pinia根配置函数
import { createPinia } from 'pinia' 
// 持久化插件
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import { App } from 'vue'
// pinia创建pinia实例
const store = createPinia(); 
store.use(piniaPluginPersistedstate);

// 导出注册函数
export const initStore = (app: App<Element>) => {
    app.use(store); 
} 
export default store; 