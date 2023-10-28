import request from './request'

// T={}  T不传时,默认空对象
type PromiseRes<T={}>= Promise<ManageResult<T>>;// 替代Promise<ManageResult<AdminLoginRes>>

interface AdminLoginData {
    password: string
    username: string
}
interface ManageResult <T>{
    code: number
    data:T
    message:string
}
interface AdminLoginRes{
    token:string
    tokenHead:string
}
interface AdminInfoRes{
    menu:[]
}
interface AdminInfoRes{
    menu:[]
}
interface AdminListParames{keyword:string;pageNum:number;pageSize:number}

export const adminLoginApi = (data:AdminLoginData):PromiseRes<AdminLoginRes> =>request.post('admin/login',data);
export const getAdminInfoApi = ():PromiseRes<AdminInfoRes>=>request.get('admin/info');
export const getAdminLists = (data:AdminListParames):PromiseRes<{ list: {}[] }>=>request.get('admin/list',{params:data});
export const updateAdmin = (id:string,data:AdminObjItf):PromiseRes=>request.post('admin/update/'+id,data);


