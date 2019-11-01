package com.huiminsheng.app.net;

import android.content.Context;

import com.huiminsheng.app.utils.SPUtils;

import java.util.HashMap;

public class Parameters {
    public static HashMap<String, Object> getParame(Context context){
        HashMap<String,Object> map = new HashMap<>();
        map.put("oemid","90005");
        map.put("mchid", SPUtils.get(context,ApiUrls.KEY.MCHID,""));
        map.put("access_token", SPUtils.get(context,ApiUrls.KEY.ACCESS_TOKEN,""));
        map.put("os", "1");
        return map;
    }
}
