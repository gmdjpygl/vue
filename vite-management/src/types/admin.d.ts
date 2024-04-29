// 抽取全局接口(tsconfig include配置,其他文件不用导入可以直接使用)
interface AdminObjItf {
  id?:string;
  name?: string;
}
interface NewMenus {
  [key: number]: Menu
}
interface Menu {
  id:number,
  parentId:number,
  createTime:string,
  title:string,
  level:number,
  sort:number,
  name:string,
  icon:string,
  hidden:number,
  children?:Menu[]
}