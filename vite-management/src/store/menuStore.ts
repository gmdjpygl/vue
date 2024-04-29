// 引用defineStore ,创建pinia仓库
import { defineStore } from 'pinia'
import {getAdminInfoApi}  from '../request/api'
// 导出menuStore仓库函数
export const menuStore = defineStore('menuStore1', {
   state: ():{menus:Menu[]} => (
      { 
        menus:[]
      }),
    getters: {
      getNewMenus() {
        console.log('--------------------')
        const newMenus: NewMenus = {};
        // 获取旧的菜单
        const menus = this.menus;
        for (let i = 0; i < menus.length; i++) {
          if (menus[i].parentId === 0) {
            // 一级菜单对象
            newMenus[menus[i].id] = { ...menus[i],children:newMenus[menus[i].id]?.children||[]}
          } else {
            // 二级菜单
            let parentId = menus[i].parentId; // 对应的一级菜单id
            newMenus[parentId] =  newMenus[parentId]||[];
            newMenus[parentId].children = newMenus[parentId].children || [];
            newMenus[parentId].children?.push(menus[i]);
          }
        }
        return newMenus
      }
    },
    actions: {
      getMenus() {
         // this.menus =  [{ "id": 7, "parentId": 0, "createTime": "2020-02-02T08:54:07.000+00:00", "title": "订单", "level": 0, "sort": 0, "name": "oms", "icon": "order", "hidden": 0 }, { "id": 8, "parentId": 7, "createTime": "2020-02-02T08:55:18.000+00:00", "title": "订单列表", "level": 1, "sort": 0, "name": "order", "icon": "product-list", "hidden": 0 }, { "id": 9, "parentId": 7, "createTime": "2020-02-02T08:56:46.000+00:00", "title": "订单设置", "level": 1, "sort": 0, "name": "orderSetting", "icon": "order-setting", "hidden": 0 }, { "id": 10, "parentId": 7, "createTime": "2020-02-02T08:57:39.000+00:00", "title": "退货申请处理", "level": 1, "sort": 0, "name": "returnApply", "icon": "order-return", "hidden": 0 }, { "id": 11, "parentId": 7, "createTime": "2020-02-02T08:59:40.000+00:00", "title": "退货原因设置", "level": 1, "sort": 0, "name": "returnReason", "icon": "order-return-reason", "hidden": 0 }, { "id": 1, "parentId": 0, "createTime": "2020-02-02T06:50:36.000+00:00", "title": "商品", "level": 0, "sort": 0, "name": "pms", "icon": "product", "hidden": 0 }, { "id": 2, "parentId": 1, "createTime": "2020-02-02T06:51:50.000+00:00", "title": "商品列表", "level": 1, "sort": 0, "name": "product", "icon": "product-list", "hidden": 0 }, { "id": 3, "parentId": 1, "createTime": "2020-02-02T06:52:44.000+00:00", "title": "添加商品", "level": 1, "sort": 0, "name": "addProduct", "icon": "product-add", "hidden": 0 }, { "id": 4, "parentId": 1, "createTime": "2020-02-02T06:53:51.000+00:00", "title": "商品分类", "level": 1, "sort": 0, "name": "productCate", "icon": "product-cate", "hidden": 0 }, { "id": 5, "parentId": 1, "createTime": "2020-02-02T06:54:51.000+00:00", "title": "商品类型", "level": 1, "sort": 0, "name": "productAttr", "icon": "product-attr", "hidden": 0 }, { "id": 6, "parentId": 1, "createTime": "2020-02-02T06:56:29.000+00:00", "title": "品牌管理", "level": 1, "sort": 0, "name": "brand", "icon": "product-brand", "hidden": 0 }, { "id": 12, "parentId": 0, "createTime": "2020-02-04T08:18:00.000+00:00", "title": "营销", "level": 0, "sort": 0, "name": "sms", "icon": "sms", "hidden": 0 }, { "id": 13, "parentId": 12, "createTime": "2020-02-04T08:19:22.000+00:00", "title": "秒杀活动列表", "level": 1, "sort": 0, "name": "flash", "icon": "sms-flash", "hidden": 0 }, { "id": 14, "parentId": 12, "createTime": "2020-02-04T08:20:16.000+00:00", "title": "优惠券列表", "level": 1, "sort": 0, "name": "coupon", "icon": "sms-coupon", "hidden": 0 }, { "id": 16, "parentId": 12, "createTime": "2020-02-07T08:22:38.000+00:00", "title": "品牌推荐", "level": 1, "sort": 0, "name": "homeBrand", "icon": "product-brand", "hidden": 0 }, { "id": 17, "parentId": 12, "createTime": "2020-02-07T08:23:14.000+00:00", "title": "新品推荐", "level": 1, "sort": 0, "name": "homeNew", "icon": "sms-new", "hidden": 0 }, { "id": 18, "parentId": 12, "createTime": "2020-02-07T08:26:38.000+00:00", "title": "人气推荐", "level": 1, "sort": 0, "name": "homeHot", "icon": "sms-hot", "hidden": 0 }, { "id": 19, "parentId": 12, "createTime": "2020-02-07T08:28:16.000+00:00", "title": "专题推荐", "level": 1, "sort": 0, "name": "homeSubject", "icon": "sms-subject", "hidden": 0 }, { "id": 20, "parentId": 12, "createTime": "2020-02-07T08:28:42.000+00:00", "title": "广告列表", "level": 1, "sort": 0, "name": "homeAdvertise", "icon": "sms-ad", "hidden": 0 }, { "id": 21, "parentId": 0, "createTime": "2020-02-07T08:29:13.000+00:00", "title": "权限", "level": 0, "sort": 0, "name": "ums", "icon": "ums", "hidden": 0 }, { "id": 22, "parentId": 21, "createTime": "2020-02-07T08:29:51.000+00:00", "title": "用户列表", "level": 1, "sort": 0, "name": "admin", "icon": "ums-admin", "hidden": 0 }, { "id": 23, "parentId": 21, "createTime": "2020-02-07T08:30:13.000+00:00", "title": "角色列表", "level": 1, "sort": 0, "name": "role", "icon": "ums-role", "hidden": 0 }, { "id": 24, "parentId": 21, "createTime": "2020-02-07T08:30:53.000+00:00", "title": "菜单列表", "level": 1, "sort": 0, "name": "menu", "icon": "ums-menu", "hidden": 0 }, { "id": 25, "parentId": 21, "createTime": "2020-02-07T08:31:13.000+00:00", "title": "资源列表", "level": 1, "sort": 0, "name": "resource", "icon": "ums-resource", "hidden": 0 }, { "id": 26, "parentId": 21, "createTime": "2021-10-10T06:12:26.000+00:00", "title": "分配角色", "level": 1, "sort": 0, "name": "allocRole", "icon": "", "hidden": 1 }, { "id": 27, "parentId": 21, "createTime": "2021-10-10T06:14:59.000+00:00", "title": "编辑角色", "level": 1, "sort": 0, "name": "editUser", "icon": "editUser", "hidden": 1 }, { "id": 28, "parentId": 21, "createTime": "2021-10-10T06:16:02.000+00:00", "title": "添加菜单", "level": 1, "sort": 0, "name": "addMenu", "icon": "addMenu", "hidden": 1 }, { "id": 29, "parentId": 21, "createTime": "2021-10-10T07:30:23.000+00:00", "title": "编辑菜单", "level": 1, "sort": 0, "name": "editMenu", "icon": "editMenu", "hidden": 1 }, { "id": 30, "parentId": 21, "createTime": "2021-10-10T07:33:14.000+00:00", "title": "分配资源", "level": 1, "sort": 0, "name": "allocResource", "icon": "allocResource", "hidden": 1 }]
         return new Promise((resolve,reject)=>{
          getAdminInfoApi().then(
            res=>{
              if(res.code==200){
                  this.menus =res.data.menu;
                  resolve(res.data);
              }else{
                reject(res);
              }
            }
          )
        })
        
        },
    },
    //persist: true,
})

