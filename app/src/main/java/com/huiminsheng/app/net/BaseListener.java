package com.huiminsheng.app.net;

/**
 *  抽象类 可以选择自己需要的方法  onSucceed必带
 * Created by Lyx on 2017/11/20.
 */
public abstract class BaseListener<T> {
    public  void onStart(int what){}
    public abstract void onSucceed(int what, Result<T> result);
    public  void onFailed(int what){}
    public  void onFinish(int what){}
}
