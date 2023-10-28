import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import { App } from 'vue'
import store from '../store'
import Cookies from 'js-cookie';
import { getAdminInfoApi } from '../request/api'
// import Vue from 'vue';
// Vue.use(VueRouter)
//Vue2 use : 调用的就是参数上的install方法，或者是直接调用参数。Vue.prototype.$router/$route

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login/login.vue')
  }

  // http://localhost:3000/a/b/c  
  // http://localhost:3000/a/b/pms/product
  // http://localhost:3000/pms/product
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
  const menus = store.getters.getNewMenus;
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
  // 动态添加首页
  router.addRoute({
    path: '/',
    name: 'homepage',
    component: () => import('../views/homepage/homepage.vue'),
    redirect: '/index',
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
  // 1.token && vuex里面menus（权限列表）为空
  const token = Cookies.get('token');
  console.log(store);
  if (token && store.state.menus.length === 0) {  // 登录后,vuex菜单为空时
         // 异步
        store.dispatch('getAdminInfo').then(() => {
            genRoutes();
            next(to);
         });
  } else if (token && store.state.menus.length !== 0 && from.path === '/login' && to.path === '/homepage') {// 
        // 动态添加路由规则
        genRoutes();
        next('/index');
  } else if (!token && to.path !== '/login') {
        next('/login');
  } else if (token && to.path === '/login') {
        next(from);
  } else {
        next();
  }

})

export const initRouter = (app: App<Element>) => {
  app.use(router);
}

// export default router