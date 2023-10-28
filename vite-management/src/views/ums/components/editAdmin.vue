<template>
<el-dialog v-model="newVisible" title="Shipping address" :before-close="close">
    <el-form :model="newForm">
      <el-form-item label="Promotion name" :label-width="formLabelWidth">
        <el-input v-model="newForm.name" autocomplete="off" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="modifyAdmin">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

 
<script lang="ts" setup> 
  import {reactive,ref,toRefs,watch} from "vue"
  import {updateAdmin} from "../../../request/api"

  const props = defineProps<{
        visible: boolean;
        form:AdminObjItf  
   }>();

    const state=reactive<{
        newForm:AdminObjItf,
        formLabelWidth:string,
        newVisible:boolean
    }>({
        newForm:{},
        formLabelWidth:'100px',
        newVisible:false
    })
 //  let {newVisible}=toRefs(state);
   const {newForm,newVisible}=toRefs(state);
   const {formLabelWidth}=toRefs(state);
 
   const emit = defineEmits<{
        (event: 'close', r?: 'reload'): void
    }>();

    // 点击关闭
    const close = (r?: 'reload') => {
        newVisible.value= false;
        // 传到父组件
         emit('close', r);
    }
    watch(() => props.visible, (newVal, oldVal) => {
      //console.log("oldVal:"+oldVal);
      //console.log("newVal:"+newVal);
      newVisible.value = props.visible;
    })
    watch(() => props.form, (newVal, oldVal) => {
      newForm.value = { ...props.form };
    })

    // 确定按钮
    const modifyAdmin = () => {
      console.log(newForm);
    if (newForm.value.id) {
        updateAdmin(newForm.value.id, newForm.value).then(res => {
        if (res.code === 200) {
            close('reload');
        }
        })
    }
 

}

  </script>