package com.huiminsheng.app.utils;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Rx实现倒计时
 * @author Lyx
 * @date 2017/11/23
 */
public class CountdownUtils {
    public static void countdown(Observer<Long> observer, final int time){
       Observable.interval(0, 1, TimeUnit.SECONDS)//0 延迟 1秒发送一次 TimeUnit.SECONDS单位秒
                .take(time + 1)//执行多少次
                .subscribeOn(Schedulers.io())//在io线程执行
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return time - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//观察者执行线程 UI线程
                .subscribe(observer);
    }
}
