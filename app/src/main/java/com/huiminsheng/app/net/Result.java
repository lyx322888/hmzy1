package com.huiminsheng.app.net;

/**
 * 请求结果
 * Created by Lyx on 2017/11/17.
 */
public class Result<T> {
    private int status;
    private T result;
    private String msg; // 提示信息。
    Result(T result, String msg, int status) {
        this.result = result;
        this.msg = msg;
        this.status = status;
    }
    //请求状态（成功 缺少参数 异地。。。）
    public int getStatus() {
        return status;
    }
    //得到所需数据
    public T getResult() {
        return result;
    }
    //提示信息
    public String getMsg() {
        return msg;
    }

}
