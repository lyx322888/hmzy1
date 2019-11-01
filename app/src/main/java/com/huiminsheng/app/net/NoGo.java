package com.huiminsheng.app.net;

import android.content.Context;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by Lyx on 2017/12/1.
 */
public class NoGo {

    private Context context; //上下文
    private String url; //请求地址
    private int what ; //回调标识  默认0 用于多个请求公用一个回调时区分
    private boolean isShowDialog; //是否显示Dialog 默认不显示
    private boolean isCache; //是否缓存 默认不缓存
    private HashMap<String,Object> map; //参数
    private Object sign; //请求标识 可用于取消拥有该标识的请求 默认为上下文
    private String debugName; //设置并显示debug
    private boolean isPost;//请求方式 默认post

    private NoGo(Context context){
        this.context = context;
        this.isShowDialog = false;
        this.isCache = false;
        this.isPost = true;
        this.map = Parameters.getParame(context); //初始化请求参数 带token
        this.what = 0;
        this.sign = context;
    }
    //初始化
    public  static NoGo create(Context context){
            return new NoGo(context);
    }
    //设置请求地址
    public NoGo setUrl(String url){
        this.url = url;
        return this;
    }
    // 从外部设置map
    public NoGo setMap(HashMap<String,Object> map){
        this.map = map;
        return this;
    }

    //回调标识  默认0 用于多个请求公用一个回调时区分
    public NoGo setWhat(int what){
        this.what = what;
        return this;
    }

    //请求方式，未设置的话默认post
    public NoGo post( ){
        this.isPost = true;
        return this;
    }
    //请求方式，未设置的话默认post
    public NoGo get( ){
        this.isPost = false;
        return this;
    }

     //显示Dialog
    public NoGo showDialog(){
        this.isShowDialog = true;
        return this;
    }
    //开启缓存
    public NoGo cache(){
        this.isCache = true;
        return this;
    }
    //显示Debug
    public NoGo setDebugName(String debugName){
        this.debugName = debugName;
        return this;
    }
    //添加请求参数
    public NoGo addParam(String key,String value){
        map.put(key,value);
        return this;
    }

    //添加文件
    public NoGo addFile(String key,File file){
        map.put(key,file);
        return this;
    }

    //添加多文件
    public NoGo addFiles(ArrayList<File> files){
        for (int i = 0; i < files.size(); i++) {
            String key = "file[" + i + "]";
            map.put(key, files.get(i));
        }
        return this;
    }

    //请求标识 可用于取消拥有该标识的请求 默认为上下文
    public NoGo setSign(Object sign){
        this.sign = sign;
        return this;
    }

    /**
     *  用于页面销毁时 取消请求  避免内存泄漏
     * @param object  请求标识 默认为 页面上下文 可不创建直接传入 cancelBySign（this）
     */
    public static void cancelBySign(Object object) {
       CallServer.getInstance().queue.cancelBySign(object);
    }


    /**
     * （一） 返回 1 或 -1 （如列表页的没有更多）的泛型回调 由页面自行处理这两种情况
     * （二） 因为这里也解析了 -1的数据 所以严格要求 当出现-1时 服务器的返回类型不能偏差
     * （三） 如果不需要解析  可以使用 requestString
     *
     * @param tClass   要解析成的类
     * @param listener  回调
     * @param <R>  用于一般页面加载请求
     *
     */
    public  <R> void request(Class <R> tClass,BaseListener<R> listener){
        MyRestRequest<R> restRequest = new MyRestRequest<>(url, (isPost)?RequestMethod.POST:RequestMethod.GET,  tClass,MyRestRequest.ENTITY,debugName);
        restRequest.add(map);
        restRequest.setCancelSign(sign);//添加请求标识 用于取消
        //是否缓存
        if (isCache){
            //REQUEST_NETWORK_FAILED_READ_CACHE请求失败返回上次缓存的数据（建议使用这种）
        restRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        }
        CallServer.getInstance().request(what,restRequest,new BaseResponseListener<>(context,isShowDialog,listener,restRequest));
    }

    /**
     *    （一） 只返回 业务码为 1 的情况 其他情况由BaseResponseListenerDialog 处理
     *    （二） 回调中带有 服务器返回的json数据  如需要可另行解析  如登录页
     * @param listenerDialog 回调
     */
    public void requestString(BaseListener<String> listenerDialog){
        MyRestRequest<String> restRequest = new MyRestRequest<>(url, (isPost)?RequestMethod.POST:RequestMethod.GET,MyRestRequest.STRING,debugName);
        restRequest.add(map);
        restRequest.setCancelSign(sign);//添加请求标识 用于取消
        //是否缓存
        if (isCache){
            //REQUEST_NETWORK_FAILED_READ_CACHE请求失败返回上次缓存的数据（建议使用这种）
            restRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        }
        CallServer.getInstance().request(what,restRequest,new BaseResponseListenerDialog<>(context,isShowDialog,listenerDialog,restRequest));
    }

    /**
     * 除了异地什么都返回  让页面更自由处理
     * @param listener
     */
    public void request(BaseListener<String> listener){
        MyRestRequest<String> restRequest = new MyRestRequest<>(url, (isPost)?RequestMethod.POST:RequestMethod.GET,MyRestRequest.STRING,debugName);
        restRequest.add(map);
        restRequest.setCancelSign(sign);//添加请求标识 用于取消
        //是否缓存
        if (isCache){
            //REQUEST_NETWORK_FAILED_READ_CACHE请求失败返回上次缓存的数据（建议使用这种）
            restRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        }
        CallServer.getInstance().request(what,restRequest,new BaseResponseListenerString<>(context,isShowDialog,listener,restRequest));
    }
    /**
     * 直接返回string
     * @param listener
     */
    public void normallyString( OnResponseListener<String> listener){
        StringRequest restRequest = new StringRequest(url, (isPost)?RequestMethod.POST:RequestMethod.GET);
        restRequest.add(map);
        restRequest.setCancelSign(sign);//添加请求标识 用于取消
        //是否缓存
        if (isCache){
            //REQUEST_NETWORK_FAILED_READ_CACHE请求失败返回上次缓存的数据（建议使用这种）
            restRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        }
        CallServer.getInstance().request(what,restRequest,listener);
    }

}
