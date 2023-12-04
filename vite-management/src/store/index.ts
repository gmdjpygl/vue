import { createPinia } from 'pinia' 
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import { App } from 'vue'

const store = createPinia(); 
store.use(piniaPluginPersistedstate);


export const initStore = (app: App<Element>) => {
    app.use(store); 
} 
export default store; 