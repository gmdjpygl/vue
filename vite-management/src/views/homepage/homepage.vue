<!--  -->
<template>
  <div class="homepage_container">
    <div class="homepage_header">头部</div>
    <div class="homepage_menu">
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="2"
        text-color="#fff"
        :unique-opened="true"
        :router="true"  
      >
        <el-sub-menu :index="menu.id+''" v-for="menu in newMenus" :key="menu.id" >
          <template #title>
            <span>{{ menu.title}}</span>
          </template>
          <template  v-for="submenu in menu.children" >
          <el-menu-item 
              :index="'/' + menu.name + '/' + submenu.name"
              v-if="!submenu.hidden"
              :key="submenu.id"
          
          >{{submenu.title}}</el-menu-item>
          </template>
        </el-sub-menu>
      </el-menu>
    </div>
    <div class="homepage_content">
      <router-view>
        
      </router-view>
    </div>
  </div>
</template>

<script lang='ts' setup>
      import { reactive, toRefs, ref ,computed} from 'vue'
      import {useStore} from 'vuex'
      interface MenuObj {
        parentId: number;
        id: number;
        title:string;
        hidden:number;
        name:String;
        children?: MenuObj[]
      }

      interface State {
        menus: MenuObj[]
      }

      interface NewMenus {
        [key: number]: MenuObj
      }
      const store = useStore();
     // const newMenus:NewMenus = store.getters.getNewMenus;
      // 计算属性
      const newMenus = computed<NewMenus>(()=>store.getters.getNewMenus)
      // [{title: '一级菜单标题', children: [{二级菜单对象}, {二级菜单对象}]}, {。。。}]
      //{ 一级菜单id: {title: '一级菜单标题', children: [{二级菜单对象}, {二级菜单对象}]}}
</script>
<style lang='less' scoped>

.homepage_container {
  position: relative;
  height: 100%;
}
  .homepage_header {
    height: 70px;
    background-color: antiquewhite;
  }
  .homepage_menu {
    position: absolute;
    top: 70px;
    left: 0;
    bottom: 0;
    width: 250px;
    background-color: beige;
  }
  .homepage_content {
    position: absolute;
    top: 70px;
    right: 0;
    left: 250px;
    bottom: 0;
    background-color: skyblue;
  }

</style>