package com.huiminsheng.app.net;


import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * 请求泛型封装
 * Created by Lyx on 2017/11/17.
 */

public  class MyRestRequest<T> extends Request<Result<T>> {
    private Class<T> clazz;
    private int type;
    private  String debugName;
    static final int ENTITY_LIST = 1; //泛型数组
    static final int ENTITY = 2;        //实体类
    static final int STRING = 3;         //string

     MyRestRequest(String url, RequestMethod requestMethod, Class<T> clazz, int type, String debugName) {
        super(url, requestMethod);
        this.clazz = clazz;
        this.type = type;
        this.debugName = debugName;
    }

     MyRestRequest(String url, RequestMethod requestMethod, int type, String debugName) {
        super(url, requestMethod);
        this.type = type;
         this.debugName = debugName;
     }

//    @Override
//    public void onPreExecute() {
//        super.onPreExecute();   这里可以做一下md5加密之类耗时的准备工作
//    }

    @Override
    public Result<T> parseResponse(Headers headers, byte[] body) throws Exception {
        int responseCode = headers.getResponseCode(); // 响应码。
        // 响应码等于200，Http层成功。
        if (responseCode == 200) {
            if (body == null || body.length == 0) {
                // 服务器包体为空。
                return new Result<>(null, null, 123);
            } else {
                // 这里可以统一打印所有请求的数据：
                String bodyString = StringRequest.parseResponseString(headers, body);
                //请求debug
                if (!TextUtils.isEmpty(debugName)){
                    Log.e(debugName,bodyString );
                }
                try {
                    JSONObject bodyObject = new JSONObject(bodyString);
                    // 业务层成功。
                    JSONObject result = bodyObject.getJSONObject("result");//业务响应码
                    int code = result.getInt("code");//业务响应码
                    String msg = result.getString("msg");//业务提示
                    if (code == 10000 | code == -1) {
                        // 1时为业务成功 -1可能为没数据 缺少参数 没有更多等
                        switch (type) {
                            case STRING:
                                //string请求
                            return new Result<>((T) bodyString, msg, code);
                            case ENTITY:
                                //实体类请求
                                T results = new Gson().fromJson(bodyString,clazz);
                                return new Result<>(results, msg, code);
                            case ENTITY_LIST:
                                //泛型数组请求
                                JSONArray jsonArray =bodyObject.getJSONArray("data");
                                ArrayList<T> list = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    list.add(new Gson().fromJson(jsonArray.getString(0),clazz));
                                }
//                                T list = new Gson().fromJson(data, new TypeToken<List<T>>() {}.getType());  出现问题 泛型在编译期类型被擦除
                                return new Result<>( (T) list, msg, code);
                            default:return new Result<>(null, msg, code);
                        }
                    } else {
                        return new Result<>(null, msg, code);
                    }
                } catch (Exception e) {
                    // 解析异常，测试时通过，正式发布后就是服务器的锅。
                    return new Result<>(null, bodyString, 123);
                }
            }
        } else { // 其它响应码，如果和服务器没有约定，那就是服务器发生错误了。
            String error = headers.toString();
            return new Result<>(null, error, 333);
        }
    }
}
