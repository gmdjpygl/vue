import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import { App } from 'vue'
import {menuStore} from '../store/menuStore'
import Cookies from 'js-cookie';
import { getAdminInfoApi } from '../request/api'
// import Vue from 'vue';
// Vue.use(VueRouter)
//Vue2 use : 调用的就是参数上的install方法，或者是直接调用参数。Vue.prototype.$router/$route

const routes: RouteRecordRaw[] = [
  // 默认配置登录页面路由
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login/login.vue')
  },
  /* {
      path: '/pms',
      name: 'pms',
      component: () => import('../views/homepage/homepage.vue'),
      children: [
        {
          path: 'product', //  /product
          name: 'product',
          component: () => import('../views/pms/product.vue'),
        }
      ]
    } */
]

const router = createRouter({
  history: createWebHashHistory(),
  routes // 路由配置
});


// 根据getters里面的菜单对象生成路由规则
const genRoutes = () => { 
  const mStore = menuStore();

  const menus = mStore.getNewMenus;
  // const newRoutes: RouteRecordRaw[] = [];
  // 循环菜单对象
  for (let key in menus) {
    const newRoute: RouteRecordRaw = {
      path: '/' + menus[key].name,
      name: menus[key].name,
      component: () => import('../views/homepage/homepage.vue'),
      redirect: '/' + menus[key].name + '/' + menus[key].children[0].name,
      children: []
    };
    for (let i = 0; i < menus[key].children.length; i++) {
      newRoute.children?.push({
        path: menus[key].children[i].name,
        name: menus[key].children[i].name,
        component: () => import(`../views/${menus[key].name}/${menus[key].children[i].name}.vue`)
      })
    } 
    // 动态添加路由规则
    router.addRoute(newRoute)
  }
  // 动态添加首页 children展示在router-view里
  router.addRoute({
    path: '/',
    name: 'homepage',
    component: () => import('../views/homepage/homepage.vue'),
    redirect: 'index',
    children: [
      {
        path: 'index',
        name: 'index',
        component: () => import('../views/index/index.vue'),
      }
    ]
  });
}

// 前置导航守卫
router.beforeEach((to, from, next) => {
  const mStore = menuStore();
const {  getNewMenus } = mStore
  // 1.token && vuex里面menus（权限列表）为空
  const token = Cookies.get('token');
  
  if (token && mStore.menus.length === 0) {  // 登录后,跳转页面或刷新时执行vuex菜单为空时
        console.log('登录后,跳转页面或刷新时执行vuex菜单为空时');
         // 异步
         mStore.getMenus().then(() => {
            mStore.getMenus(); 
            genRoutes();
            next(to);
         })
       //  });
  } else if (token && mStore.menus.length !== 0 && from.path === '/login' && to.path === '/homepage') {
         console.log('第一次登录');
        // 动态添加路由规则
        genRoutes();
        next('/index');
  } else if (!token && to.path !== '/login') {
        console.log('没有登录时打开页面');
        next('/login');
  } else if (token && to.path === '/login') {
        console.log('登录后再次打开登录页面');
        next(from);
  } else{
        // 打开to页面
        console.log('下一步');
        //genRoutes()
  //      console.log(router.getRoutes());
        next();
  }

})

export const initRouter = (app: App<Element>) => {
  app.use(router);
}

// export default router