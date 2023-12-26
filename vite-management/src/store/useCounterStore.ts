import { defineStore } from 'pinia'
export const useCounterStore = defineStore( {
    persist: {
      key:'uKey',
      storage:window.localStorage
    },
    id:'useCounterStore',
    state: () => ({ 
      count: 50 ,
      name:'wang rui huan'
    }),
    getters: {
       // 自动推断出返回类型是一个 number
      doubleCount: (state) => state.count * 2,
      // 返回类型**必须**明确设置
      doublePlusOne():number{
        // 整个 store 的 自动补全和类型标注 ✨
        return this.doubleCount + 1
      },
      doubleCountPlusOne():number {
        // 自动补全 ✨
        return this.doubleCount + 1
      },
    },
    actions: {
      increment() {
        this.count++
      },
    },
  

})