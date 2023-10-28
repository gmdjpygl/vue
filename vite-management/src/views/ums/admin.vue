<template>
    <el-table :data="tableData" border style="width: 100%" >
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="address" label="Address" />

      <el-table-column label="是否启用">
        <template  v-slot:default="scope">
          {{ scope.row.status }}
          <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0"/>
        </template>
     </el-table-column>

      <el-table-column  label="操作" >
        <template #default="{row}">
           <el-button type="text">分配角色</el-button>
           <el-button type="text" @click="editAdminF(row)">编辑</el-button>
      </template>
      </el-table-column>
    </el-table>

    <editAdmin :visible="visible" @close="closeDialog" :form="rowData"></editAdmin>
  </template>
  
  <script lang="ts" setup>
  import {reactive,ref,toRefs} from "vue"
  import {getAdminLists} from "../../request/api"
  import editAdmin from "./components/editAdmin.vue"

    const state=reactive<{
       tableData:{}[],
       visible:boolean,
       rowData:AdminObjItf
       }>
      ({
      tableData:[],
      visible:false,
      rowData:{}
    })
    const {tableData,visible,rowData} = toRefs(state);
    const fetchData=()=>{
      getAdminLists({pageNum:0,pageSize:10,keyword:""})
        .then(res=>{
            if(res.code==200){
              tableData.value=res.data.list;
            }

        })
    }
    fetchData();
      // 编辑按钮
      const editAdminF=(row:{})=>{
        console.log(row);
        visible.value=true;
        rowData.value=row
      } 
      // 隐藏弹框
    const closeDialog = (r?: 'reload') => {
      //console.log("closeDialog:"+visible.value );
      visible.value = false;
      if (r === 'reload') {
        // 更新表格数据
          fetchData();
      }
    }
  </script>