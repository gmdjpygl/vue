<!--  -->
<template>
  <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" class="demo-ruleForm">
    <el-form-item prop="username">
      <el-input v-model="ruleForm.username" type="text" autocomplete="off" />
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input v-model="ruleForm.pwd" type="password" autocomplete="off" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="loginFn()">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang='ts' setup>

import { reactive, toRefs, ref,toRef} from 'vue'
import { adminLoginApi ,getAdminInfoApi} from '../../request/api'
import { useRouter } from 'vue-router' 
import { useStore } from 'vuex' 
import  Cookie  from 'js-cookie'  


const state = reactive({
  ruleForm: {
    username: 'admin', 
    pwd: '123456'
  }
});
let { ruleForm } = toRefs(state);
// 获取el-form组件对象
let ruleFormRef = ref();
// 获取项目路由对象
let router = useRouter();
let store = useStore();

const rules = reactive({
 
})

// 点击登录按钮触发
const  loginFn = () => {
    ruleFormRef.value.validate().then(() => {
        console.log('校验通过');

    
        adminLoginApi({
          password: ruleForm.value.pwd,
          username: ruleForm.value.username
        }).then(res => {
          if(res.code==200){
            Cookie.set('token',res.data.tokenHead+res.data.token,{expires:1/24});
            store.dispatch("getAdminInfo").then(rec=>{
                router.push('/homepage');
            });
           /*  getAdminInfoApi().then(
              res=>{
                console.log(res);
                store.commit('updateMenus',res.data.menu)
                router.push('/homepage')
              } 

            ) */
          }
        })
    }).catch(() => {
      console.log('校验不同通过')
    })
}

</script>
<style lang='less' scoped>
</style>