package com.huiminsheng.app.net;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * 网络公用一个线程
 */

public class CallServer {
    private static CallServer instance;

    public static CallServer getInstance() {
        if (instance == null) {
            synchronized (CallServer.class) {
                if (instance == null) {
                    instance = new CallServer();
                }
            }
        }
        return instance;
    }

     public RequestQueue queue;

    private CallServer() {
        queue = NoHttp.newRequestQueue(5);
    }


    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        queue.add(what, request, listener);
    }



    // 完全退出app时，调用这个方法释放CPU。
    public void stop() {
        queue.stop();
        instance = null;
    }

    public void cancelAll() {
        queue.cancelAll();
    }


}
